package br.com.pordotom.service;

import br.com.pordotom.service.impl.BookNotFoundException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

public interface BookService {

    @GET
    @Path("/books/{bookId}/")
    @Produces("application/xml")
    public Response getThatBook(@PathParam("bookId") Long bookId) throws BookNotFoundException;

    @GET
    @Path("/books/not-found/{bookId}/")
    @Produces("application/xml")
    public Response getThatBookWithException(@PathParam("bookId") Long bookId) throws BookNotFoundException;

    @GET
    @Path("/books/by/name/{bookName}/")
    @Produces("application/xml")
    public Response getThatBookByName(@PathParam("bookName") String name) throws BookNotFoundException;
}
