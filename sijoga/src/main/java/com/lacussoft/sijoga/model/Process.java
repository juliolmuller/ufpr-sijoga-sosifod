package com.lacussoft.sijoga.model;

import java.io.Serializable;
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

@Entity
@Table(name = "tb_processos")
public class Process implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "descricao")
    private String description;

    @ManyToOne
    @JoinColumn(name = "juiz", nullable = false)
    private Juiz judge;

    @ManyToOne
    @JoinColumn(name = "promovente", nullable = false)
    private Parte promoter;

    @ManyToOne
    @JoinColumn(name = "promovido", nullable = false)
    private Parte promoted;

    @OneToMany(mappedBy = "process")
    private Set<ProcessPhase> phases = new TreeSet<>();

    @ManyToOne
    @JoinColumn(name = "criado_por", nullable = false)
    private User createdBy;

    @CreationTimestamp
    @Column(name = "criado_em", nullable = false)
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "alterado_por", nullable = false)
    private User updatedBy;

    @UpdateTimestamp
    @Column(name = "alterado_em", nullable = false)
    private Date updatedAt;

    public Process() {}

    public Process(String description, Juiz judge, Parte promoter, Parte promoted, User createdBy, User updatedBy) {
        this.description = description;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.promoter = promoter;
        this.promoted = promoted;
        this.judge = judge;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Juiz getJudge() {
        return judge;
    }

    public void setJudge(Juiz judge) {
        this.judge = judge;
    }

    public Parte getPromoter() {
        return promoter;
    }

    public void setPromoter(Parte party) {
        promoter = party;
    }

    public Parte getPromoted() {
        return promoted;
    }

    public void setPromoted(Parte party) {
        promoted = party;
    }

    public Set<ProcessPhase> getPhases() {
        return phases;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User user) {
        createdBy = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User user) {
        updatedBy = user;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
