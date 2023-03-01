package pro.cleancoder.books.adapter.in.quarkus;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pro.cleancoder.books.port.in.FindBooksQuery;
import pro.cleancoder.books.port.in.FindBooksResult;
import pro.cleancoder.books.port.in.FindBooksUseCase;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("/books")
@RequiredArgsConstructor
@Slf4j
public class FindBooksController {

    private final FindBooksUseCase findBooksUseCase;

    @GET
    public FindBooksResponse findBooks(@BeanParam FindBooksRequest request) {
        log.debug("debug!");
        var query = new FindBooksQuery(request.getTitle(), request.getPageNumber(), request.getPageSize());
        var books = findBooksUseCase.findBooks(query);
        return new FindBooksResponse(mapBooks(books), books.total());
    }

    private List<BookBasicInfo> mapBooks(FindBooksResult books) {
        return books.books()
                .stream()
                .map(book -> new BookBasicInfo(
                        book.getUuid(),
                        book.getBookInfo().getTitle(),
                        book.getBookInfo().getAuthor(),
                        book.getBookInfo().getGender()
                ))
                .toList();
    }

}
