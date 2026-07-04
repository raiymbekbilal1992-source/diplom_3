package api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.User;

import static io.restassured.RestAssured.given;

public class UserClient {

    private static final String BASE_URI =
            "https://qa-stellarburgers.education-services.ru";

    public UserClient() {
        RestAssured.baseURI = BASE_URI;
    }

    @Step("Создание пользователя через API")
    public Response createUser(User user) {

        return given()
                .header("Content-Type", "application/json")
                .body(user)
                .post("/api/auth/register");
    }

    @Step("Логин пользователя через API")
    public Response login(User user) {

        return given()
                .header("Content-Type", "application/json")
                .body(user)
                .post("/api/auth/login");
    }

    @Step("Удаление пользователя через API")
    public Response deleteUser(String accessToken) {

        return given()
                .header("Authorization", accessToken)
                .delete("/api/auth/user");
    }
}