package pro.cleancoder.books;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import pro.cleancoder.books.port.in.CreateBookUseCase;
import pro.cleancoder.books.port.out.BookMapper;
import pro.cleancoder.books.port.out.BooksRepository;
import pro.cleancoder.books.port.out.CreateBookPort;
import pro.cleancoder.books.service.CreateBookService;

import javax.sql.DataSource;

@Factory
class ApplicationConfiguration {

    @Bean
    public CreateBookPort createBookPort(DataSource dataSource) {
        return new BooksRepository(dataSource, new BookMapper());
    }

    @Bean
    public CreateBookUseCase createBookUseCase(CreateBookPort createBookPort) {
        return new CreateBookService(createBookPort);
    }

}
