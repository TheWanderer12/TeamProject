package com.solvd.dao.jdbcimpl;

import com.solvd.dao.connector.ConnectionPool;
import com.solvd.dao.interfaces.ICityDAO;
import com.solvd.dao.models.City;
import com.solvd.dao.service.DistanceCalculator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class CityDAO implements ICityDAO {
    private static final Logger logger = LogManager.getLogger(com.solvd.dao.mybatisimpl.CityDAO.class);

    @Override
    public City getById(int id) {
        try {
            Connection conn = ConnectionPool.getInstance().retrieve();
            PreparedStatement statement = conn
                    .prepareStatement("SELECT id, city, lat, lng, country FROM cities WHERE id = ?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            String output = "";
            City city = new City();
            while (result.next()) {
                output += result.getInt("id")
                        + ":" + result.getString("city")
                        + ":" + result.getString("city_ascii")
                        + ":" + result.getString("lat")
                        + ":" + result.getString("lng")
                        + ":" + result.getString("country");

                city.setId(result.getInt("id"));
                city.setName(result.getString("city"));
                city.setLat(result.getDouble("lat"));
                city.setLng(result.getDouble("lng"));
                city.setCountry(result.getString("country"));
            }
            System.out.println(output);
            return city;
        } catch (SQLException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public void insert(City city) {
        try {
            Connection conn = ConnectionPool.getInstance().retrieve();
            PreparedStatement statement = conn
                    .prepareStatement("INSERT INTO cities(city, lat, lng, country) VALUES (?,?,?,?)");
            statement.setString(1, city.getName());
            statement.setDouble(2, city.getLat());
            statement.setDouble(3, city.getLng());
            statement.setString(4, city.getCountry());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            Connection conn = ConnectionPool.getInstance().retrieve();
            PreparedStatement statement = conn.prepareStatement("DELETE FROM cities WHERE id=?;");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e);
        }

    }

    @Override
    public void update(City com) {
        try {
            Connection conn = ConnectionPool.getInstance().retrieve();
            PreparedStatement statement = conn
                    .prepareStatement("UPDATE cities SET city = ?, lat = ?, lng = ?, country = ?  WHERE id = ?");
            statement.setString(1, com.getName());
            statement.setDouble(2, com.getLat());
            statement.setDouble(3, com.getLng());
            statement.setString(4, com.getCountry());
            statement.setInt(5, com.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public City getCityByName(String c) {
        try {
            Connection conn = ConnectionPool.getInstance().retrieve();
            PreparedStatement statement = conn.prepareStatement("SELECT city, lat, lng FROM cities WHERE city = ?");
            statement.setString(1, c);
            ResultSet result = statement.executeQuery();
            // String output = "";
            City city = new City();
            while (result.next()) {
                city.setName(result.getString("city"));
                city.setLat(result.getDouble("lat"));
                city.setLng(result.getDouble("lng"));

                // output += "lat: " + result.getString("lat")
                // + "\nlng: "+ result.getString("lng");
            }
            // System.out.println(output);
            return city;
        } catch (SQLException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public List<City> getCitiesInRange(String from, String to) {
        try {
            City origin = getCityByName(from);
            City destination = getCityByName(to);
            System.out.println("---STARTING to get CITIES IN RANGE from " + from.toUpperCase() + " to "
                    + to.toUpperCase() + "---");

            Connection conn = ConnectionPool.getInstance().retrieve();
            PreparedStatement statement = conn.prepareStatement("SELECT city, lat, lng FROM cities");
            ResultSet result = statement.executeQuery();

            // String output = "";
            List<City> cities = new ArrayList<>();
            while (result.next()) {
                City city = new City();
                city.setName(result.getString("city"));
                city.setLat(result.getDouble("lat"));
                city.setLng(result.getDouble("lng"));
                cities.add(city);
            }

            // removing origin and destination from List
            Iterator<City> itr = cities.iterator();
            while (itr.hasNext()) {
                City city = itr.next();
                if (city.getName().equals(from) || city.getName().equals(to)) {
                    itr.remove();
                }
            }

            double middlePointLat = (double) ((origin.getLat() + destination.getLat()) / 2);
            double middlePointLng = (double) ((origin.getLng() + destination.getLng()) / 2);
            double radiusOfRangeCircle = (double) (DistanceCalculator.getDistance(origin.getLat(), origin.getLng(),
                    destination.getLat(), destination.getLng()) / 2);

            cities = cities.stream().filter(c -> DistanceCalculator.getDistance(middlePointLat, middlePointLng,
                    c.getLat(), c.getLng()) <= radiusOfRangeCircle).collect(Collectors.toList());

            System.out.print("CITIES IN RANGE:\n[");
            cities.forEach(city -> System.out.print(city.getName() + ", "));
            System.out.println("]");

            System.out.println("-ENDING getting CITIES IN RANGE from " + from.toUpperCase() + " to "
                    + to.toUpperCase() + "-\n");
            return cities;
        } catch (SQLException e) {
            logger.error(e);
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        // CityDAO dao = new CityDAO();
        // City from = dao.getCityByName("Tokyo");
        // City to = dao.getCityByName("Shanghai");
        // dao.getCitiesInRange(from.getName(), to.getName());

        // ArrayList<City> cities = new ArrayList<City>();
        // try {
        // Connection conn = ConnectionPool.getInstance().retrieve();
        // PreparedStatement statement = conn.prepareStatement("SELECT city, lat, lng
        // FROM cities");
        // ResultSet result = statement.executeQuery();

        // // String output = "";
        // cities = new ArrayList<>();
        // while (result.next()) {
        // City city = new City();
        // city.setName(result.getString("city"));
        // city.setLat(result.getDouble("lat"));
        // city.setLng(result.getDouble("lng"));
        // cities.add(city);
        // }
        // } catch (Exception e) {
        // // TODO: handle exception
        // }
        // cities.forEach(city -> System.out.print(city.getName() + ", "));

    }
}
