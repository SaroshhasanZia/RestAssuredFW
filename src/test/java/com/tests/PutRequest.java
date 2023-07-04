package com.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PutRequest {

    @Test
    public void update(){
        JSONObject obj = new JSONObject();
        obj.put("name","tester");
        obj.put("salary","1500");
        obj.put("age","25");

        Response response = given().header("ContentType",ContentType.JSON)
                .pathParam("id",22)
                .log()
                .all()
                .body(obj.toMap())
                .put("https://dummy.restapiexample.com/api/v1/update/{id}");
        response.prettyPrint();
        System.out.println(response.jsonPath().getString("status"));
        List<String> list = response.jsonPath().getList("data");
        System.out.println(list);



    }
}
