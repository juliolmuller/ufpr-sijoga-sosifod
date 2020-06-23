package com.lacussoft.sijoga.bean;

import com.lacussoft.sijoga.model.AccessRole;
import com.lacussoft.sijoga.model.Address;
import com.lacussoft.sijoga.model.Advogado;
import com.lacussoft.sijoga.model.User;
import com.lacussoft.sijoga.services.DaoFacade;
import com.lacussoft.utils.Converter;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;

@Named("userForm")
@RequestScoped
public class UserFormBean implements Serializable {

    private AccessRole role;
    private String cpf;
    private String name;
    private String email;
    private String oab;
    private Date dateOfBirth;
    private Address address = new Address();
    private String password1;
    private String password2;

    @EJB
    private DaoFacade dao;

    @Inject
    private FacesContext context;

    @Inject
    private Flash flash;

    public String create() {
        User user = User.create(role);
        user.setCpf(cpf);
        user.setName(name);
        user.setEmail(email);
        user.setAddress(address);
        user.setPassword(password1);
        user.setDateOfBirth(dateOfBirth);
        if (user instanceof Advogado) {
            ((Advogado) user).setOab(oab);
        }

        dao.save(user);

        String successMsg = "Usuário criado com sucesso.";
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, successMsg, successMsg));
        flash.setKeepMessages(true);

        return "login?faces-redirect=true";
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = Converter.removeNonDigits(cpf);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOab() {
        return oab;
    }

    public void setOab(String oab) {
        this.oab = oab;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date date) {
        dateOfBirth = date;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public AccessRole getRole() {
        return role;
    }

    public void setRole(AccessRole role) {
        this.role = role;
    }
}
