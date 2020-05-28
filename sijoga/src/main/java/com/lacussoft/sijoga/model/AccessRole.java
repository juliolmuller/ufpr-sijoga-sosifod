package com.lacussoft.sijoga.model;

import javax.annotation.security.DeclareRoles;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@DeclareRoles({ "Juiz", "Advogado", "Parte" })
public enum AccessRole {
    JUIZ(Juiz.class),
    ADVOGADO(Advogado.class),
    PARTE(Parte.class);

    private final Class role;

    private AccessRole(Class role) {
        this.role = role;
    }

    public Class asClass() {
        return role;
    }

    public String getName() {
        String[] className = role.getName().split("\\.");
        return className[className.length - 1];
    }
}
