package com.solvd.dao.service;

import com.solvd.dao.models.City;
import com.solvd.dao.jdbcimpl.CityDAO;

import java.text.DecimalFormat;
import java.util.List;


public class DistanceCalculator {
    final static int earthRadiusKm = 6371;
    final static int earthRadiusMile = 3963;

    static double getDistance(String origin, String destination){
        CityDAO dao = new CityDAO();
        City from = dao.getCityByName(origin);
        City to = dao.getCityByName(destination);


        from.setLat(Math.toRadians(from.getLat()));
        from.setLng(Math.toRadians(from.getLng()));
        to.setLat(Math.toRadians(to.getLat()));
        to.setLng(Math.toRadians(to.getLng()));


        double result = earthRadiusKm * Math.acos((Math.sin(from.getLat())
                * Math.sin(to.getLat()))
                + Math.cos(from.getLat())
                * Math.cos(to.getLat())
                * Math.cos(to.getLng() - from.getLng()));
        System.out.println( "distance between " + origin + " and "+ destination + ": " + result + " Km");

        return result;
    }

    public static double[][] getDistanceMatrix(City from, City to){
        //list of probable route cities
        List<City> cities = new CityDAO().getCitiesInRange(from, to);
        double[][] myMatrix = new double[cities.size()][cities.size()];

        for(int i = 0; i < cities.size(); i++){
            for (int j = 0; j < cities.size(); j++) {
                myMatrix[i][j] = getDistance(cities.get(i).getCity(),cities.get(j).getCity());
                if(myMatrix[i][j] == getDistance(from.getCity(), to.getCity())){
                    myMatrix[i][j] = 0;
                }
            }
        }
        return myMatrix;
    }

    public static void main(String[] args) {
//        getDistance("Moscow","delhi");
        CityDAO dao = new CityDAO();
        City from = dao.getCityByName("Tokyo");
        City to = dao.getCityByName("Shanghai");
        FloydsAlgorithm.floydWarshall(getDistanceMatrix(from,to));

        System.out.println( "\nTokyo lng: " + from.getLng() + " lat: " + from.getLat()
                +"\nSeoul lng: " + to.getLng() + " lat: " + to.getLat());

        getDistance("Tokyo","Shanghai");

    }
}
