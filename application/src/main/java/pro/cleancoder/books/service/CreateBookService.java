package pro.cleancoder.books.service;

import lombok.RequiredArgsConstructor;
import pro.cleancoder.books.Book;
import pro.cleancoder.books.BookInfo;
import pro.cleancoder.books.port.in.CreateBookCommand;
import pro.cleancoder.books.port.in.CreateBookUseCase;
import pro.cleancoder.books.port.out.CreateBookPort;

import java.util.UUID;

@RequiredArgsConstructor
public class CreateBookService implements CreateBookUseCase {

    private final CreateBookPort createBookPort;

    @Override
    public UUID createBook(CreateBookCommand command) {
        var book = createBookAggregate(command);
        var savedBook = createBookPort.createBook(book);
        return savedBook.getUuid();
    }

    private Book createBookAggregate(CreateBookCommand command) {
        var bookInfo = BookInfo.fromBasicInfo(command.author(), command.title(), command.gender());
        return Book.createNewBook(bookInfo);
    }

}
