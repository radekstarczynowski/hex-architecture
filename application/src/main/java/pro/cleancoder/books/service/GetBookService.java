package pro.cleancoder.books.service;

import lombok.RequiredArgsConstructor;
import pro.cleancoder.books.Book;
import pro.cleancoder.books.port.in.GetBookQuery;
import pro.cleancoder.books.port.in.GetBookUseCase;
import pro.cleancoder.books.port.out.GetBookPort;

import java.util.Optional;

@RequiredArgsConstructor
public class GetBookService implements GetBookUseCase {

    private final GetBookPort getBookPort;

    @Override
    public Optional<Book> getBook(GetBookQuery query) {
        return getBookPort.getBook(query.uuid());
    }

}
