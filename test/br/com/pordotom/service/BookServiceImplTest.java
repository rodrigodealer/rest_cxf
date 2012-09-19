package br.com.pordotom.service;


import br.com.pordotom.service.impl.BookNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.core.Response;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:./web/WEB-INF/config/applicationContext-beans-test.xml" })
public class BookServiceImplTest {

    private static final String WINDOWS_XP = "Windows XP";
    private static final String OS_X = "OS X";
    private static final String GNU_LINUX = "GNU/Linux";

    @Autowired
    protected BookService bookService;
    private Response response;

    @Test
    public void shouldGetThatBook() throws BookNotFoundException {
        Response response = bookService.getThatBook(new Long(1));
        Assert.assertTrue(response.getStatus() == 400);
    }

    @Test(expected = BookNotFoundException.class)
    public void shouldGetAnException() throws BookNotFoundException {
        bookService.getThatBookWithException(new Long(1));
    }

    // bdd approach

    @Test
    public void shouldReturnOKForWindows() throws BookNotFoundException {
        // given
        iWantToSearchABookNamed(WINDOWS_XP);

        // then
        iShouldReceiveStatus(200);
    }

    @Test
    public void shouldReturnNotFoundForWindows() throws BookNotFoundException {
        // given
        iWantToSearchABookNamed(OS_X);

        // then
        iShouldReceiveStatus(404);
    }

    @Test
    public void shouldReturnForbiddenForLinux() throws BookNotFoundException {
        // given
        iWantToSearchABookNamed(GNU_LINUX);

        // then
        iShouldReceiveStatus(403);
    }

    private void iWantToSearchABookNamed(String name) throws BookNotFoundException {
        response = bookService.getThatBookByName(name);
    }

    private void iShouldReceiveStatus(long status) {
        Assert.assertEquals("Valor deve ser " + status, status, response.getStatus());
    }
}
