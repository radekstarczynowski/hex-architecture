package pro.cleancoder.books;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.cleancoder.books.port.in.CreateBookUseCase;
import pro.cleancoder.books.port.out.CreateBookPort;
import pro.cleancoder.books.port.out.jpa.RepositoryConfiguration;
import pro.cleancoder.books.service.CreateBookService;

import javax.persistence.EntityManagerFactory;

@Configuration
class ApplicationConfiguration {

    @Bean
    public CreateBookPort createBookPort(EntityManagerFactory entityManagerFactory) {
        var configuration = new RepositoryConfiguration(entityManagerFactory);
        return configuration.createBooksRepository();
    }

    @Bean
    public CreateBookUseCase createBookUseCase(CreateBookPort createBookPort) {
        return new CreateBookService(createBookPort);
    }

}
