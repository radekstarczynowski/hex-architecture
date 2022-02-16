package pro.cleancoder.books.port.out.jdbi;

import javax.sql.DataSource;

public class RepositoryConfiguration {

    private final DataSource dataSource;
    private final BookMapper bookMapper;

    public RepositoryConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
        this.bookMapper = createBookMapper();
    }

    public BookWriteRepository createBookWriteRepository() {
        return new BookWriteRepository(dataSource, bookMapper);
    }

    public BookReadRepository createBookReadRepository() {
        return new BookReadRepository(dataSource, bookMapper);
    }

    protected BookMapper createBookMapper() {
        return new BookMapper();
    }

}
