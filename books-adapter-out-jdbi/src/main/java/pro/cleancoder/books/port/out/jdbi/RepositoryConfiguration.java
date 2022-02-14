package pro.cleancoder.books.port.out.jdbi;

import lombok.RequiredArgsConstructor;
import pro.cleancoder.books.port.out.CreateBookPort;

import javax.sql.DataSource;

@RequiredArgsConstructor
public class RepositoryConfiguration {

    private final DataSource dataSource;

    public CreateBookPort createBooksRepository() {
        var bookMapper = createBookMapper();
        return new BookRepositoryImpl(dataSource, bookMapper);
    }

    protected BookMapper createBookMapper() {
        return new BookMapper();
    }

}
