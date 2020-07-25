package com.lacussoft.sosifod.bean;

import com.lacussoft.sosifod.model.User;
import com.lacussoft.sosifod.services.DaoFacade;
import com.lacussoft.utils.Converter;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;

@Named("userForm")
@RequestScoped
public class UserFormBean implements Serializable {
    private String cpf;
    private String name;
    private String email;
    private String password1;
    private String password2;

    @EJB
    private DaoFacade dao;

    @Inject
    private FacesContext context;

    @Inject
    private Flash flash;

    public String create() {
        User user = new User();
        user.setCpf(cpf);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password1);
        dao.save(user);

        String successMsg = "Oficial de Justiça adicionado com sucesso.";
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, successMsg, successMsg));
        flash.setKeepMessages(true);

        return "login?faces-redirect=true";
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = Converter.removeNonDigits(cpf);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
}
