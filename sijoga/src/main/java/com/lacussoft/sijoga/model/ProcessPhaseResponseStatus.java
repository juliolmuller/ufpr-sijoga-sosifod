package com.lacussoft.sijoga.model;

public enum ProcessPhaseResponseStatus {
    ACCEPTED("Aceita"),
    REJECTED("Negada"),
    SUMMONING("Em Intimação"),
    SUMMONED("Intimado"),
    CLOSED("Encerrado");

    private final String status;

    private ProcessPhaseResponseStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return getStatus();
    }
}
