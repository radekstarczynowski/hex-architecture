package pro.cleancoder.books.port.out.jpa;

import lombok.RequiredArgsConstructor;
import pro.cleancoder.books.Book;
import pro.cleancoder.books.port.out.GetBookPort;

import javax.persistence.EntityManagerFactory;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
class GetBookRepository implements GetBookPort {

    private final EntityManagerFactory entityManagerFactory;
    private final BookMapper bookMapper;

    @Override
    public Optional<Book> getBook(UUID uuid) {
        var entityManager = entityManagerFactory.createEntityManager();
        var entity = entityManager.find(BookEntity.class, uuid);
        return Optional.ofNullable(entity)
                .map(bookMapper::map);
    }

}
