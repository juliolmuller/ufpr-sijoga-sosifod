package com.lacussoft.sijoga.model;

import com.lacussoft.utils.SecurityUtil;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_usuarios")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "perfil")
public abstract class User implements Serializable {

    private static final String HASH_PREFIX = "@SIJOGA::";

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, updatable = false, nullable = false, length = 11)
    private String cpf;

    @Column(name = "senha", nullable = false, length = 41)
    private String password;

    @Column(name = "nome", nullable = false, length = 120)
    private String name;

    @Column(nullable = false)
    private String email;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_nasc", nullable = false)
    private Date dateOfBirth;

    @Embedded
    private Address address = new Address();

    public User() {}

    public User(String cpf, String password, String name, Date dateOfBirth, String email, Address address) {
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.address = address;
        this.email = email;
        this.name = name;
        this.cpf = cpf;
    }

    public static User create(AccessRole role) {
        try {
            return (User) role.asClass().newInstance();
        } catch (IllegalAccessException | InstantiationException | NullPointerException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String hashPassword(String plainPassword) {
        if (!plainPassword.startsWith(HASH_PREFIX)) {
            return HASH_PREFIX + SecurityUtil.encryptPassword(plainPassword);
        }
        return plainPassword;
    }

    public void hashPassword() {
        password = hashPassword(password);
    }

    public String getRole() {
        String[] clasName = getClass().getName().split("\\.");
        return clasName[clasName.length - 1];
    }

    public Long getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPassword() {
        if (!password.startsWith(HASH_PREFIX)) {
            int hashLength = HASH_PREFIX.length();
            return password.substring(hashLength);
        }
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date date) {
        this.dateOfBirth = date;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
