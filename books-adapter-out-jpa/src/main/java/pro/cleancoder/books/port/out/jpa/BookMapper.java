package pro.cleancoder.books.port.out.jpa;

import pro.cleancoder.books.Book;
import pro.cleancoder.books.BookInfo;

class BookMapper {

    BookEntity map(Book book) {
        return BookEntity.builder()
                         .uuid(book.getUuid())
                         .author(book.getBookInfo().getAuthor())
                         .title(book.getBookInfo().getTitle())
                         .gender(book.getBookInfo().getGender())
                         .build();
    }

    Book map(BookEntity book) {
        var bookInfo = BookInfo.fromBasicInfo(book.getAuthor(), book.getTitle(), book.getGender());
        return Book.createBook(book.getUuid(), bookInfo);
    }

}
