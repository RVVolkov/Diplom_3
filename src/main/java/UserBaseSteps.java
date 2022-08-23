import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class UserBaseSteps extends BaseClass {
    @Step("Создание и регистрация пользователя")
    public ValidatableResponse userCreationAndRegistration(String email, String password, String name) {
        String json = ("{\"email\": \"" + email + "\"," +
                "\"password\": \"" + password + "\"," +
                "\"name\": \"" + name + "\"}");
        return given()
                .spec(getBaseSpec())
                .body(json)
                .when()
                .post(Endpoints.CREATING_USER.endpoint)
                .then();
    }

    @Step("Авторизация пользователя")
    public ValidatableResponse userAuthorization(String email, String password) {
        String json = ("{\"email\": \"" + email + "\"," +
                "\"password\": \"" + password + "\"}");
        return given()
                .spec(getBaseSpec())
                .body(json)
                .when()
                .post(Endpoints.LOGIN_USER.endpoint)
                .then();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .spec(getBaseSpec())
                .header("Authorization", accessToken)
                .when()
                .delete(Endpoints.USER_PATH.endpoint)
                .then()
                .assertThat().body("message", equalTo("User successfully removed"))
                .and().statusCode(202);
    }
}
