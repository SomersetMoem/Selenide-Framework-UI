package Helpers;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

public class ApiAuth {

    private static final String BASE_URL = "https://asd.ru/api";
    private static final String AUTH_ENDPOINT = "/user/auth";
    private static final String REGISTER_ENDPOINT = "/user/register";
    public static String getToken(String phone, String code) {
        Response responseAuth = RestAssured.given()
                .baseUri(BASE_URL)
                .basePath(AUTH_ENDPOINT)
                .contentType(ContentType.JSON)
                .body("{\"phone\": \"" + phone + "\", \"consent\": true, \"debug\": true}")
                .post();

        int userId = responseAuth.path("user_id");

        String requestBodyRegister = "{\n"
                + "  \"user_id\": \"" + userId + "\",\n"
                + "  \"code\": \"" + code + "\",\n"
                + "  \"passport_lastname\": \"Василевский\",\n"
                + "  \"passport_name\": \"Никита\",\n"
                + "  \"passport_middlename\": \"Алексеевич\",\n"
                + "  \"email\": \"nik@mail.ru\",\n"
                + "  \"birth_date\": \"1998-02-03\",\n"
                + "  \"gender\": 1,\n"
                + "  \"no_middlename\": false,\n"
                + "  \"debug\": true\n"
                + "}";

        Response responseRegister = RestAssured.given()
                .baseUri(BASE_URL)
                .basePath(REGISTER_ENDPOINT)
                .contentType(ContentType.JSON)
                .body(requestBodyRegister)
                .post();

        JSONObject jsonResponse = new JSONObject(responseRegister.getBody().asString());
        String token = jsonResponse.getJSONObject("auth").getString("token");
        return token;
    }
}
