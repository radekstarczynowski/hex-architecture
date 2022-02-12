package pro.cleancoder.books.adapter.in;

import io.agroal.api.AgroalDataSource;
import pro.cleancoder.books.BookMapper;
import pro.cleancoder.books.BooksRepository;
import pro.cleancoder.books.port.in.CreateBookUseCase;
import pro.cleancoder.books.port.out.CreateBookPort;
import pro.cleancoder.books.service.CreateBookService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
class ApplicationConfiguration {

    @Produces
    public CreateBookPort createBookPort(AgroalDataSource dataSource) {
        return new BooksRepository(dataSource, new BookMapper());
    }

    @Produces
    public CreateBookUseCase createBookUseCase(CreateBookPort createBookPort) {
        return new CreateBookService(createBookPort);
    }

}
