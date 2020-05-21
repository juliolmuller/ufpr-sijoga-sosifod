package model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Temporal;

@Entity
@Table(name = "tb_fase")

public class Fase {
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datahoraCriacao;
    @Id
    private Long id;
    private Long processo;
    private String nome, descricao, tipo, estado, advogado;

    public Fase() {
    }
    
    
    //--------------------//
    //--Getter e Setters--//
    //--------------------//
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Date getDataHoraCriacao() {
        return this.datahoraCriacao;
    }

    public void setDataHoraCriacao(Date datahoraCriacao) {
        this.datahoraCriacao = datahoraCriacao;
    }
    
    public void setProcesso(Long processo) {
        this.processo = processo;
    }

    public Long getProcesso() {
        return this.processo;
    }
    
    public String getDescricao() {
        return this.descricao;
    }

    public void setgetDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getEstado() {
        return this.estado;
    }

    public void setgetEstado(String estado) {
        this.estado = estado;
    }
    
    public String getAdvogado() {
        return this.advogado;
    }

    public void setgetAdvogado(String advogado) {
        this.advogado = advogado;
    }
}
