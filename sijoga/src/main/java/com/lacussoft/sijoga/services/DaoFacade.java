package com.lacussoft.sijoga.services;

import com.lacussoft.sijoga.model.Juiz;
import com.lacussoft.sijoga.model.Model;
import com.lacussoft.sijoga.model.Process;
import com.lacussoft.sijoga.model.User;
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

    public List<Process> getProcessesFor(User user) {
        Criteria criteria = createCriteria(Process.class);
        criteria.createAlias("promoter.lawyer", "promoterLawyer");
        criteria.createAlias("promoted.lawyer", "promotedLawyer");
        criteria.add(Restrictions.or(
            Restrictions.eq("judge.id", user.getId()),
            Restrictions.eq("promoter.id", user.getId()),
            Restrictions.eq("promoted.id", user.getId()),
            Restrictions.eq("promoterLawyer.id", user.getId()),
            Restrictions.eq("promotedLawyer.id", user.getId())
        ));

        return criteria.list();
    }

    public User findUserByCpf(String cpf)  {
        return (User) createQuery("FROM User u WHERE u.cpf = :cpf")
            .setString("cpf", cpf)
            .uniqueResult();
    }

    public Juiz getRandomJudge() {
        Random rand = new Random();
        List<Juiz> judges = createQuery("FROM Juiz").list();
        return judges.get(rand.nextInt(judges.size()));
    }
}
