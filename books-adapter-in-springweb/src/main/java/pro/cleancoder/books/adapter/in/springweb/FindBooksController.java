package pro.cleancoder.books.adapter.in.springweb;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.cleancoder.books.port.in.FindBooksQuery;
import pro.cleancoder.books.port.in.FindBooksResult;
import pro.cleancoder.books.port.in.FindBooksUseCase;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
class FindBooksController {

    private final FindBooksUseCase findBooksUseCase;

    @GetMapping
    public FindBooksResponse findBooks(FindBooksRequest request) {
        var query = new FindBooksQuery(request.getTitle(), request.getPageNumber(), request.getPageSize());
        var books = findBooksUseCase.findBooks(query);
        return new FindBooksResponse(mapBooks(books), books.total());
    }

    private List<BookBasicInfo> mapBooks(FindBooksResult books) {
        return books.books()
                    .stream()
                    .map(book -> new BookBasicInfo(
                            book.getBookInfo().getTitle(),
                            book.getBookInfo().getAuthor(),
                            book.getBookInfo().getGender()
                    ))
                    .toList();
    }

}
