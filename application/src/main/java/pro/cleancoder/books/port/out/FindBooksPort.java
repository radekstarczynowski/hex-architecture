package pro.cleancoder.books.port.out;

import pro.cleancoder.books.port.in.FindBooksResult;

public interface FindBooksPort {

    FindBooksResult findBooks(FindBooksCriteria criteria);

}
