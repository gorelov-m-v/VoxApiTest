package tests.accounts;

import appmanager.ApplicationManager;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

public class TestBase {
    public static final ApplicationManager app = new ApplicationManager();

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        RestAssured.baseURI = app.getProperty("papi.host");
    }
}
