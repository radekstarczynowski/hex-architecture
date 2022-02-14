package pro.cleancoder.books.port.in;

import pro.cleancoder.books.Book;

import java.util.List;

public record FindBooksResult(List<Book> books, long total) {}