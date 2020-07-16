package com.lacussoft.sosifod.services;

import com.lacussoft.sosifod.model.User;
import com.lacussoft.utils.HibernateUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

@Stateless
public class DaoFacade implements Serializable {

    private Session session;

    @PostConstruct
    public void onInit() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @PreDestroy
    public void onDestroy() {
        session.close();
    }

    public void save(Serializable model) {

        if (model instanceof User) {
            ((User) model).hashPassword();
        }

        session.beginTransaction();
        session.save(model);
        session.getTransaction().commit();
    }

    public <T extends Serializable> T find(Serializable id, Class<T> clazz) {
        return (T) session.get(clazz, id);
    }

    public void destroy(Serializable model) {
        session.beginTransaction();
        session.delete(model);
        session.getTransaction().commit();
    }

    public Query createQuery(String hql) {
        return session.createQuery(hql);
    }

    public <T extends Serializable> Criteria createCriteria(Class<T> clazz) {
        return session.createCriteria(clazz);
    }
}
