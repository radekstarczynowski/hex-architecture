package pro.cleancoder.books.adapter.in.quarkus;

import java.util.List;

public record FindBooksResponse(List<BookBasicInfo> books, long total) {}
