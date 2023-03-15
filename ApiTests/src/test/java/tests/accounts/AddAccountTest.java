package tests.accounts;

import constants.Paths;
import io.restassured.RestAssured;
import model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import requests.accounts.AddAccountRequest;
import response.accounts.AddAccountResponse;

public class AddAccountTest {
    Paths paths = new Paths();
    AddAccountRequest request = new AddAccountRequest();
    AddAccountResponse response;

    @Before
    public void setUp() {
        RestAssured.baseURI = paths.URL;
    }

    @Test
    public void AddAccountSmoke() {
        User user = new User().withEmail("mailfortesffsdfftes123@testmail.ru").withName("firwesdfsggtuser")
                              .withPassword("Aa123456!").withActive(true)
                              .withCurrency("RUR").withInitBalance(1234)
                              .withIsTrial(false).withApiKey("sasa")
                              .withInitBalance(3000);

        response = request.addAccount(user);
        System.out.println(response.getAccount_id());
        System.out.println(response.getError().getMsg());
    }
}
