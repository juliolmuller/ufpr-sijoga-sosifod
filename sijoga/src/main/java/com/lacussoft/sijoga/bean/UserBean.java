package com.lacussoft.sijoga.bean;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.SecurityContext;

@Named
@RequestScoped
public class UserBean implements Serializable {

    @Inject
    private SecurityContext securityContext;

    public String getName() {
        return securityContext.getCallerPrincipal().getName();
    }
}
