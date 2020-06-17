package com.lacussoft.sijoga.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class ProcessPhaseResponse implements Serializable {

    @Enumerated(EnumType.STRING)
    private ProcessPhaseResponseStatus status;

    @Column(name = "resposta")
    private String description;

    public ProcessPhaseResponse() {}

    public ProcessPhaseResponse(ProcessPhaseResponseStatus status, String description) {
        this.description = description;
        this.status = status;
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
