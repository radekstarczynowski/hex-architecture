package pro.cleancoder.books;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class BookEntity {

    private final UUID uuid;
    private final String author;
    private final String title;
    private final String gender;

}
