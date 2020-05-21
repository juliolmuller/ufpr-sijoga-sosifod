package model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "tb_processo")

public class Processo {
    
    private Long id;
    private String juiz, advogado, promovente, promovido, fase;

    public Processo() {
    }
    
    //--------------------//
    //--Getter e Setters--//
    //--------------------//
    @Id
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getJuiz() {
        return this.juiz;
    }

    public void setJuiz(String juiz) {
        this.juiz = juiz;
    }
    
    public String getAdvogado() {
        return this.advogado;
    }

    public void setgetAdvogado(String advogado) {
        this.advogado = advogado;
    }
    
    public String getPromovente() {
        return this.promovente;
    }

    public void setgetPromovente(String promovente) {
        this.promovente = promovente;
    }
    
    public String getPromovido() {
        return this.promovido;
    }

    public void setgetPromovido(String promovido) {
        this.promovido = promovido;
    }
    
    public String getFase() {
        return this.fase;
    }

    public void setgetFase(String fase) {
        this.fase = fase;
    }
}
