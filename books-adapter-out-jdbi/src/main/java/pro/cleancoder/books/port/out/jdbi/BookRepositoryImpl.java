package pro.cleancoder.books.port.out.jdbi;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import pro.cleancoder.books.Book;
import pro.cleancoder.books.port.out.CreateBookPort;

import javax.sql.DataSource;

class BookRepositoryImpl implements CreateBookPort {

    private final Jdbi jdbi;
    private final BookMapper bookMapper;

    BookRepositoryImpl(DataSource dataSource, BookMapper bookMapper) {
        this.jdbi = Jdbi.create(dataSource);
        this.bookMapper = bookMapper;
        this.jdbi.installPlugin(new SqlObjectPlugin());
    }

    @Override
    public Book createBook(Book book) {
        var entity = bookMapper.map(book);
        jdbi.useExtension(
                BooksInsertRepository.class,
                repository -> repository.insert(entity));
        return book;
    }

}
