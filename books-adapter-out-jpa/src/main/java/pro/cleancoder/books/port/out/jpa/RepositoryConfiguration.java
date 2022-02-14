package pro.cleancoder.books.port.out.jpa;

import lombok.RequiredArgsConstructor;
import pro.cleancoder.books.port.out.CreateBookPort;

import javax.persistence.EntityManagerFactory;

@RequiredArgsConstructor
public class RepositoryConfiguration {

    private final EntityManagerFactory entityManagerFactory;

    public CreateBookPort createBooksRepository() {
        var bookMapper = createBookMapper();
        return new BookRepositoryImpl(entityManagerFactory, bookMapper);
    }

    protected BookMapper createBookMapper() {
        return new BookMapper();
    }

}