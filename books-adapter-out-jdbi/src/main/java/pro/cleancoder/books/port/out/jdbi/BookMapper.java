package pro.cleancoder.books.port.out.jdbi;

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

    public Book map(BookEntity entity) {
        var bookInfo = BookInfo.fromBasicInfo(entity.getAuthor(), entity.getTitle(), entity.getGender());
        return Book.createBook(entity.getUuid(), bookInfo);
    }
}
