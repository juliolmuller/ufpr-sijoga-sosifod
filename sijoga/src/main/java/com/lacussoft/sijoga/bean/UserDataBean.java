package com.lacussoft.sijoga.bean;

import com.lacussoft.sijoga.model.User;
import com.lacussoft.sijoga.services.DaoFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named("userData")
@RequestScoped
public class UserDataBean implements Serializable {

    private User currentUser;
    private String currentPassword;
    private String newPassword1;
    private String newPassword2;

    HttpSession session;

    @EJB
    private DaoFacade dao;

    @Inject
    private FacesContext context;

    @Inject
    private ExternalContext externalContext;

    @Inject
    private Flash flash;

    @PostConstruct
    public void init() {
        session = (HttpSession) externalContext.getSession(false);
        currentUser = (User) session.getAttribute("user");
        currentUser = dao.find(currentUser.getId(), User.class);
    }

    public String updateData() {
        dao.save(currentUser);
        session.setAttribute("user", currentUser);

        String successMsg = "Dados atualizados com sucesso.";
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, successMsg, successMsg));
        flash.setKeepMessages(true);

        return "meus-dados?faces-redirect=true";
    }

    public String updatePassword() {
        currentUser.setPassword(newPassword1);

        dao.save(currentUser);
        session.setAttribute("user", currentUser);

        String successMsg = "Dados atualizados com sucesso.";
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, successMsg, successMsg));
        flash.setKeepMessages(true);

        return "meus-dados?faces-redirect=true";
    }

    public void validatePassword(FacesContext context, UIComponent ui, Object value) throws ValidatorException {
        if (value == null) return;

        String hash1 = currentUser.getPassword();
        String hash2 = User.hashPassword((String) value);

        if (!hash1.equals(hash2)) {
            String msg = "Senha atual incorreta.";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword1() {
        return newPassword1;
    }

    public void setNewPassword1(String newPassword1) {
        this.newPassword1 = newPassword1;
    }

    public String getNewPassword2() {
        return newPassword2;
    }

    public void setNewPassword2(String newPassword2) {
        this.newPassword2 = newPassword2;
    }
}
