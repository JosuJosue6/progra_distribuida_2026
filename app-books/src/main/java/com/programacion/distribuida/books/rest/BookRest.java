package com.programacion.distribuida.books.rest;

import com.programacion.distribuida.books.clients.AuthorRestClient;
import com.programacion.distribuida.books.dto.BookDto;
import com.programacion.distribuida.books.repo.BookRepository;
import com.programacion.distribuida.books.servicios.MapperService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.modelmapper.ModelMapper;

import java.util.List;

@Path("/books")
@Transactional
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookRest {

    @Inject
    BookRepository bookRepository;

    @Inject
    ModelMapper mapper;

    AuthorRestClient client;

    @PostConstruct
    void init()
    {
        var authorsServer = "http://localhost:8070";
        client = RestClientBuilder.newBuilder()
                .baseUri(authorsServer)
                .build(AuthorRestClient.class);
    }

    @GET
    @Path("/{isbn}")
    public Response findByIsbn(@PathParam("isbn") String isbn)
    {

        /*var authorServer ="http://localhost:8070";

        var client = RestClientBuilder.newBuilder()
                .baseUri(authorServer)
                .build(AuthorRestClient.class);*/
        return bookRepository.findByIdOptional(isbn)
                .map(book -> {
                    var authors = client.findByBook(isbn);
                    var dto = new BookDto();
                    mapper.map(book, dto);
                    return Response.ok(dto).build();
                })
                .orElse(Response.status(Response.Status.NOT_FOUND).build());

        /*var obj = bookRepository.findByIdOptional(isbn);



        if(obj.isEmpty())
        {
            return Response.status(Response.Status.NOT_FOUND).build();
        }


        BookDto ret = new BookDto();
       // mapper.map(obj.get(),ret);
        ret.setIsbn(obj.get().getIsbn());
        ret.setTitle(obj.get().getTitle());
        ret.setPrice(obj.get().getPrice());
        if(obj.get().getInventory() != null){
            ret.setInventorySold(obj.get().getInventory().getSold());
            ret.setInventorySupplied(obj.get().getInventory().getSupplied());
        }
        ret.setAuthors(
                List.of()
        );

        return Response.ok(ret).build();*/
    }

    @GET
    public List<BookDto> findAll(){
        return bookRepository.streamAll()
                .map(book ->{
                    var dto = new BookDto();
                    mapper.map(book,dto);
                    return dto;
                })
                .map(book -> {
                    var authors = client.findByBook(book.getIsbn());
                    book.setAuthors(authors);
                    return book;
                }).toList();
    }
}
