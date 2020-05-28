package com.lacussoft.sijoga.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_processos")
public class Process implements Serializable {
    
    @Id
    private Long id;
}
