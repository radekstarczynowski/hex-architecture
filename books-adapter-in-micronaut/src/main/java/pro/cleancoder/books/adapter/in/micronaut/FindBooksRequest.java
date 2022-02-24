package pro.cleancoder.books.adapter.in.micronaut;

import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import lombok.Getter;

@Introspected
@Getter
public class FindBooksRequest {

    private String title;
    private Integer pageNumber;
    private Integer pageSize;

    @Creator
    public FindBooksRequest(String title, Integer pageNumber, Integer pageSize) {
        this.title = title;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

}