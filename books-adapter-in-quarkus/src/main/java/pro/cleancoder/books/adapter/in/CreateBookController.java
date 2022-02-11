package pro.cleancoder.books.adapter.in;

import lombok.RequiredArgsConstructor;
import pro.cleancoder.books.port.in.CreateBookCommand;
import pro.cleancoder.books.port.in.CreateBookUseCase;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.UUID;

@Path("/books")
@RequiredArgsConstructor
public class CreateBookController {

    private final CreateBookUseCase createBookUseCase;

    @POST
    public UUID createBook(CreateBookResource resource) {
        var command = new CreateBookCommand(resource.author(), resource.title(), resource.gender());
        return createBookUseCase.createBook(command);
    }
}
