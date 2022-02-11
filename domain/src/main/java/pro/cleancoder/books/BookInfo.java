package pro.cleancoder.books;

import lombok.Builder;
import lombok.Getter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Getter
public class BookInfo {

    private final String author;
    private final String title;
    private final String gender;
    @Builder.Default
    private final Set<String> tags = new HashSet<>();

    public static BookInfo fromBasicInfo(String author, String title, String gender) {
        return new BookInfo(author, title, gender);
    }

    private BookInfo(String author, String title, String gender) {
        this.author = author;
        this.title = title;
        this.gender = gender;
    }

    public Set<String> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    protected boolean addTag(String tag) {
        return this.tags.add(tag);
    }

}
