package com.lacussoft.sijoga.model;

import java.util.Date;
import javax.persistence.Entity;

@Entity
public class Juiz extends User {

    public Juiz() {
        super();
    }

    public Juiz(String cpf, String password, String name, Date dateOfBirth, String email, Address address) {
        super(cpf, password, name, dateOfBirth, email, address);
    }
}
