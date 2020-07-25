package com.lacussoft.sosifod.bean;

import com.lacussoft.sosifod.model.Intimacao;
import com.lacussoft.sosifod.model.User;
import com.lacussoft.sosifod.services.DaoFacade;
import com.lacussoft.utils.Converter;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.RequestParameterMap;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@RequestScoped
public class IntimacaoBean implements Serializable {

    private User currentUser;
    private Intimacao intimacao;
    private Date dataExecucao;
    private long intimacaoId;
    
    @Inject
    @RequestParameterMap
    private Map<String, String> querystring;

    @Inject
    private FacesContext context;

    @Inject
    private ExternalContext externalContext;

    @EJB
    private DaoFacade dao;

    @PostConstruct
    public void init() {
        HttpSession session = (HttpSession) externalContext.getSession(false);
        currentUser = (User) session.getAttribute("user");

        if (querystring.containsKey("id")) {
            intimacaoId = Long.parseLong(querystring.get("id"));
            intimacao = dao.find(intimacaoId, Intimacao.class);
        }
    }

    public String createIntimacao() {
        System.out.print("Modificando intimação ...");
        if (dataExecucao == null) {
                String msg = "Data da execução é requerida.";
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
            return null;
        }
        if (intimacao == null) {
                String msg = "Intimação não carregada.";
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
            return null;
        }
        intimacao.setData_execucao(dataExecucao);
        intimacao.setEstado("Executada");
        dao.save(intimacao);
        return "/intimacao/index?faces-redirect=true&id=" + intimacao.getId();
    }
    
    public Intimacao getIntimacao() {
        return intimacao;
    }

    public Date getDataExecucao() {
        return dataExecucao;
    }

    public void setDataExecucao(Date dataExecucao) {
        this.dataExecucao = dataExecucao;
    }

    public long getIntimacaoId() {
        return intimacaoId;
    }

    public void setIntimacaoId(long intimacaoId) {
        this.intimacaoId = intimacaoId;
    }
    
    
}
