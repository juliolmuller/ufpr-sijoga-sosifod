package com.lacussoft.sosifod.ws;

import com.lacussoft.sosifod.model.User;
import com.lacussoft.sosifod.services.DaoFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsersResources {

    @EJB
    private DaoFacade dao;

    @GET
    public List<User> index() {
        return dao.createQuery("FROM User").list();
    }

    @GET
    @Path("/{userId}")
    public User find(@PathParam("userId") long id) {
        return dao.find(id, User.class);
    }

    // TODO: not working
    @POST
    public User store(User user) {
        dao.save(user);
        return user;
    }

    // TODO: not working
    @PUT
    @Path("/{userId}")
    public User update(User data, @PathParam("userId") long id) {
        User user = dao.find(id, User.class);
        user.setPassword(data.getPassword());
        user.setEmail(data.getEmail());
        user.setName(data.getName());
        user.setCpf(data.getCpf());
        dao.save(user);
        return user;
    }

    @DELETE
    @Path("/{userId}")
    public User destroy(@PathParam("userId") long id) {
        User user = dao.find(id, User.class);
        dao.destroy(user);
        return user;
    }
}
