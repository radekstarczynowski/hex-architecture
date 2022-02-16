package pro.cleancoder.books.port.out.jdbi;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import pro.cleancoder.books.Book;
import pro.cleancoder.books.port.out.CreateBookPort;

import javax.sql.DataSource;

public class BookWriteRepository implements CreateBookPort {

    private final Jdbi jdbi;
    private final BookMapper bookMapper;

    public BookWriteRepository(DataSource dataSource, BookMapper bookMapper) {
        this.jdbi = Jdbi.create(dataSource);
        this.bookMapper = bookMapper;
        this.jdbi.installPlugin(new SqlObjectPlugin());
    }

    @Override
    public Book createBook(Book book) {
        var entity = bookMapper.map(book);
        jdbi.useExtension(
                BooksCommandRepository.class,
                repository -> repository.insert(entity));
        return book;
    }

}
