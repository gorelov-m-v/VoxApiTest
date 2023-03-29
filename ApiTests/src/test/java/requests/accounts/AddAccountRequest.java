package requests.accounts;

import constants.Paths;
import model.User;
import response.accounts.AddAccountResponse;

import static io.restassured.RestAssured.given;


public class AddAccountRequest {
    Paths paths = new Paths();

    public AddAccountResponse addAccount(User user) {
        return given()
                .header("Content-type", "application/json")
                .queryParam("account_name", user.accountName())
                .queryParam("account_password", user.getAccount_password())
                .queryParam("account_email", user.getAccount_email())
                .queryParam("api_key", user.getApiKey())
                .queryParam("currency", user.getCurrency())
                .queryParam("is_trial", user.is_trial())
                .queryParam("active", user.active())
                .queryParam("init_balance", user.getInitBalance())
                .queryParam("is_trial", user.is_trial())
                .when()
                .get(paths.addAccount)
                .then()
                .extract()
                .body()
                .as(AddAccountResponse.class);
    }
}
