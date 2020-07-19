package com.lacussoft.sosifod.model;

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
public class User implements Model {

     private static final String HASH_PREFIX = "@SOSIFOD::";
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(nullable = false, length = 11)
    private String cpf;
    
    @Column(nullable = false, length = 100)
    private String name;
    
    @Column(nullable = false, length = 100)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private boolean isAdmin;
    
    public User() {}

    public User(String cpf, String name, String email, String password, boolean isAdmin) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
    }
    
    public static String hashPassword(String plainPassword) {
        if (plainPassword.startsWith(HASH_PREFIX)) {
            return plainPassword;
        }
        return HASH_PREFIX + SecurityUtil.encryptPassword(plainPassword);
    }

    public void hashPassword() {
        password = hashPassword(password);
    }

    public Long getId() {
        return this.id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}