package com.lacussoft.sijoga;

import javax.annotation.PostConstruct;
import javax.annotation.security.DeclareRoles;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
//import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
//import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;

@DeclareRoles({ "JUZ", "ADV", "PAR" })
@BasicAuthenticationMechanismDefinition
//@CustomFormAuthenticationMechanismDefinition(
//    loginToContinue = @LoginToContinue(
//        loginPage = "/index.xhtml", 
//        errorPage = "/index.xhtml?error=true",
//        useForwardToLogin = false
//    )
//)
@FacesConfig(version = Version.JSF_2_3)
@ApplicationScoped
public class Config {

    @PostConstruct
    public void init() {
        System.out.println("||||||||| CONFIG ||||||||||");
    }
}
