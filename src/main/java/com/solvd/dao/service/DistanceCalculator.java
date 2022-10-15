package com.solvd.dao.service;

import com.solvd.dao.models.City;
import com.solvd.dao.jdbcimpl.CityDAO;

public class DistanceCalculator {
    final static int earthRadiusKm = 6371;
    final static int earthRadiusMile = 3963;
    static double getDistances(String origin, String destination){
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



        System.out.println( "distance between " + origin + " and "+ destination + ": " + result);
        return result;
    }

    public static void main(String[] args) {
//        City city = dao.getById(1);
//        String originLat = city.getLat();
//        String originLng = city.getLng();
//        System.out.println(originLng);

        getDistances("Moscow","delhi");
    }
}
