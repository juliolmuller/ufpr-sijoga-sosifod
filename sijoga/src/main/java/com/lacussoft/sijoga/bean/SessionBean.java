package com.lacussoft.sijoga.bean;

import com.lacussoft.sijoga.model.User;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@SessionScoped
public class SessionBean implements Serializable {

    private HttpSession session;
    private User user;

    @Inject
    private ExternalContext externalContext;
    
    @PostConstruct
    public void init() {
        session = (HttpSession) externalContext.getSession(false);
        user = (User) session.getAttribute("user");
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        this.session.setAttribute("user", user);
    }
}
