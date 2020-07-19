package com.lacussoft.sosifod.model;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_intimacoes")
public class Intimacao implements Model {

    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_intimacao")
    private Date data_intimacao;
    
    @Column(name = "cpf_intimado")
    private String cpf_intimado;
    
    @Column(name = "endereco_intimado")
    private String endereco_intimado;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_execucao")
    private Date data_execucao;
    
    @Column(name = "estado")
    private String estado;
    
    @ManyToOne
    @JoinColumn(name = "oficial", nullable = false)
    private User oficial;
    
    @Column(name = "processo")
    private String processo;
    
    public Intimacao() {}

    public Intimacao(Date data_intimacao,  String cpf_intimado, String endereco_intimado, Date data_execucao, String estado, User oficial, String processo) {
        this.data_intimacao = data_intimacao;
        this.cpf_intimado = cpf_intimado;
        this.endereco_intimado = endereco_intimado;
        this.data_execucao = data_execucao;
        this.estado = estado;
        this.oficial = oficial;
        this.processo = processo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData_intimacao() {
        return data_intimacao;
    }

    public void setData_intimacao(Date data_intimacao) {
        this.data_intimacao = data_intimacao;
    }

    public String getCpf_intimado() {
        return cpf_intimado;
    }

    public void setCpf_intimado(String cpf_intimado) {
        this.cpf_intimado = cpf_intimado;
    }

    public String getEndereco_intimado() {
        return endereco_intimado;
    }

    public void setEndereco_intimado(String endereco_intimado) {
        this.endereco_intimado = endereco_intimado;
    }

    public Date getData_execucao() {
        return data_execucao;
    }

    public void setData_execucao(Date data_execucao) {
        this.data_execucao = data_execucao;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public User getOficial() {
        return oficial;
    }

    public void setOficial(User oficial) {
        this.oficial = oficial;
    }

    public String getProcesso() {
        return processo;
    }

    public void setProcesso(String processo) {
        this.processo = processo;
    }

   
}
