package com.lacussoft.sosifod.bean;

import com.lacussoft.sosifod.security.AuthenticationBean;
import java.io.IOException;
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

    public void login() {
        AuthenticationStatus status = auth.attempt(cpf, password);
        try {
            if (status.equals(AuthenticationStatus.SUCCESS)) {
                externalContext.redirect("/sosifod/index.html");
            }
            externalContext.redirect("/sosifod/login.xhtml?error=true");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
