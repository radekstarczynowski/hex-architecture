package pro.cleancoder.books.adapter.in.micronaut;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import lombok.RequiredArgsConstructor;
import pro.cleancoder.books.port.in.FindBooksQuery;
import pro.cleancoder.books.port.in.FindBooksResult;
import pro.cleancoder.books.port.in.FindBooksUseCase;

import java.util.List;

@Controller("/books")
@RequiredArgsConstructor
public class FindBooksController {

    private final FindBooksUseCase findBooksUseCase;

    @Get
    public FindBooksResponse findBooks(@QueryValue FindBooksRequest request) {
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
