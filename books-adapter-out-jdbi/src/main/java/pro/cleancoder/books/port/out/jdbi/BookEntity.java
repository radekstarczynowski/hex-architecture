package pro.cleancoder.books.port.out.jdbi;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
class BookEntity {

    private final UUID uuid;
    private final String author;
    private final String title;
    private final String gender;

}
