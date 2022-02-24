package pro.cleancoder.books.adapter.in.micronaut;

import java.util.List;

public record FindBooksResponse(List<BookBasicInfo> books, long total) {}
