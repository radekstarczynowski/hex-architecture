package pro.cleancoder.books.port.out.jpa;

import lombok.RequiredArgsConstructor;
import pro.cleancoder.books.Book;
import pro.cleancoder.books.port.in.FindBooksResult;
import pro.cleancoder.books.port.out.FindBooksCriteria;
import pro.cleancoder.books.port.out.FindBooksPort;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@RequiredArgsConstructor
class QueryBookRepository implements FindBooksPort {

    private final EntityManagerFactory entityManagerFactory;
    private final BookMapper bookMapper;

    @Override
    public FindBooksResult findBooks(FindBooksCriteria criteria) {
        var entityManager = entityManagerFactory.createEntityManager();

        var findBooksQuery = findBooksQuery(criteria, entityManager);
        var books = getAndMapBooks(findBooksQuery);

        var numberOfBooks = countBooksQuery(criteria, entityManager)
                                    .getSingleResult();

        return new FindBooksResult(books, numberOfBooks);
    }

    private TypedQuery<BookEntity> findBooksQuery(FindBooksCriteria criteria, EntityManager entityManager) {
        var builder = entityManager.getCriteriaBuilder();
        var query = builder.createQuery(BookEntity.class);
        var root = query.from(BookEntity.class);

        query.select(root)
             .where(titleEquals(builder, root, criteria));

        var typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult(criteria.pageSize() * criteria.pageNumber());
        typedQuery.setMaxResults(criteria.pageSize());
        return typedQuery;
    }

    private TypedQuery<Long> countBooksQuery(FindBooksCriteria criteria, EntityManager entityManager) {
        var builder = entityManager.getCriteriaBuilder();
        var query = builder.createQuery(Long.class);
        var root = query.from(BookEntity.class);

        query.select(builder.count(root))
             .where(titleEquals(builder, root, criteria));

        return entityManager.createQuery(query);
    }


    private Predicate titleEquals(CriteriaBuilder builder, Root<BookEntity> root, FindBooksCriteria criteria) {
        if(criteria.title() == null || criteria.title().isBlank()) {
            return builder.and();
        }
        return builder.equal(
                builder.lower(root.get("title")),
                criteria.title().toLowerCase()
        );
    }

    private List<Book> getAndMapBooks(TypedQuery<BookEntity> typedQuery) {
        return typedQuery.getResultStream()
                         .map(bookMapper::map)
                         .toList();
    }
}
