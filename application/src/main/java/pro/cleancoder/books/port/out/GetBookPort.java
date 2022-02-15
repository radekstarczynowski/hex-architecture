package pro.cleancoder.books.port.out;

import pro.cleancoder.books.Book;

import java.util.Optional;
import java.util.UUID;

public interface GetBookPort {

    Optional<Book> getBook(UUID uuid);

}
