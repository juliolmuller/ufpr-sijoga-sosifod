package com.lacussoft.sijoga.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Advogado extends User {

    @Column(name = "insc_oab", length = 10, unique = true)
    private String oab;

    // TODO: remover construtores
    public Advogado() {}
    public Advogado(String cpf, String password) {
        super.setCpf(cpf);
        super.setPassword(password);
        super.setName("Josnei");
        super.setEmail("josnei@email.com");
        super.setDateOfBirth(new Date());
        super.setAddress(new Address(
            "80610150", "Rua das Flores", 753,
            "Apartamento 65", "Curitiba", "PR"
        ));
        oab = "12345";
    }

    public String getOab() {
        return oab;
    }

    public void setOab(String oab) {
        this.oab = oab;
    }
}
