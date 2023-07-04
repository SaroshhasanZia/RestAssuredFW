package com.tests;

import com.constants.FrameworkConstants;
import com.fwannotations.TestReporter;
import com.reports.ExtentLogger;
import com.requestbuilder.RequestBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GetSsController {

    private String token = FrameworkConstants.getToken();


    @Test(dataProvider = "getData")
    @TestReporter(author = "sarosh",category = "GetSessionsController")
    public void GetSessionsByIdEnglish(Integer data){
        RequestSpecification req = RequestBuilder.requestForGetCall()
                .header("ContentType", ContentType.JSON)
                .header("lang", "en")
                .header("Authorization", token)
                .pathParam("id", data);
        Response res = req.get("/{id}");
        res.prettyPrint();
        ExtentLogger.logRequest(req);
        ExtentLogger.logResponse(res.prettyPrint());
        Assertions.assertThat(res.getStatusCode()).isEqualTo(200);
        Assertions.assertThat(res.jsonPath().getInt("data.id")).isEqualTo(data);


    }

    @Test(dataProvider = "getData")
    @TestReporter(author = "sarosh",category = "GetSessionsController")
    public void GetSessionsByIdUrdu(Integer data){
        RequestSpecification req = RequestBuilder.requestForGetCall()
                .header("ContentType", ContentType.JSON)
                .header("lang", "roman-ur")
                .header("Authorization", token)
                .pathParam("id", data);
        Response res = req.get("/{id}");
        res.prettyPrint();
        ExtentLogger.logRequest(req);
        ExtentLogger.logResponse(res.prettyPrint());
        Assertions.assertThat(res.getStatusCode()).isEqualTo(200);
        Assertions.assertThat(res.jsonPath().getInt("data.id")).isEqualTo(data);


    }

    @Test(dataProvider = "getData")
    @TestReporter(author = "sarosh",category = "GetSessionsController")
    public void GetSessionsByIdPunjabi(Integer data){
        RequestSpecification req = RequestBuilder.requestForGetCall()
                .header("ContentType", ContentType.JSON)
                .header("lang", "punjabi")
                .header("Authorization", token)
                .pathParam("id", data);
        Response res = req.get("/{id}");
        res.prettyPrint();
        ExtentLogger.logRequest(req);
        ExtentLogger.logResponse(res.prettyPrint());
        Assertions.assertThat(res.getStatusCode()).isEqualTo(200);
        Assertions.assertThat(res.jsonPath().getInt("data.id")).isEqualTo(data);


    }

    @Test(dataProvider = "getData")
    @TestReporter(author = "sarosh",category = "GetSessionsController")
    public void GetSessionsByIdHindi(Integer data){
        RequestSpecification req = RequestBuilder.requestForGetCall()
                .header("ContentType", ContentType.JSON)
                .header("lang", "hindi")
                .header("Authorization", token)
                .pathParam("id", data);
        Response res = req.get("/{id}");
        res.prettyPrint();
        ExtentLogger.logRequest(req);
        ExtentLogger.logResponse(res.prettyPrint());
        Assertions.assertThat(res.getStatusCode()).isEqualTo(200);
        Assertions.assertThat(res.jsonPath().getInt("data.id")).isEqualTo(data);


    }





    @DataProvider
    public Object[][] getData() {
        //first dim basically number of times you want to execute
        //second dim indicates number of parameters to the method

        return new Object[][]{  //3*2
                {4},{5}, {6}, {7}, {8}, {9}, {10},{11},{12},{13},{14},{15},{16},{17},{18},{19},{20},
                {21},{22},{23}, {24}, {25}, {26}, {27},{28},{29},{30},{31},{32},{33}

        };
    }
}
