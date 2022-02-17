package pro.cleancoder.books;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import pro.cleancoder.books.adapter.in.quarkus.CreateBookResource;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CreateBookControllerTest {

    @Test
    public void test_create_book() {
        given()
                .contentType(ContentType.JSON)
                .body(new CreateBookResource("John Doe", "Insomnia", "Horror"))
                .when()
                .post("/books")
                .then()
                .statusCode(200);
    }

    @Test
    public void test_create_book_bad_request() {
        given()
                .contentType(ContentType.JSON)
                .body(new CreateBookResource(null, null, null))
                .when()
                .post("/books")
                .then()
                .statusCode(400);
    }

}
