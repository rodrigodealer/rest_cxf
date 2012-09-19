package br.com.pordotom.service.impl;

import br.com.pordotom.service.BookService;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Service("bookService")
public class BookServiceImpl implements BookService {

    public Response getThatBook(Long bookId) throws BookNotFoundException {
        return Response.status(Status.BAD_REQUEST)
                .build();
    }

    public Response getThatBookWithException(Long bookId) throws BookNotFoundException {
        throw new BookNotFoundException();
    }

    public Response getThatBookByName(String name) throws BookNotFoundException {
        if (name.contains("Windows")) {
            return Response.status(Status.OK)
                    .build();
        }
        if (name.contains("OS X")) {
            return Response.status(Status.NOT_FOUND)
                    .build();
        }
        return Response.status(Status.FORBIDDEN)
                .build();
    }
}
