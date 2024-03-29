package pro.cleancoder.books.adapter.in.quarkus;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pro.cleancoder.books.port.in.CreateBookCommand;
import pro.cleancoder.books.port.in.CreateBookUseCase;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.UUID;

@Path("/books")
@RequiredArgsConstructor
@Slf4j
public class CreateBookController {

    private final CreateBookUseCase createBookUseCase;

    @POST
    public UUID createBook(@Valid CreateBookResource resource) {
        var command = new CreateBookCommand(resource.author(), resource.title(), resource.gender());
        return createBookUseCase.createBook(command);
    }
}
