package pro.cleancoder.books.port.out.jdbi;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.Optional;
import java.util.UUID;

interface BooksQueryRepository {

    @SqlQuery("""
                select uuid, author, title, gender 
                from books 
                where uuid = :uuid
            """)
    @RegisterConstructorMapper(value = BookEntity.class)
    Optional<BookEntity> findBook(UUID uuid);

}
