package com.newsfeed.dao;

import com.newsfeed.models.Model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


import org.springframework.beans.factory.annotation.Autowired;


import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class HibernateAbstract<T extends Model> {
    @Autowired
    private SessionFactory sessionFactory;
    private Class<T> clazz;


    public HibernateAbstract(){
        final ParameterizedType supperClass = (ParameterizedType) getClass().getGenericSuperclass();
        this.clazz = (Class<T>)((ParameterizedType) supperClass).getActualTypeArguments()[0];
    }


    public List<T> getAll() {
        return (List<T>) getSession().createCriteria(clazz).list();
    }

    public T getById(Long id) {
        return getSession().get(clazz, id);
    }

    public void add(T model) {
        getSession().save(model);
    }

    public void update(T model) {
        getSession().update(model);
    }

    public void remove(T model) {
        getSession().delete(model);
    }

    public Session getSession(){
        return this.sessionFactory.getCurrentSession();
    }
}