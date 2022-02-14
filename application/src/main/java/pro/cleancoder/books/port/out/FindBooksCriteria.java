package pro.cleancoder.books.port.out;

public record FindBooksCriteria(
        String title,
        int pageNumber,
        int pageSize
) {}
