package pro.cleancoder.books.port.in;

public record FindBooksQuery(
        String title,
        Integer pageNumber,
        Integer pageSize
) {}
