package com.lacussoft.sosifod.model;

import java.io.Serializable;

public class ProcessoSobIntimacao implements Serializable {
    
    private Long idProcesso;
    private String descProcesso;
    
    private Long idFase;
    private String tituloFase;
    private String descFase;

    private PessoaProcesso juiz;
    private PessoaProcesso promovente;
    private PessoaProcesso intimado;
    
    public ProcessoSobIntimacao() {}

    public Long getIdProcesso() {
        return idProcesso;
    }

    public void setIdProcesso(Long idProcesso) {
        this.idProcesso = idProcesso;
    }

    public String getDescProcesso() {
        return descProcesso;
    }

    public void setDescProcesso(String descProcesso) {
        this.descProcesso = descProcesso;
    }

    public Long getIdFase() {
        return idFase;
    }

    public void setIdFase(Long idFase) {
        this.idFase = idFase;
    }

    public String getTituloFase() {
        return tituloFase;
    }

    public void setTituloFase(String tituloFase) {
        this.tituloFase = tituloFase;
    }

    public String getDescFase() {
        return descFase;
    }

    public void setDescFase(String descFase) {
        this.descFase = descFase;
    }

    public PessoaProcesso getJuiz() {
        return juiz;
    }

    public void setJuiz(PessoaProcesso juiz) {
        this.juiz = juiz;
    }

    public PessoaProcesso getPromovente() {
        return promovente;
    }

    public void setPromovente(PessoaProcesso promovente) {
        this.promovente = promovente;
    }

    public PessoaProcesso getIntimado() {
        return intimado;
    }

    public void setIntimado(PessoaProcesso intimado) {
        this.intimado = intimado;
    }
}
