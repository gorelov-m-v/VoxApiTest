package tests.accounts;

import constants.Paths;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.User;
import org.junit.Before;
import org.junit.Test;
import requests.accounts.AddAccount;

public class AddAccountTest {
    Paths paths = new Paths();
    AddAccount addAccount = new AddAccount();

    @Before
    public void setUp() {
        RestAssured.baseURI = paths.URL;
    }

    @Test
    public void AddAccountSmoke() {
        User user = new User().withEmail("mailfortests123@testmail.ru").withName("firstuser")
                              .withPassword("Aa123456!").withActive(true)
                              .withCurrency("RUR").withInitBalance(1234)
                              .withIsTrial(false).withApiKey("sasa")
                              .withInitBalance(3000);

        Response response = addAccount.addAccount(user);

        int actualStatusCode = response.getStatusCode();
        System.out.println(actualStatusCode);
    }
}
