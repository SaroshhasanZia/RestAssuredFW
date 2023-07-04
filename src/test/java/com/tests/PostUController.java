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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class PostUController {

    private String token = FrameworkConstants.getToken();

    @Test(dataProvider="getFeedbackData")
    @TestReporter(author = "sarosh", category = "PostUserController")
    public void postUserFeedback(Integer data) {
        JSONObject obj = new JSONObject();
        obj.put("feedback", FakerUtils.getCommments());
        obj.put("rating", FakerUtils.getNumber(1, 5));
        obj.put("submittedAt",FakerUtils.getDateTimeForFeedback());

        RequestSpecification req = RequestBuilder.requestForPutCall()
                .header("Content-Type", ContentType.JSON)
                .header("Authorization", token)
                .pathParam("id", data)
                .body(obj.toMap());
        Response res = req.post("//{id}");
        res.prettyPrint();
        ExtentLogger.logRequest(req);
        ExtentLogger.logResponse(res.prettyPrint());
        Assertions.assertThat(res.getStatusCode()).isEqualTo(200);
        Assertions.assertThat(res.jsonPath().getString("message")).isEqualTo("Request completed successfully");

    }



    @DataProvider
    public Object[][] getFeedbackData() {
        //first dim basically number of times you want to execute
        //second dim indicates number of parameters to the method
        RequestSpecification req = RequestBuilder.requestForGetCall()
                .header("Content-Type", ContentType.JSON)
                .header("lang", "en")
                .header("Authorization", token);
        Response res = req.get("/");
        List<Integer> list = res.jsonPath().getList("data.id");
        Object[][] data = new Object[list.size()][1];
        for (int i=0;i< list.size();i++){
            data[i][0] = list.get(i);
        }

        return data;


    }

    @Test(dataProvider="getData")
    @TestReporter(author = "sarosh", category = "PostUserController")
    public void postSessionCompleteUpdate(Integer data) {
        JSONObject obj = new JSONObject();
        obj.put("completedAt",FakerUtils.getDateTimeForFeedback());

        RequestSpecification req = RequestBuilder.requestForPutCall()
                .header("Content-Type", ContentType.JSON)
                .header("Authorization", token)
                .pathParam("id", data)
                .body(obj.toMap());
        Response res = req.post("/{id}");
        res.prettyPrint();
        ExtentLogger.logRequest(req);
        ExtentLogger.logResponse(res.prettyPrint());
        Assertions.assertThat(res.getStatusCode()).isEqualTo(200);
        Assertions.assertThat(res.jsonPath().getString("message")).isEqualTo("Request completed successfully");
    }

    @DataProvider
    public Object[][] getData() {
        //first dim basically number of times you want to execute
        //second dim indicates number of parameters to the method

        return new Object[][]{
                {15},{16},{17},{18}

        };
    }

}
