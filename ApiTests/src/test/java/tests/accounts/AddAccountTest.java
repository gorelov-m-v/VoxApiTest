package tests.accounts;

import constants.Paths;
import io.restassured.RestAssured;
import model.User;
import org.junit.Before;
import org.junit.Test;
import requests.accounts.AddAccountRequest;
import response.accounts.AddAccountResponse;
import utils.Generator;

public class AddAccountTest {
    Paths paths = new Paths();
    Generator generate = new Generator();
    AddAccountRequest request = new AddAccountRequest();
    AddAccountResponse response;

    @Before
    public void setUp() {
        RestAssured.baseURI = paths.URL;
    }

    @Test
    public void AddAccountSmoke() {
        User user = new User().withEmail(generate.randomEmail()).withName(generate.randomAccountName())
                              .withPassword(generate.password).withActive(true)
                              .withCurrency(generate.CURRENCY[0]).withInitBalance(generate.randomValue(1000, 2000))
                              .withIsTrial(false).withApiKey(generate.api_key);


        response = request.addAccount(user);
        System.out.println(response.account_id());

    }
}
