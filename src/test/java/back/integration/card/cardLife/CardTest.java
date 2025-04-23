package back.integration.card.cardLife;

import back.setUp.BaseSetUp;
import back.setUp.TestBase;
import com.aventstack.extentreports.Status;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})


public class CardTest extends TestBase {
    BaseSetUp baseSetUp = new BaseSetUp();
    private String accessToken;
    private String baseURI;


    @BeforeMethod
    public void setUp() {
        accessToken = baseSetUp.getAccessToken();
        baseURI = baseSetUp.getBaseURI();


    }

    @Test
    public void testGetAllCards() {
        test.log(Status.INFO,"Sending request to get all cards");
        given().auth().oauth2(accessToken).when().get(baseURI + "/api/v2/card").then().statusCode(200).log().all().extract().response();
        test.pass("Successfully passed");

    }

    @Test
    public void testGetAllCardsInvalidToken() {
        test.log(Status.INFO,"Sending request to InvalidToken");
        // Negative test case: Simulate an invalid o\r missing authentication token
        String invalidToken = "ae8470e8-4e49-4d72-b259-5b6f3ca0d602657542";
        given().auth().oauth2(invalidToken).when().get(baseURI + "/api/v1/card").then().statusCode(404).log().all();
    }
//
//    @Test
//    public void testGetNonExistentEndpoint() {
//        // Negative test case: incorrect endpoint
//        given().auth().oauth2(accessToken).when().get(baseURI + "/api/v2").then().statusCode(404).log().all();
//    }
//
//    @Test
//    public void checkGetCardTypeByPan() {
//        given().auth().oauth2(accessToken).when().get(baseURI + "/api/v2/card/check-type/{pan}", "9860").then().statusCode(200).log().all();
//    }
//
//    @Test
//    public void testGetOneCard() {
//        given().auth().oauth2(accessToken).when().get(baseURI + "/api/v2/card/{cardId}", "354").then().statusCode(200).log().all();
//    }
//
//    @Test
//    public void checkGetCardPan() {
//        given().auth().oauth2(accessToken).when().get(baseURI + "/api/v2/card/get-pan/354").then().statusCode(200).log().all();
//    }
//
//    @Test
//    public void getRefreshBalance() {
//        given().auth().oauth2(accessToken).when().get(baseURI + "/api/v2/card/refresh-balance").then().statusCode(200).log().all();
//    }
//
//    @Test
//    public void testGetRefreshBalanceInvalidEndpoint() {
//        // Negative test case:  non-endpoint
//        given().auth().oauth2(accessToken).when().get(baseURI + "/api/v2/card/refresh").then().statusCode(400).log().all();
//    }
//
//    @Test
//    public void getCardTypes() {
//        given().auth().oauth2(accessToken).when().get(baseURI + "/api/v2/card/card-types").then().statusCode(200).log().all();
//    }
//
//    @Test
//    public void getCardPanBySms() {
//        given().auth().oauth2(accessToken).when().get(baseURI + "/api/v2/card/pan-sms/354").then().statusCode(200).log().all();
//    }
//
//    @Test
//    public void testGetCardPanBySmsInvalidCardId() {
//        // negative test case
//        // invalid card id
//        given().auth().oauth2(accessToken).when().get(baseURI + "/api/v2/card/pan-sms/100").then().statusCode(400).log().all();
//    }
//
//    @Test
//    public void testPostAddCard() {
//        given().auth().oauth2(accessToken).contentType(ContentType.JSON).body("{\"cardName\": \"HUMO Xalq\",\"pan\":\"9860080105058947\",\"expireDate\":\"0127\"}").when().post(baseURI + "/api/v2/card").then().log().all();
//
//    }
//
//    @Test
//    public void testPostAddCardInvalidRequestBody() {
//        // Negative test case: Missing or invalid fields in the request body
//        given().auth().oauth2(accessToken).contentType(ContentType.JSON).body("{\"cardFull\": \"demo\", \"pan\":\"9860050556545689\",\"expireDate\":\"0102\"}").when().post(baseURI + "/api/v2/card").then().statusCode(400).log().all();
//
//    }

//    @Test
//    public void deleteCardByCardId() {
//        Response response = given().auth().oauth2(accessToken).when().delete(baseURI + "/api/v2/card/{cardId}", "371").then().statusCode(200).log().all().extract().response();
//
//        String message = response.jsonPath().getString("message");
//        assertEquals("Process successfully done!", message);
//    }


}
