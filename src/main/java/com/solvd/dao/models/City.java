package com.solvd.dao.models;

public class City {
    private int id;
    private String name;

    private double lat;
    private double lng;
    private String country;

    public City(){

    }




    public City(int id, String name, double lat, double lng, String country) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
