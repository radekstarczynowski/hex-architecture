package pro.cleancoder.books.port.out.jpa;

import lombok.RequiredArgsConstructor;
import pro.cleancoder.books.Book;
import pro.cleancoder.books.port.out.CreateBookPort;

import javax.persistence.EntityManagerFactory;

@RequiredArgsConstructor
class BookRepositoryImpl implements CreateBookPort {

    private final EntityManagerFactory entityManagerFactory;
    private final BookMapper bookMapper;

    @Override
    public Book createBook(Book book) {
        var entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        var entity = bookMapper.map(book);
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        return book;
    }

}
