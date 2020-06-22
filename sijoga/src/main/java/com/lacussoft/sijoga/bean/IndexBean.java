package com.lacussoft.sijoga.bean;

import com.lacussoft.sijoga.model.Process;
import com.lacussoft.sijoga.model.User;
import com.lacussoft.sijoga.services.Dao;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

@Named
@RequestScoped
public class IndexBean implements Serializable {

    @EJB
    private Dao dao;

    @Inject
    private ExternalContext externalContext;

    public List<Process> getProcesses() {
        HttpSession httpSession = (HttpSession) externalContext.getSession(false);
        User user = (User) httpSession.getAttribute("user");

        Criteria criteria = dao.criteria(Process.class);
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
}
