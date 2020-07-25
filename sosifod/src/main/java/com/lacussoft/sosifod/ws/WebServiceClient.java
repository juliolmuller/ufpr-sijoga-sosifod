package com.lacussoft.sosifod.ws;

import com.lacussoft.sosifod.model.ProcessoSobIntimacao;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
public class WebServiceClient implements Serializable {

    private final static String BASE_URI = "http://localhost:8080/sijoga/ws/subpoenas";

    private Client httpClient;

    @PostConstruct
    public void init() {
        httpClient = ClientBuilder.newClient();
    }

    @PreDestroy
    public void destroy() {
        if (httpClient != null) {
            httpClient.close();
        }
    }

    public List<ProcessoSobIntimacao> fetchAll() {
        Response response = httpClient
            .target(BASE_URI)
            .request(MediaType.APPLICATION_JSON)
            .get();

        return response.readEntity(new GenericType<List<ProcessoSobIntimacao>>() {});
    }

    public Response execute(Long idFase, String msg) {
        String comment = buildMessage(msg).toString();

        return httpClient
            .target(BASE_URI)
            .path(String.valueOf(idFase))
            .request(MediaType.APPLICATION_JSON)
            .post(Entity.json(comment));
    }

    private JsonObject buildMessage(String msg) {
        return Json.createObjectBuilder().add("resposta", msg).build();
    }
}
