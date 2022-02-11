package pro.cleancoder.books.port.in;

import java.util.UUID;

public interface CreateBookUseCase {

    UUID createBook(CreateBookCommand command);

}
