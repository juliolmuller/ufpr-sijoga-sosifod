package com.lacussoft.sijoga.bean;

import com.lacussoft.sijoga.model.Process;
import com.lacussoft.sijoga.model.User;
import com.lacussoft.sijoga.services.DaoFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@RequestScoped
public class IndexBean implements Serializable {

    @EJB
    private DaoFacade dao;

    @Inject
    private ExternalContext externalContext;

    public List<Process> getProcesses() {
        HttpSession httpSession = (HttpSession) externalContext.getSession(false);
        User user = (User) httpSession.getAttribute("user");

        return dao.getProcessesFor(user);
    }
}
