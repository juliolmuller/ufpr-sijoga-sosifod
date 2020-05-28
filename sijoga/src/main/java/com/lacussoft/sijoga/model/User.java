package com.lacussoft.sijoga.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "tb_usuarios")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "perfil")
public abstract class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, updatable = false, nullable = false, length = 11)
    private String cpf;

    @Column(name = "senha", nullable = false, length = 32)
    private String password;

    @Column(name = "nome", nullable = false, length = 120)
    private String name;

    @Column(nullable = false)
    private String email;

    @Embedded
    private Address address;

    public static User getInstanceOf(AccessRole role) {
        try {
            return (User) role.asClass().newInstance();
        } catch (IllegalAccessException | InstantiationException | NullPointerException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String getRole() {
        String[] clasName = getClass().getName().split("\\.");
        return clasName[clasName.length - 1];
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
