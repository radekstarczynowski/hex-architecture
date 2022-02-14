package pro.cleancoder.books.adapter.in.springweb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class FindBooksRequest {

    private String title;
    private Integer pageNumber;
    private Integer pageSize;

}