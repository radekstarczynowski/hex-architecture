package pro.cleancoder.books.port.in;

public record CreateBookCommand(String author, String title, String gender) {}
