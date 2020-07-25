package com.lacussoft.sosifod.security;

import com.lacussoft.sosifod.model.User;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import javax.servlet.http.HttpSession;

@ApplicationScoped
public class DatabaseIdentityStore implements IdentityStore {

    @EJB
    private AuthenticationBean auth;

    @Inject
    private ExternalContext externalContext;

    @Override
    public CredentialValidationResult validate(Credential credential) {

        UsernamePasswordCredential upCredential = (UsernamePasswordCredential) credential;
        String username = upCredential.getCaller();
        String password = upCredential.getPasswordAsString();

        User user = auth.validate(username, password);
        if (user == null) {
            return CredentialValidationResult.INVALID_RESULT;
        }

        HttpSession session = (HttpSession) externalContext.getSession(true);
        session.setAttribute("user", user);

        return new CredentialValidationResult(username);
    }
}
