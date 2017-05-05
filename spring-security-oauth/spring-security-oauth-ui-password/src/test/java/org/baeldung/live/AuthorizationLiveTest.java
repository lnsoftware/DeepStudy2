package org.baeldung.live;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.http.MediaType;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

public class AuthorizationLiveTest {

    public static String OauthServerUrl = "http://localhost:8096/oauth-server/oauth/token" ;

    public static String fooUrl = "http://localhost:8080/oauth-resource/foos/1";

    public static String barUrl = "http://localhost:8080/oauth-resource/bars/1";


    static String FooUserName = "krishna";
    static String FooUserPass = "k123";

    private String obtainAccessToken(String clientId, String username, String password) {
        final Map<String, String> params = new HashMap<String, String>();
        params.put("grant_type", "password");
        params.put("client_id", clientId);
        params.put("username", username);
        params.put("password", password);
        final Response response = RestAssured.given().auth().preemptive().basic(clientId, "secret").and().with().params(params).when().post( OauthServerUrl );
        return response.jsonPath().getString("access_token");
    }

    @Test
    public void givenUser_whenUseFooClient_thenOkForFooResourceOnly() {
        final String accessToken = obtainAccessToken("fooClientIdPassword", FooUserName , FooUserPass);

        System.out.println(accessToken);
        final Response fooResponse = RestAssured.given().header("Authorization", "Bearer " + accessToken).get( fooUrl );
        assertEquals(200, fooResponse.getStatusCode());
        assertNotNull(fooResponse.jsonPath().get("name"));

        final Response barResponse = RestAssured.given().header("Authorization", "Bearer " + accessToken).get( barUrl );
        assertEquals(403, barResponse.getStatusCode());
    }





    //没有改写
    @Test
    public void givenUser_whenUseBarClient_thenOkForBarResourceReadOnly() {
        final String accessToken = obtainAccessToken("barClientIdPassword", "john", "123");

        final Response fooResponse = RestAssured.given().header("Authorization", "Bearer " + accessToken).get("http://localhost:8081/spring-security-oauth-resource/foos/1");
        assertEquals(403, fooResponse.getStatusCode());

        final Response barReadResponse = RestAssured.given().header("Authorization", "Bearer " + accessToken).get("http://localhost:8081/spring-security-oauth-resource/bars/1");
        assertEquals(200, barReadResponse.getStatusCode());
        assertNotNull(barReadResponse.jsonPath().get("name"));

        final Response barWritResponse = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).header("Authorization", "Bearer " + accessToken).body("{\"id\":1,\"name\":\"MyBar\"}").post("http://localhost:8081/spring-security-oauth-resource/bars");
        assertEquals(403, barWritResponse.getStatusCode());
    }

    @Test
    public void givenAdmin_whenUseBarClient_thenOkForBarResourceReadWrite() {
        final String accessToken = obtainAccessToken("barClientIdPassword", "tom", "111");

        final Response fooResponse = RestAssured.given().header("Authorization", "Bearer " + accessToken).get("http://localhost:8080/spring-security-oauth-resource/foos/1");
        assertEquals(403, fooResponse.getStatusCode());

        final Response barResponse = RestAssured.given().header("Authorization", "Bearer " + accessToken).get("http://localhost:8080/spring-security-oauth-resource/bars/1");
        assertEquals(200, barResponse.getStatusCode());
        assertNotNull(barResponse.jsonPath().get("name"));

        final Response barWritResponse = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).header("Authorization", "Bearer " + accessToken).body("{\"id\":1,\"name\":\"MyBar\"}").post("http://localhost:8080/spring-security-oauth-resource/bars");
        assertEquals(201, barWritResponse.getStatusCode());
        assertEquals("MyBar", barWritResponse.jsonPath().get("name"));
    }

}
