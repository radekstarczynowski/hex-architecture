package pro.cleancoder.books.adapter.in.springweb;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.cleancoder.books.Book;
import pro.cleancoder.books.port.in.GetBookQuery;
import pro.cleancoder.books.port.in.GetBookUseCase;

import java.util.UUID;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
class GetBookController {

    private final GetBookUseCase getBookUseCase;

    @GetMapping("/{book-uuid}")
    public ResponseEntity<BookBasicInfo> getBook(@PathVariable("book-uuid") String bookUuid) {
        var query = new GetBookQuery(UUID.fromString(bookUuid));
        return ResponseEntity.of(
                getBookUseCase.getBook(query).map(this::mapBook)
        );
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
