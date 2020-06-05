package com.lacussoft.sijoga.bean;

import com.lacussoft.sijoga.security.AuthenticationBean;
import com.lacussoft.utils.Converter;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;

@Named
@RequestScoped
public class LoginBean implements Serializable {

    private String cpf;
    private String password;

    @EJB
    private AuthenticationBean auth;

    @Inject
    private ExternalContext externalContext;

    public String login() {
        AuthenticationStatus status = auth.attempt(cpf, password);
        if (status.equals(AuthenticationStatus.SUCCESS)) {
            return "index?faces-redirect=true";
        }

        return "login?error=true";
    }

    public String logout() {
        auth.logout();
        return "index?faces-redirect=true";
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = Converter.removeNonDigits(cpf);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
