package com.solvd.dao.mybatisimpl;

import com.solvd.dao.interfaces.ICityDAO;
import com.solvd.dao.interfaces.ICityDAO;
import com.solvd.dao.models.City;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CityDAO implements ICityDAO {
    private static final Logger logger = LogManager.getLogger(CityDAO.class);

    @Override
    public City getById(int id) {
        SqlSession session = MyBatis.getSession();
        try {
            ICityDAO mapper = session.getMapper(ICityDAO.class);
            return mapper.getById(id);
        } catch (Exception e) {
            logger.error(e);
        } finally {
            MyBatis.close(session);
        }
        return null;
    }

    @Override
    public void insert(City company) {
        SqlSession session = MyBatis.getSession();
        try {
            ICityDAO mapper = session.getMapper(ICityDAO.class);
            mapper.insert(company);
        } catch (Exception e) {
            logger.error(e);
        } finally {
            MyBatis.close(session);
        }
    }

    @Override
    public void update(City company) {
        SqlSession session = MyBatis.getSession();
        try {
            ICityDAO mapper = session.getMapper(ICityDAO.class);
            mapper.update(company);
        } catch (Exception e) {
            logger.error(e);
        } finally {
            MyBatis.close(session);
        }
    }

    @Override
    public void delete(int id) {
        SqlSession session = MyBatis.getSession();
        try {
            ICityDAO mapper = session.getMapper(ICityDAO.class);
            mapper.delete(id);
        } catch (Exception e) {
            logger.error(e);
        } finally {
            MyBatis.close(session);
        }
    }

    // @Override
    // public List<City> getProbableRouteCities(String city) {
    // return null;
    // }

    @Override
    public City getCityByName(String name) {
        return null;
    }

    @Override
    public List<City> getCitiesInRange(String from, String to) {
        return null;
    }
}
