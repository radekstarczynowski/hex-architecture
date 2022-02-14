package pro.cleancoder.books;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.cleancoder.books.port.in.CreateBookUseCase;
import pro.cleancoder.books.port.in.FindBooksUseCase;
import pro.cleancoder.books.port.out.CreateBookPort;
import pro.cleancoder.books.port.out.FindBooksPort;
import pro.cleancoder.books.port.out.jpa.RepositoryConfiguration;
import pro.cleancoder.books.service.CreateBookService;
import pro.cleancoder.books.service.FindBooksService;

import javax.persistence.EntityManagerFactory;

@Configuration
class ApplicationConfiguration {

    @Bean
    public RepositoryConfiguration repositoryConfiguration(EntityManagerFactory entityManagerFactory) {
        return new RepositoryConfiguration(entityManagerFactory);
    }

    @Bean
    public CreateBookPort createBookPort(RepositoryConfiguration repositoryConfiguration) {
        return repositoryConfiguration.createCommandBooksRepository();
    }

    @Bean
    public FindBooksPort findBooksPort(RepositoryConfiguration repositoryConfiguration) {
        return repositoryConfiguration.createQueryBooksRepository();
    }

    @Bean
    public CreateBookUseCase createBookUseCase(CreateBookPort createBookPort) {
        return new CreateBookService(createBookPort);
    }

    @Bean
    public FindBooksUseCase findBooksUseCase(FindBooksPort findBooksPort) {
        return new FindBooksService(findBooksPort);
    }

}
