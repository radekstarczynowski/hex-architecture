package pro.cleancoder.books.adapter.in.quarkus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.ws.rs.QueryParam;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FindBooksRequest {

    @QueryParam("title")
    private String title;

    @QueryParam("pageNumber")
    private Integer pageNumber;

    @QueryParam("pageSize")
    private Integer pageSize;

}