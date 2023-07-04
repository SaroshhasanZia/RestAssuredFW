package com.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
@Test
public class DeleteRequest {
    public void delete() {

        Response response = given().header("ContentType", ContentType.JSON)
                .pathParam("id", 22)
                .log()
                .all()
                .delete("https://dummy.restapiexample.com/api/v1/delete/{id}");
        response.prettyPrint();
    }
}
