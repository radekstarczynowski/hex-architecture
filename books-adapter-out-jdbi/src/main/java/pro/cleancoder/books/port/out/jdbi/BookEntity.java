package pro.cleancoder.books.port.out.jdbi;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class BookEntity {

    private UUID uuid;
    private String author;
    private String title;
    private String gender;

    public BookEntity(UUID uuid, String author, String title, String gender) {
        this.uuid = uuid;
        this.author = author;
        this.title = title;
        this.gender = gender;
    }
}
