package pro.cleancoder.books.port.out.jdbi;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.ConstructorMapper;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import pro.cleancoder.books.Book;
import pro.cleancoder.books.port.in.FindBooksResult;
import pro.cleancoder.books.port.out.FindBooksCriteria;
import pro.cleancoder.books.port.out.FindBooksPort;
import pro.cleancoder.books.port.out.GetBookPort;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class BookReadRepository implements GetBookPort, FindBooksPort {

    private final Jdbi jdbi;
    private final BookMapper bookMapper;

    public BookReadRepository(DataSource dataSource, BookMapper bookMapper) {
        this.jdbi = Jdbi.create(dataSource);
        this.bookMapper = bookMapper;
        this.jdbi.installPlugin(new SqlObjectPlugin());
    }

    @Override
    public Optional<Book> getBook(UUID uuid) {
        return jdbi.withExtension(
                        BooksQueryRepository.class,
                        repository -> repository.findBook(uuid)
                )
                .map(bookMapper::map);
    }

    @Override
    public FindBooksResult findBooks(FindBooksCriteria criteria) {
        return jdbi.withHandle(handle -> {
            var countQuery = prepareCountQuery(criteria);
            var count = handle.createQuery(countQuery.query)
                    .bindMap(countQuery.args)
                    .mapTo(Long.class)
                    .one();
            var selectQuery = prepareSelectQuery(criteria);
            var books = handle.registerRowMapper(ConstructorMapper.factory(BookEntity.class))
                    .createQuery(selectQuery.query)
                    .bindMap(selectQuery.args)
                    .mapTo(BookEntity.class)
                    .stream()
                    .map(bookMapper::map)
                    .toList();
            return new FindBooksResult(books, count);
        });
    }

    private PreparedQuery prepareSelectQuery(FindBooksCriteria criteria) {
        return new PreparedQuery("select * from books ")
                .append(createWhereClause(criteria))
                .append(createPaginationClause(criteria));
    }

    private PreparedQuery prepareCountQuery(FindBooksCriteria criteria) {
        return new PreparedQuery("select count(1) from books ")
                .append(createWhereClause(criteria));
    }

    private PreparedQuery createWhereClause(FindBooksCriteria criteria) {
        if(criteria.title() != null && !criteria.title().isBlank()) {
            String sql = "where upper(title) = upper(:title) ";
            return new PreparedQuery(sql, Map.of("title", criteria.title()));
        }
        return new PreparedQuery("");
    }

    private PreparedQuery createPaginationClause(FindBooksCriteria criteria) {
        return new PreparedQuery(
                " order by title limit :limit offset :offset",
                Map.of("limit", criteria.pageSize(), "offset", criteria.pageNumber() * criteria.pageSize()));
    }

    private static class PreparedQuery {
        private final String query;
        private final Map<String, Object> args;

        PreparedQuery(String query) {
            this.query = query;
            this.args = Map.of();
        }

        PreparedQuery(String query, Map<String, Object> args) {
            this.query = query;
            this.args = args;
        }

        PreparedQuery append(PreparedQuery query) {
            var args = new HashMap<>(this.args);
            args.putAll(query.args);
            return new PreparedQuery(this.query + query.query, args);
        }
    }

}
