package pro.cleancoder.books.port.out.jpa;

import lombok.RequiredArgsConstructor;
import pro.cleancoder.books.port.out.CreateBookPort;
import pro.cleancoder.books.port.out.FindBooksPort;

import javax.persistence.EntityManagerFactory;

@RequiredArgsConstructor
public class RepositoryConfiguration {

    private final EntityManagerFactory entityManagerFactory;
    private final BookMapper bookMapper = new BookMapper();

    public CreateBookPort createCommandBooksRepository() {
        return new CommandBookRepository(entityManagerFactory, bookMapper);
    }

    public FindBooksPort createQueryBooksRepository() {
        return new QueryBookRepository(entityManagerFactory, bookMapper);
    }

}