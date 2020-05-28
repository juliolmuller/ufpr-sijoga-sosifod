package com.lacussoft.sijoga.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Parte extends User {

    @ManyToOne
    @JoinColumn(name = "advogado", nullable = true) // TODO: desativar 'nullable'
    private Advogado lawyer;

    // TODO: remover construtores
    public Parte() {}
    public Parte(String cpf, String password) {
        super.setCpf(cpf);
        super.setPassword(password);
        super.setName("Josnei");
        super.setEmail("josnei@email.com");
        super.setAddress(new Address(
            "80610150", "Rua das Flores", 753,
            "Apartamento 65", "Curitiba", "PR"
        ));
    }

    public Advogado getLawyer() {
        return lawyer;
    }

    public void setLawyer(Advogado lawyer) {
        this.lawyer = lawyer;
    }
}
