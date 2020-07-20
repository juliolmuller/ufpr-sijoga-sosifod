package com.lacussoft.sosifod.bean;

import com.lacussoft.sosifod.model.Intimacao;
import com.lacussoft.sosifod.model.PessoaProcesso;
import com.lacussoft.sosifod.model.ProcessoSobIntimacao;
import com.lacussoft.sosifod.model.User;
import com.lacussoft.sosifod.services.DaoFacade;
import com.lacussoft.sosifod.ws.WebServiceClient;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@RequestScoped
public class IndexBean implements Serializable {

    @EJB
    private WebServiceClient wsClient;

    @EJB
    private DaoFacade dao;

    @Inject
    private ExternalContext externalContext;

    public List<Intimacao> getIntimacoes() {
        HttpSession httpSession = (HttpSession) externalContext.getSession(false);
        User user = (User) httpSession.getAttribute("user");

        List<ProcessoSobIntimacao> json = wsClient.fetchAll();
        json.forEach(p -> System.out.println("idProcesso => " + p.getIdProcesso() + "; idFase => " + p.getIdFase()));
        
        return dao.getIntimacoesFor(user);
    }
}
