package pro.cleancoder.books;

import io.agroal.api.AgroalDataSource;
import pro.cleancoder.books.port.in.CreateBookUseCase;
import pro.cleancoder.books.port.out.CreateBookPort;
import pro.cleancoder.books.port.out.jdbi.RepositoryConfiguration;
import pro.cleancoder.books.service.CreateBookService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
class ApplicationConfiguration {

    @Produces
    public CreateBookPort createBookPort(AgroalDataSource dataSource) {
        var configuration = new RepositoryConfiguration(dataSource);
        return configuration.createBooksRepository();
    }

    @Produces
    public CreateBookUseCase createBookUseCase(CreateBookPort createBookPort) {
        return new CreateBookService(createBookPort);
    }

}
