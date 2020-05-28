package com.lacussoft.sijoga;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
//import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
//import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;

@ApplicationScoped
@FacesConfig(version = Version.JSF_2_3)
@BasicAuthenticationMechanismDefinition
//@CustomFormAuthenticationMechanismDefinition(
//    loginToContinue = @LoginToContinue(
//        loginPage = "/index.xhtml", 
//        errorPage = "/index.xhtml?error=true",
//        useForwardToLogin = false
//    )
//)
public class Config {}
