package com.lacussoft.sosifod;

import javax.annotation.security.DeclareRoles;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
//import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
//import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;

@DeclareRoles({ "OFJ", "ADM" })
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
public class Config {}
