package com.programacion.distribuida.authors.rest;

import com.programacion.distribuida.authors.db.Author;
import com.programacion.distribuida.authors.repo.AuthorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
//COmponente CDI
@ApplicationScoped
@Path("/authors")
public class AuthorRest {

    @Inject
    AuthorRepository authorRepository;

    @GET
    public List<Author> findAll() {
        return authorRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        /*var obj = authorRepository.findByIdOptional(id);

        if (obj.isPresent()) {
            return Response.ok(obj.get()).build();
        }else  {
            return Response.status(Response.Status.NOT_FOUND).build();
        }*/

        return authorRepository.findByIdOptional(id)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

}
