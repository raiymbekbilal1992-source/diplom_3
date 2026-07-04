package utils;

import models.User;

import java.util.UUID;

public class UserGenerator {

    public static User getRandomUser() {

        String randomString =
                UUID.randomUUID().toString().substring(0, 8);

        return new User(
                randomString + "@yandex.ru",
                "Password123",
                "User" + randomString
        );
    }
}
