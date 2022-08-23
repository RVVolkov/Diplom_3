import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseClass {
    public RequestSpecification getBaseSpec() {
        return new RequestSpecBuilder()
                .setBaseUri("https://stellarburgers.nomoreparties.site")
                .setContentType(ContentType.JSON)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }

    public enum Endpoints {
        //Создание пользователя
        CREATING_USER("/api/auth/register"),
        //Авторизация пользователя
        LOGIN_USER("/api/auth/login"),
        //Получение и изменение данных пользователя, а также его удаление
        USER_PATH("/api/auth/user");
        public String endpoint;

        Endpoints(String endpoint) {
            this.endpoint = endpoint;
        }
    }
}
