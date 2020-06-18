package com.lacussoft.sijoga.services;

import com.lacussoft.sijoga.model.User;
import com.lacussoft.utils.HibernateUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import org.hibernate.Query;
import org.hibernate.Session;

@Stateless
public class Dao implements Serializable {

    private Session session;

    @PostConstruct
    public void onInit() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @PreDestroy
    public void onDestroy() {
        session.close();
    }

    public void create(Object model) {

        if (model instanceof User) {
            ((User) model).hashPassword();
        }

        session.beginTransaction();
        session.save(model);
        session.getTransaction().commit();
    }

    public Query query(String hql) {
        return session.createQuery(hql);
    }
}
