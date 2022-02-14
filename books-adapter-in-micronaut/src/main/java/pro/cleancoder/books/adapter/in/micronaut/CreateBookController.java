package pro.cleancoder.books.adapter.in.micronaut;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import lombok.RequiredArgsConstructor;
import pro.cleancoder.books.port.in.CreateBookCommand;
import pro.cleancoder.books.port.in.CreateBookUseCase;

import java.util.UUID;

@Controller("/books")
@RequiredArgsConstructor
public class CreateBookController {

    private final CreateBookUseCase createBookUseCase;

    @Post
    public UUID createBook(@Body CreateBookResource resource) {
        var command = new CreateBookCommand(resource.author(), resource.title(), resource.gender());
        return createBookUseCase.createBook(command);
    }

}
