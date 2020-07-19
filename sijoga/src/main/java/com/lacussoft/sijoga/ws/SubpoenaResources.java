package com.lacussoft.sijoga.ws;

import com.lacussoft.sijoga.model.Advogado;
import com.lacussoft.sijoga.model.Parte;
import com.lacussoft.sijoga.model.Process;
import com.lacussoft.sijoga.model.ProcessPhase;
import com.lacussoft.sijoga.model.ProcessPhaseResponse;
import com.lacussoft.sijoga.model.ProcessPhaseResponseStatus;
import com.lacussoft.sijoga.model.User;
import com.lacussoft.sijoga.services.DaoFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("subpoenas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SubpoenaResources {

    @EJB
    private DaoFacade dao;

    @POST
    @Path("/{phaseId}")
    public Response update(SubpoenaResponseBean comment, @PathParam("phaseId") Long phaseId) {
        ProcessPhase phase = dao.find(phaseId, ProcessPhase.class);
        ProcessPhaseResponse response = phase.getResponse();
        response.setStatus(ProcessPhaseResponseStatus.SUMMONED);
        response.setDescription(comment.resposta);

        dao.save(phase);

        return Response.ok().build();
    }

    @GET
    public Response index() {
        JsonArrayBuilder json = Json.createArrayBuilder();
        List<ProcessPhase> phases = dao.createQuery("FROM ProcessPhase").list();

        phases.forEach(phase -> {
            ProcessPhaseResponse resp = phase.getResponse();
            if (resp != null && resp.getStatus().equals(ProcessPhaseResponseStatus.SUMMONING)) {
                json.add(generateJson(phase));
            }
        });

        return Response.ok(json.build().toString()).build();
    }

    private JsonValue generateJson(ProcessPhase phase) {
        Process process = phase.getProcess();
        JsonObjectBuilder json = Json.createObjectBuilder()
            .add("id_processo", process.getId())
            .add("desc_processo", process.getDescription())
            .add("data_processo", process.getCreatedAt().toString())
            .add("id_fase", phase.getId())
            .add("titulo_fase", phase.getTitle())
            .add("desc_fase", phase.getDescription())
            .add("juiz", generateJson(process.getJudge()))
            .add("promovente", generateJson(process.getPromoter()))
            .add("intimado", generateJson(process.getPromoted()));
        return json.build();
    }

    private JsonValue generateJson(User user) {
        if (user == null) {
            return JsonValue.NULL;
        }

        JsonObjectBuilder json = Json.createObjectBuilder()
            .add("id", user.getId())
            .add("nome", user.getName())
            .add("cpf", user.getCpf())
            .add("email", user.getRole())
            .add("perfil", user.getRole());

        if (user instanceof Advogado) {
            Advogado lawyer = (Advogado) user;
            json.add("oab", lawyer.getOab());
        } else if (user instanceof Parte) {
            Parte party = (Parte) user;
            json.add("advogado", generateJson(party.getLawyer()));
        }

        return json.build();
    }
}
