import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetPostsTest {

    static {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    void testGetPosts() {
        given()
        .when()
            .get("/posts")
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0))
            .body("[0].userId", notNullValue());
    }

    @Test
    void testGetPostById() {
        given()
        .when()
            .get("/posts/1")
        .then()
            .statusCode(200)
            .body("id", equalTo(1))
            .body("title", not(isEmptyOrNullString()));
    }

    @Test
    void testCreatePost() {
        String payload = "{ \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1 }";

        given()
            .header("Content-type", "application/json")
            .body(payload)
        .when()
            .post("/posts")
        .then()
            .statusCode(201)
            .body("id", notNullValue())
            .body("title", equalTo("foo"));
    }
}
