package com.lacussoft.sijoga.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Parte extends User {

    @ManyToOne
    @JoinColumn(name = "advogado")
    private Advogado lawyer;

    public Parte() {
        super();
    }

    public Parte(String cpf, String password, String name, Date dateOfBirth, String email, Address address, Advogado lawyer) {
        super(cpf, password, name, dateOfBirth, email, address);
        this.lawyer = lawyer;
    }

    public Advogado getLawyer() {
        return lawyer;
    }

    public void setLawyer(Advogado lawyer) {
        this.lawyer = lawyer;
    }
}
