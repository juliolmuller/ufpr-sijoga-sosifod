package com.lacussoft.sijoga.bean;

import com.lacussoft.sijoga.model.Parte;
import com.lacussoft.sijoga.model.Process;
import com.lacussoft.sijoga.model.User;
import com.lacussoft.sijoga.services.DaoFacade;
import com.lacussoft.utils.Converter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.RequestParameterMap;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@RequestScoped
public class ProcessBean {

    private String description;
    private String promoterCpf;
    private String promotedCpf;
    private User currentUser;
    private Process process;

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
            Long id = Long.parseLong(querystring.get("id"));
            process = dao.find(id, Process.class);
        }
    }

    public String createProcess() {
        if (promoterCpf == null || promotedCpf == null) {
            if (promoterCpf == null) {
                String msg = "CPF do Promovente é requerido.";
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
            }
            if (promotedCpf == null) {
                String msg = "CPF do Promovido é requerido.";
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
            }
            return null;
        }
        Parte promoter = getPromoter();
        Parte promoted = getPromoted();
        if (promoter == null || promoted == null) {
            return null;
        }
        if (promoter.getId().equals(promoted.getId())) {
            String msg = "Promovente e Promovido não podem ser a mesma pessoa.";
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
            return null;
        }
        Process process = new Process();
        process.setDescription(description);
        process.setPromoter(promoter);
        process.setPromoted(promoted);
        process.setCreatedBy(currentUser);
        process.setUpdatedBy(currentUser);
        process.setJudge(dao.getRandomJudge());
        dao.save(process);
        return "/processo/index?faces-redirect=true&id=" + process.getId();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPromoterCpf() {
        return promoterCpf;
    }

    public void setPromoterCpf(String cpf) {
        promoterCpf = cpf;
    }

    public String getPromotedCpf() {
        return promotedCpf;
    }

    public void setPromotedCpf(String cpf) {
        promotedCpf = cpf;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public Map<String, String> getClients() {
        List<String> cpfs = dao
            .createQuery("SELECT p.cpf FROM Parte p WHERE p.lawyer.id = :lawyerId")
            .setLong("lawyerId", currentUser.getId())
            .list();
        Map<String, String> clients = new LinkedHashMap<>();
        cpfs.forEach(c -> clients.put(Converter.toCpf(c), c));
        return clients;
    }

    public Parte getPromoter() {
        if (promoterCpf != null) {
            System.out.println("Promovente: " + promoterCpf);
            User promoter = dao.findUserByCpf(promoterCpf);
            if (promoter == null) {
                String msg = "CPF \"" + promoterCpf + "\" não cadastrado. Cadastre-o antes de criar o processo.";
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg));
            }
            if (!(promoter instanceof Parte)) {
                String msg = "CPF \"" + promoterCpf + "\" pertence a um Juiz ou Advogado.";
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
                return null;
            }
            return (Parte) promoter;
        }
        return null;
    }

    public Parte getPromoted() {
        if (promotedCpf != null) {
            System.out.println("Promovido: " + promotedCpf);
            User promoted = dao.findUserByCpf(promotedCpf);
            if (promoted == null) {
                String msg = "CPF \"" + promotedCpf + "\" não cadastrado. Cadastre-o antes de criar o processo.";
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg));
            }
            if (!(promoted instanceof Parte)) {
                String msg = "CPF \"" + promotedCpf + "\" pertence a um Juiz ou Advogado.";
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
                return null;
            }
            return (Parte) promoted;
        }
        return null;
    }
}
