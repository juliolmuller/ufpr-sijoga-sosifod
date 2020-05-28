package com.lacussoft.sijoga.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_respostas_processo")
public class ProcessPhaseResponse implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "response")
    private ProcessPhase processPhase;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProcessPhaseResponseStatus status;

    @Column(name = "descricao")
    private String description;

    public Long getId() {
        return id;
    }

    public ProcessPhase getProcessPhase() {
        return processPhase;
    }

    public void setProcessPhase(ProcessPhase processPhase) {
        this.processPhase = processPhase;
    }

    public ProcessPhaseResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ProcessPhaseResponseStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
