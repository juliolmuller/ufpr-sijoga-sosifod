package com.lacussoft.sosifod.services;

import com.lacussoft.sosifod.model.Model;
import com.lacussoft.sosifod.model.Intimacao;
import com.lacussoft.sosifod.model.User;
import com.lacussoft.utils.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

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

    public void save(Model model) {

        if (model instanceof User) {
            ((User) model).hashPassword();
        }

        session.beginTransaction();
        session.save(model);
        session.getTransaction().commit();
    }

    public Query createQuery(String hql) {
        return session.createQuery(hql);
    }

    public <T extends Model> Criteria createCriteria(Class<T> clazz) {
        return session.createCriteria(clazz);
    }

    public List<Intimacao> getIntimacoesFor(User user) {
        Criteria criteria = createCriteria(Intimacao.class);

        return criteria.list();
    }

    public <T extends Model> T find(Serializable id, Class<T> clazz) {
        return (T) session.get(clazz, id);
    }

    public User findUserByCpf(String cpf)  {
        return (User) createQuery("FROM User u WHERE u.cpf = :cpf")
            .setString("cpf", cpf)
            .uniqueResult();
    }

}
