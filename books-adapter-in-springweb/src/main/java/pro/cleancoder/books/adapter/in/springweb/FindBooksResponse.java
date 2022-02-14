package pro.cleancoder.books.adapter.in.springweb;

import java.util.List;

record FindBooksResponse(List<BookBasicInfo> books, long total) {}
