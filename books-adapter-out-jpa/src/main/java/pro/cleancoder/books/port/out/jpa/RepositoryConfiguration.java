package pro.cleancoder.books.port.out.jpa;

import lombok.RequiredArgsConstructor;
import pro.cleancoder.books.port.out.CreateBookPort;
import pro.cleancoder.books.port.out.FindBooksPort;
import pro.cleancoder.books.port.out.GetBookPort;

import javax.persistence.EntityManagerFactory;

@RequiredArgsConstructor
public class RepositoryConfiguration {

    private final EntityManagerFactory entityManagerFactory;
    private final BookMapper bookMapper = new BookMapper();

    public CreateBookPort createCreateBookPort() {
        return new CommandBookRepository(entityManagerFactory, bookMapper);
    }

    public FindBooksPort createFindBooksPort() {
        return new FindBooksRepository(entityManagerFactory, bookMapper);
    }

    public GetBookPort createGetBookPort() {
        return new GetBookRepository(entityManagerFactory, bookMapper);
    }

}