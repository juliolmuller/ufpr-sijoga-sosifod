package com.lacussoft.sijoga.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "tb_anexos_processo")
public class ProcessPhaseAttachment implements Comparable<ProcessPhaseAttachment>, Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fase_processo", nullable = false)
    private ProcessPhase processPhase;

    @Column(name = "arquivo", nullable = false)
    private String fileName;

    @Column(name = "tipo", nullable = false)
    private String contentType;

    @ManyToOne
    @JoinColumn(name = "enviado_por", nullable = false)
    private User uploadedBy;

    @CreationTimestamp
    @Column(name = "enviado_em", nullable = false)
    private Date uploadedAt;

    @Override
    public int compareTo(ProcessPhaseAttachment attachment) {
        return attachment.uploadedAt.compareTo(uploadedAt);
    }

    public Long getId() {
        return id;
    }

    public ProcessPhase getProcessPhase() {
        return processPhase;
    }

    public void setProcessPhase(ProcessPhase processPhase) {
        this.processPhase = processPhase;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public User getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(User user) {
        uploadedBy = user;
    }

    public Date getUploadedAt() {
        return uploadedAt;
    }
}
