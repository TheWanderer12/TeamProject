package com.solvd.dao.service;

import com.solvd.dao.models.City;
import com.solvd.dao.jdbcimpl.CityDAO;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class DistanceCalculator {
    public static final CityDAO dao = new CityDAO();
    // nothing
    final static int earthRadiusKm = 6371;
    final static int earthRadiusMile = 3963;
    final static NumberFormat nf = new DecimalFormat("#0.00");

    // get distance between two coordinates
    public static double getDistance(double originlat, double originlng, double destinationlat, double destinationlng) {
        // formula to calculate distance between two Locations on Sphere
        double result = earthRadiusKm * Math.acos((Math.sin(Math.toRadians(originlat))
                * Math.sin(Math.toRadians(
                        destinationlat)))
                + Math.cos(Math.toRadians(
                        originlat))
                        * Math.cos(Math.toRadians(
                                destinationlat))
                        * Math.cos(Math.toRadians(
                                destinationlng)
                                - Math.toRadians(
                                        originlng)));

        // System.out.println( "distance between " + origin + " and "+ destination + ":
        // " + result + " Km");
        return result;
    }

    // checks which city is the closest to the straight line between origin and
    // destination
    public static City getClosestCityToDiagonal(City from, City to) {
        // from -> chosen origin location
        // to -> chosen destination location
        List<City> cities = dao.getCitiesInRange(from, to);

        // setting initial Closest city value
        City result = cities.get(0);

        // checks distances between chosen city(from) and all cities in range
        for (int i = 1; i < cities.size(); i++) {
            // checks if city is closer than initial closest city
            if (getDistance(from.getLat(), from.getLng(), cities.get(i).getLat(), cities.get(i).getLng())
                    + getDistance(cities.get(i).getLat(), cities.get(i).getLng(), to.getLat(),
                            to.getLng()) < getDistance(from.getLat(), from.getLng(), result.getLat(), result.getLng())
                                    + getDistance(result.getLat(), result.getLng(), to.getLat(), to.getLng())) {
                result = cities.get(i);
            }
        }
        return result;
    }

    // main function: creates path of cities in form of a list
    public static List<City> getPathCities(City from, City to) {
        List<City> pathCities = new ArrayList<>();
        pathCities.add(from);
        while (dao.getCitiesInRange(from, to).size() > 0) {
            from = getClosestCityToDiagonal(from, to);
            pathCities.add(from);
        }
        // if size is 0 its end of path and destination is added to path of cities
        pathCities.add(to);
        return pathCities;
    }

    public static void printPathCities(List<City> pathCities) {
        for (City c : pathCities) {
            if (c.equals(pathCities.get(pathCities.size() - 1))) {
                System.out.println(c.getName());
            } else {
                System.out.print(c.getName() + " -> ");
            }
        }
    }

    public static void printPathLength(List<City> pathCities) {
        double length = 0;
        for (int i = 1; i < pathCities.size(); i++) {
            length += getDistance(pathCities.get(i).getLat(), pathCities.get(i).getLng(),
                    pathCities.get(i - 1).getLat(), pathCities.get(i - 1).getLng());
        }
        System.out.println("Path Length = " + nf.format(length) + " Km");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // User input
        System.out.println("Enter origin: ");
        City from = dao.getCityByName(scanner.nextLine());
        System.out.println("Enter Destination: ");
        City to = dao.getCityByName(scanner.nextLine());

        System.out.println(
                "Calculating Path From " + from.getName() + " to " + to.getName() + ". it might take few mins...");

        // System.out.println(getDistance(from.getLat(), from.getLng(), to.getLat(),
        // to.getLng()));

        // List<City> pathCities = getPathCities(from, to);
        // printPathCities(pathCities);
        // printPathLength(pathCities);

        // System.out.println( "\nTokyo lng: " + from.getLng() + " lat: " +
        // from.getLat()
        // +"\nshanghai lng: " + to.getLng() + " lat: " + to.getLat());
    }
}
