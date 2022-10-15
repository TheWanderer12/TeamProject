package com.solvd.dao.models;

public class City {
    private int id;
    private String city;
    private String cityAscii;
    private String lat;
    private String lng;
    private String country;

    public City(){

    }

    public City(int id, String city, String cityAscii, String lat, String lng, String country) {
        this.id = id;
        this.city = city;
        this.cityAscii = cityAscii;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityAscii() {
        return cityAscii;
    }

    public void setCityAscii(String cityAscii) {
        this.cityAscii = cityAscii;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
