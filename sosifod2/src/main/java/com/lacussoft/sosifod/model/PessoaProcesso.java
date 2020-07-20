package com.lacussoft.sosifod.model;

import java.io.Serializable;

public class PessoaProcesso implements Serializable {
    
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String perfil;

    // Para PessoaProcesso com perfil = "Advogado"
    private String oab;

    // Para PessoaProcesso com perfil = "Parte"
    private PessoaProcesso advogado;

    public PessoaProcesso() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getOab() {
        return oab;
    }

    public void setOab(String oab) {
        this.oab = oab;
    }

    public PessoaProcesso getAdvogado() {
        return advogado;
    }

    public void setAdvogado(PessoaProcesso advogado) {
        this.advogado = advogado;
    }
}
