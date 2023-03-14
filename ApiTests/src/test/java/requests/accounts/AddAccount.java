package requests.accounts;

import constants.Paths;
import io.restassured.response.Response;
import model.User;
import static io.restassured.RestAssured.given;


public class AddAccount {
    Paths paths = new Paths();

    public Response addAccount(User user) {
        Response response = given()
                .header("Content-type", "application/json")
                .queryParam("account_name", user.getAccountName())
                .queryParam("account_password", user.getAccount_password())
                .queryParam("account_email", user.getAccount_email())
                .queryParam("api_key", user.getApiKey())
                .queryParam("currency", user.getCurrency())
                .queryParam("is_trial", user.is_trial())
                .queryParam("active", user.active())
                .queryParam("init_balance", user.getInitBalance())
                .queryParam("is_trial", user.is_trial())
                .when()
                .get("/AddAccount")
                .then()
                .extract()
                .response();

        return response;
    }
}
