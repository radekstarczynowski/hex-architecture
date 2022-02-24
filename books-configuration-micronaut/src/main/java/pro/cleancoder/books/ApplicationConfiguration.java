package pro.cleancoder.books;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
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

import javax.sql.DataSource;

@Factory
class ApplicationConfiguration {

    private final RepositoryConfiguration configuration;

    public ApplicationConfiguration(DataSource dataSource) {
        this.configuration = new RepositoryConfiguration(dataSource);
    }

    @Bean
    public BookWriteRepository bookWriteRepository() {
        return configuration.createBookWriteRepository();
    }

    @Bean
    public BookReadRepository bookReadRepository() {
        return configuration.createBookReadRepository();
    }

    @Bean
    public CreateBookUseCase createBookUseCase(CreateBookPort createBookPort) {
        return new CreateBookService(createBookPort);
    }

    @Bean
    public GetBookUseCase getBookUseCase(GetBookPort getBookPort) {
        return new GetBookService(getBookPort);
    }

    @Bean
    public FindBooksUseCase findBooksUseCase(FindBooksPort findBooksPort) {
        return new FindBooksService(findBooksPort);
    }

}
