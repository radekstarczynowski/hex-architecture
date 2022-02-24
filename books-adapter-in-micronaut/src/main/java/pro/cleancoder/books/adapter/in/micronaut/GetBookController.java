package pro.cleancoder.books.adapter.in.micronaut;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import lombok.RequiredArgsConstructor;
import pro.cleancoder.books.Book;
import pro.cleancoder.books.port.in.GetBookQuery;
import pro.cleancoder.books.port.in.GetBookUseCase;

import java.util.UUID;

@Controller("/books")
@RequiredArgsConstructor
public class GetBookController {

    private final GetBookUseCase getBookUseCase;

    @Get("/{book-uuid}")
    public HttpResponse<BookBasicInfo> getBook(@PathVariable("book-uuid") String bookUuid) {
        var query = new GetBookQuery(UUID.fromString(bookUuid));
        return getBookUseCase.getBook(query)
                .map(this::mapBook)
                .map(HttpResponse::ok)
                .orElseGet(HttpResponse::notFound);
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
