package com.piotrkulesza;

import com.piotrkulesza.models.ErrorModel;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.smallrye.common.annotation.Blocking;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class ControllerTest {

    @Test
    void tests() {
        //User exists
        given()
                .when().get("/github/PiotrKulesza")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$.size()", greaterThan(0))
                .body("[0].repositoryName", notNullValue())
                .body("[0].ownerLogin", is("med"))
                .body("[0].branches", not(empty()));
        //User not exists
        given()
                .when().get("/github/nonexistent-user-12345")
                .then()
                .statusCode(404) // Oczekujemy 404
                .contentType(ContentType.JSON)
                .body("message", is("User nonexistent-user-12345 not found."));
        //User do not have repositories
        given()
                .when().get("/github/user-without-repos")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$.size()", is(0));
        //User repository have main branch
        given()
                .when().get("/github/PiotrKulesza")
                .then()
                .statusCode(200)
                .body("[0].branches.name", hasItem("main"));
        //User repository have no branches
        given()
                .when().get("/github/user-with-empty-repo")
                .then()
                .statusCode(200)
                .body("[0].branches", empty());
        //Response time <2s
        given()
                .when().get("/github/PiotrKulesza")
                .then()
                .statusCode(200)
                .time(lessThan(2000L));

    }



}
