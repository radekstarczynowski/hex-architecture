package pro.cleancoder.books.port.out;

import pro.cleancoder.books.Book;

public class BookMapper {

    BookEntity map(Book book) {
        return BookEntity.builder()
                         .uuid(book.getUuid())
                         .author(book.getBookInfo().getAuthor())
                         .title(book.getBookInfo().getTitle())
                         .gender(book.getBookInfo().getGender())
                         .build();
    }

}
