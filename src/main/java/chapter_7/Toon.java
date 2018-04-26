package chapter_7;

import common.Result;

public class Toon {
    private final String firstName;
    private final String lastName;
    private final Result<String> email;

    Toon(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = Result.empty();

    }

    Toon(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = Result.success(email);
    }

    public Result<String> getEmail() {
        return email;
    }
}
