package back.integration.card.bankCardProducts;

import back.setUp.baseSetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})


public class GetBankCardProductsTest {
    baseSetUp baseSetUp = new baseSetUp();
    private String accessToken;
    private String baseURI;

    @BeforeMethod
    public void setUp() {
        accessToken = baseSetUp.getAccessToken();
        baseURI = baseSetUp.getBaseURI();
    }

    @Test
    @Description("Card productlar ro‘yxatini olish")
    public void getBankCardProduct() {
//        int setPort = baseSetUp.getPort();
        given().auth().oauth2(accessToken).when().get(baseURI + "/api/v2/card/card-products").then().statusCode(200).log().all();
    }

    @Test
    @Step("Access token bilan GET /api/v2/card/card-products")
    public void testAuthenticationFailure() {
        String invalidToken = "inlinecodedemoversion456";
//        int setPort = baseSetUp.getPort();
        //Negative test case
        given().auth().oauth2(invalidToken).when().get(baseURI + "/api/v2/card/card-products").then().statusCode(401).body("error", equalTo("invalid_token")).body("error_description", containsString("inlinecodedemoversion456")).log().all();
    }

    @Test
    public void getOneCard() {
        given().auth().oauth2(accessToken).when().get(baseURI + "/api/v2/card/card-products").then().statusCode(200).log().all();
    }
}
