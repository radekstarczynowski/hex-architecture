package pro.cleancoder.books.port.in;

import pro.cleancoder.books.Book;

import java.util.Optional;

public interface GetBookUseCase {

    Optional<Book> getBook(GetBookQuery query);

}
