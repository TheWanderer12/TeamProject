package com.solvd.dao.interfaces;

import com.solvd.dao.models.City;

import java.util.List;

public interface ICityDAO extends IBaseDAO<City> {
    // List<City> getProbableRouteCities(String city);
    City getCityByName(String name);

    List<City> getCitiesInRange(String from, String to);
}
