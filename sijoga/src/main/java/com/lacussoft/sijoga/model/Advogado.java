package com.lacussoft.sijoga.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Advogado extends User {

    @Column(name = "insc_oab", length = 10, unique = true)
    private String oab;

    public Advogado() {
        super();
    }

    public Advogado(String cpf, String password, String name, Date dateOfBirth, String email, Address address, String oab) {
        super(cpf, password, name, dateOfBirth, email, address);
        this.oab = oab;
    }

    public String getOab() {
        return oab;
    }

    public void setOab(String oab) {
        this.oab = oab;
    }
}
