package pro.cleancoder.books;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Book {

    private final UUID uuid;
    private final BookInfo bookInfo;

    public static Book createNewBook(BookInfo bookInfo) {
        return new Book(UUID.randomUUID(), bookInfo);
    }

    public static Book createBook(UUID uuid, BookInfo bookInfo) {
        return new Book(uuid, bookInfo);
    }

    public boolean addTag(String tag) {
        return this.bookInfo.addTag(tag);
    }

}
