package com.lacussoft.sijoga.security;

import com.lacussoft.sijoga.ejb.AuthenticationBean;
import com.lacussoft.sijoga.model.User;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

@ApplicationScoped
public class DatabaseIdentityStore implements IdentityStore {

    @EJB
    private AuthenticationBean auth;

    @Override
    public CredentialValidationResult validate(Credential credential) {

        UsernamePasswordCredential upCredential = (UsernamePasswordCredential) credential;
        String username = upCredential.getCaller();
        String password = upCredential.getPasswordAsString();

        User user = auth.validate(username, password);

        if (user == null) {
            return CredentialValidationResult.INVALID_RESULT;
        }

        return new CredentialValidationResult(username, Stream.of(user.getRole()).collect(Collectors.toSet()));
    }
}