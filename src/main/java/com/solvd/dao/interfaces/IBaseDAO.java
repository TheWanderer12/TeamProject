package com.solvd.dao.interfaces;

public interface IBaseDAO<T> {
    void insert(T object);

    T getById(int id);

    void delete(int id);

    void update(T object);
}
