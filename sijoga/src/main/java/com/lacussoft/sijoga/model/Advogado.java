package com.lacussoft.sijoga.model;

import javax.persistence.Entity;

@Entity
public class Advogado extends User {

    // TODO: remover construtores
    public Advogado() {}
    public Advogado(String cpf, String password) {
        super.setCpf(cpf);
        super.setPassword(password);
        super.setName("Josnei");
        super.setEmail("josnei@email.com");
        super.setAddress(new Address(
            "80610150", "Rua das Flores", 753,
            "Apartamento 65", "Curitiba", "PR"
        ));
    }
}
