package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.User;

import static io.restassured.RestAssured.given;

public class UserClient {

    private static final String BASE_URI =
            "https://stellarburgers.nomoreparties.site";

    public UserClient() {
        RestAssured.baseURI = BASE_URI;
    }

    public Response createUser(User user) {

        return given()
                .header("Content-type", "application/json")
                .body(user)
                .post("/api/auth/register");
    }

    public Response login(User user) {

        return given()
                .header("Content-type", "application/json")
                .body(user)
                .post("/api/auth/login");
    }

    public Response deleteUser(String token) {

        return given()
                .header("Authorization", token)
                .delete("/api/auth/user");
    }
}