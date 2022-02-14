package pro.cleancoder.books.service;

import lombok.RequiredArgsConstructor;
import pro.cleancoder.books.port.in.FindBooksQuery;
import pro.cleancoder.books.port.in.FindBooksResult;
import pro.cleancoder.books.port.in.FindBooksUseCase;
import pro.cleancoder.books.port.out.FindBooksCriteria;
import pro.cleancoder.books.port.out.FindBooksPort;

@RequiredArgsConstructor
public class FindBooksService implements FindBooksUseCase {

    private static final int FIRST_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 20;

    private final FindBooksPort findBooksPort;

    @Override
    public FindBooksResult findBooks(FindBooksQuery query) {
        var pageNumber = query.pageNumber() != null ? query.pageNumber() : FIRST_PAGE_NUMBER;
        var pageSize = query.pageSize() != null ? query.pageSize() : DEFAULT_PAGE_SIZE;

        var criteria = new FindBooksCriteria(query.title(), pageNumber, pageSize);
        return findBooksPort.findBooks(criteria);
    }
}
