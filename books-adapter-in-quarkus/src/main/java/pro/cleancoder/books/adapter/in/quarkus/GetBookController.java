package pro.cleancoder.books.adapter.in.quarkus;

import lombok.RequiredArgsConstructor;
import pro.cleancoder.books.Book;
import pro.cleancoder.books.port.in.GetBookQuery;
import pro.cleancoder.books.port.in.GetBookUseCase;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/books")
@RequiredArgsConstructor
public class GetBookController {

    private final GetBookUseCase getBookUseCase;

    @GET
    @Path("/{book-uuid}")
    public Response getBook(@PathParam("book-uuid") String bookUuid) {
        var query = new GetBookQuery(UUID.fromString(bookUuid));
        return getBookUseCase.getBook(query)
                .map(this::mapBook)
                .map(info -> Response.ok(info).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
    }

    private BookBasicInfo mapBook(Book book) {
        return new BookBasicInfo(
                book.getUuid(),
                book.getBookInfo().getTitle(),
                book.getBookInfo().getAuthor(),
                book.getBookInfo().getGender()
        );
    }

}
