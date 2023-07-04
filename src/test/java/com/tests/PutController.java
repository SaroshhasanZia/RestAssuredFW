package com.tests;

import com.constants.FrameworkConstants;
import com.fwannotations.TestReporter;
import com.reports.ExtentLogger;
import com.requestbuilder.RequestBuilder;
import com.utils.FakerUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class PutController {

    private String token = FrameworkConstants.getToken();

    @Test()
    @TestReporter(author = "sarosh",category = "UserController")
    public void updateUserProfile(){
        JSONObject obj = new JSONObject();
        obj.put("age", FakerUtils.getNumber(13,60));
        obj.put("firstName",FakerUtils.getFirstName());
        obj.put("gender",FakerUtils.getNumber(1,2));
        obj.put("lastName",FakerUtils.getLastName());

        RequestSpecification req = RequestBuilder.requestForPutCall()
                .header("Content-Type", ContentType.JSON)
                .header("Authorization", token)
                .body(obj.toMap());
        Response res = req.put("/");
        res.prettyPrint();
        ExtentLogger.logRequest(req);
        ExtentLogger.logResponse(res.prettyPrint());
        Assertions.assertThat(res.getStatusCode()).isEqualTo(200);
        Assertions.assertThat(res.jsonPath().getString("firstName")).isEqualTo(obj.getString("firstName"));
        Assertions.assertThat(res.jsonPath().getString("lastName")).isEqualTo(obj.getString("lastName"));
        Assertions.assertThat(res.jsonPath().getInt("age")).isEqualTo(obj.getInt("age"));
        Assertions.assertThat(res.jsonPath().getInt("gender")).isEqualTo(obj.getInt("gender"));



    }

    @Test()
    @TestReporter(author = "sarosh",category = "UserController")
    public void updateUserProfileWithInvalidGender(){
        JSONObject obj = new JSONObject();
        obj.put("age", FakerUtils.getNumber(13,60));
        obj.put("firstName",FakerUtils.getFirstName());
        obj.put("gender",FakerUtils.getNumber(3,5));
        obj.put("lastName",FakerUtils.getLastName());

        RequestSpecification req = RequestBuilder.requestForPutCall()
                .header("Content-Type", ContentType.JSON)
                .header("Authorization", token)
                .body(obj.toMap());
        Response res = req.put("/");
        res.prettyPrint();
        ExtentLogger.logRequest(req);
        ExtentLogger.logResponse(res.prettyPrint());
        Assertions.assertThat(res.getStatusCode()).isEqualTo(400);
        Assertions.assertThat(res.jsonPath().getString("message")).isEqualTo("Gender: 1 for male , 2 for female");



    }

}
