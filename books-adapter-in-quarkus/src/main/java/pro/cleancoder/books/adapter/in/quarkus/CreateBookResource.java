package pro.cleancoder.books.adapter.in.quarkus;

import javax.validation.constraints.NotBlank;

public record CreateBookResource(
        @NotBlank String author,
        @NotBlank String title,
        @NotBlank String gender
) {}
