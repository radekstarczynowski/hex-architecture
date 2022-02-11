package pro.cleancoder.books;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pro.cleancoder.books.port.in.CreateBookUseCase;
import pro.cleancoder.books.port.out.CreateBookPort;
import pro.cleancoder.books.service.CreateBookService;

@SpringBootApplication(scanBasePackages = "pro.cleancoder.books.adapter.in")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CreateBookPort createBookPort() {
        return book -> {
            System.err.println("saving book " + book.getBookInfo().getTitle());
            return book;
        };
    }

    @Bean
    public CreateBookUseCase createBookUseCase(CreateBookPort createBookPort) {
        return new CreateBookService(createBookPort);
    }

}
