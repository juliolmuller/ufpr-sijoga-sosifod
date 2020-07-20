package com.lacussoft.sosifod.bean;

import com.lacussoft.sosifod.model.ProcessoSobIntimacao;
import com.lacussoft.sosifod.ws.WebServiceClient;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Response;

@Named
@RequestScoped
public class IndexBean implements Serializable {

    @EJB
    private WebServiceClient wsClient;

    @Inject
    private FacesContext context;

    public List<ProcessoSobIntimacao> getIntimacoes() {
        return wsClient.fetchAll();
    }

    public void execute(Long idFase) {
        Response response = wsClient.execute(idFase, "Intimação realizada com sucesso!");
        if (response.getStatus() == 200) {
            String msg = "Intimação executada com sucesso.";
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        } else {
            String msg = "Execução da intimação falhou (miseravelmente).";
            System.out.println(response);
            System.out.println(response.getStatus() + " => " + response.getStatusInfo());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
        }
    }
}
