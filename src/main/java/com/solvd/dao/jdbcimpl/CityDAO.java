package com.solvd.dao.jdbcimpl;

import com.solvd.dao.connector.ConnectionPool;
import com.solvd.dao.interfaces.ICityDAO;
import com.solvd.dao.models.City;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CityDAO implements ICityDAO {
    private static final Logger logger = LogManager.getLogger(com.solvd.dao.mybatisimpl.CityDAO.class);

    @Override
    public City getById(int id) {
        try {
            Connection conn = ConnectionPool.getInstance().retrieve();
            PreparedStatement statement = conn.prepareStatement("SELECT id, city, city_ascii, lat, lng, country FROM cities WHERE id = ?");
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
                city.setCity(result.getString("city"));
                city.setCityAscii(result.getString("city_ascii"));
                city.setLat(result.getString("lat"));
                city.setLng(result.getString("lng"));
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
            PreparedStatement statement = conn.prepareStatement("INSERT INTO cities(city, city_ascii, lat, lng, country) VALUES (?,?,?,?,?)");
            statement.setString(1, city.getCity());
            statement.setString(2, city.getCityAscii());
            statement.setString(3, city.getLat());
            statement.setString(4, city.getLng());
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
            PreparedStatement statement = conn.prepareStatement("UPDATE cities SET city = ?, city_ascii = ?, lat = ?, lng = ?, country = ?  WHERE id = ?");
            statement.setString(1, com.getCity());
            statement.setString(2, com.getCityAscii());
            statement.setString(3, com.getLat());
            statement.setString(4, com.getLng());
            statement.setString(4, com.getCountry());
            statement.setInt(5, com.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
    }





}
