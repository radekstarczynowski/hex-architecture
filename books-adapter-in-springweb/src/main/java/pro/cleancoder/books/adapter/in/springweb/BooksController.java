package pro.cleancoder.books.adapter.in.springweb;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.cleancoder.books.port.in.CreateBookCommand;
import pro.cleancoder.books.port.in.CreateBookUseCase;

import java.util.UUID;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
class BooksController {

    private final CreateBookUseCase createBookUseCase;

    @PostMapping
    public UUID createBook(@RequestBody CreateBookResource resource) {
        var command = new CreateBookCommand(resource.author(), resource.title(), resource.gender());
        return createBookUseCase.createBook(command);
    }

}
