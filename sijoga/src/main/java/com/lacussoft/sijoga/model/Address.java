package com.lacussoft.sijoga.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address implements Serializable {

    @Column(name = "cep", nullable = false, length = 8)
    private String zipCode;
    
    @Column(name = "rua", nullable = false, length = 100)
    private String street;
    
    @Column(name = "numero", nullable = false)
    private Integer number;
    
    @Column(name = "complemento", length = 30)
    private String complement;
    
    @Column(name = "cidade", nullable = false, length = 60)
    private String city;
    
    @Column(name = "uf", nullable = false, length = 2)
    private String state;

    // TODO: remover construtores
    public Address() {}
    public Address(String zipCode, String street, Integer number, String complement, String city, String state) {
        this.zipCode = zipCode;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.city = city;
        this.state = state;
    }
    
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
