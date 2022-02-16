package pro.cleancoder.books;

import io.agroal.api.AgroalDataSource;
import pro.cleancoder.books.port.in.CreateBookUseCase;
import pro.cleancoder.books.port.in.FindBooksUseCase;
import pro.cleancoder.books.port.in.GetBookUseCase;
import pro.cleancoder.books.port.out.CreateBookPort;
import pro.cleancoder.books.port.out.FindBooksPort;
import pro.cleancoder.books.port.out.GetBookPort;
import pro.cleancoder.books.port.out.jdbi.BookReadRepository;
import pro.cleancoder.books.port.out.jdbi.BookWriteRepository;
import pro.cleancoder.books.port.out.jdbi.RepositoryConfiguration;
import pro.cleancoder.books.service.CreateBookService;
import pro.cleancoder.books.service.FindBooksService;
import pro.cleancoder.books.service.GetBookService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
class ApplicationConfiguration {

    private final RepositoryConfiguration configuration;

    public ApplicationConfiguration(AgroalDataSource dataSource) {
        this.configuration = new RepositoryConfiguration(dataSource);
    }

    @Produces
    public BookWriteRepository bookWriteRepository() {
        return configuration.createBookWriteRepository();
    }

    @Produces
    public BookReadRepository bookReadRepository() {
        return configuration.createBookReadRepository();
    }

    @Produces
    public CreateBookUseCase createBookUseCase(CreateBookPort createBookPort) {
        return new CreateBookService(createBookPort);
    }

    @Produces
    public GetBookUseCase getBookUseCase(GetBookPort getBookPort) {
        return new GetBookService(getBookPort);
    }

    @Produces
    public FindBooksUseCase findBooksUseCase(FindBooksPort findBooksPort) {
        return new FindBooksService(findBooksPort);
    }

}
