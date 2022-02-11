package pro.cleancoder.books.adapter.in;

import pro.cleancoder.books.port.in.CreateBookUseCase;
import pro.cleancoder.books.port.out.CreateBookPort;
import pro.cleancoder.books.service.CreateBookService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
class ApplicationConfiguration {

    @Produces
    public CreateBookPort createBookPort() {
        return book -> {
            System.err.println("saving book " + book.getBookInfo().getTitle());
            return book;
        };
    }

    @Produces
    public CreateBookUseCase createBookUseCase(CreateBookPort createBookPort) {
        return new CreateBookService(createBookPort);
    }

}
