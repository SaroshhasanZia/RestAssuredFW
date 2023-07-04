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

public class GetController {

    private String token = FrameworkConstants.getToken();



    @Test(dataProvider = "getData")
    @TestReporter(author = "sarosh",category = "GetCoursesController")
    public void GetCourseSessionsByIdEnglish(Integer data){
        RequestSpecification req = RequestBuilder.requestForGetCall()
                .header("ContentType", ContentType.JSON)
                .header("lang", "en")
                .header("Authorization", token)
                .pathParam("id", data);
        Response res = req.get("/");
        res.prettyPrint();
        ExtentLogger.logRequest(req);
        ExtentLogger.logResponse(res.prettyPrint());
        Assertions.assertThat(res.getStatusCode()).isEqualTo(200);
        Assertions.assertThat(res.jsonPath().getList("data")).hasSizeGreaterThan(2);


    }

    @Test(dataProvider = "getData")
    @TestReporter(author = "sarosh",category = "GetCoursesController")
    public void GetCourseSessionsByIdUrdu(Integer data){
        RequestSpecification req = RequestBuilder.requestForGetCall()
                .header("ContentType", ContentType.JSON)
                .header("lang", "roman-ur")
                .header("Authorization", token)
                .pathParam("id", data);
        Response res = req.get("/");
        res.prettyPrint();
        ExtentLogger.logRequest(req);
        ExtentLogger.logResponse(res.prettyPrint());
        Assertions.assertThat(res.getStatusCode()).isEqualTo(200);
        Assertions.assertThat(res.jsonPath().getList("data")).hasSizeGreaterThan(2);


    }

    @Test(dataProvider = "getData")
    @TestReporter(author = "sarosh",category = "GetCoursesController")
    public void GetCourseSessionsByIdPunjabi(Integer data){
        RequestSpecification req = RequestBuilder.requestForGetCall()
                .header("ContentType", ContentType.JSON)
                .header("lang", "punjabi")
                .header("Authorization", token)
                .pathParam("id", data);
        Response res = req.get("/");
        res.prettyPrint();
        ExtentLogger.logRequest(req);
        ExtentLogger.logResponse(res.prettyPrint());
        Assertions.assertThat(res.getStatusCode()).isEqualTo(200);
        Assertions.assertThat(res.jsonPath().getList("data")).hasSizeGreaterThan(2);


    }

    @Test(dataProvider = "getData")
    @TestReporter(author = "sarosh",category = "GetCoursesController")
    public void GetCourseSessionsByIdHindi(Integer data){
        RequestSpecification req = RequestBuilder.requestForGetCall()
                .header("ContentType", ContentType.JSON)
                .header("lang", "hindi")
                .header("Authorization", token)
                .pathParam("id", data);
        Response res = req.get("/");
        res.prettyPrint();
        ExtentLogger.logRequest(req);
        ExtentLogger.logResponse(res.prettyPrint());
        Assertions.assertThat(res.getStatusCode()).isEqualTo(200);
        Assertions.assertThat(res.jsonPath().getList("data")).hasSizeGreaterThan(2);


    }



    @DataProvider
    public Object[][] getData() {
        //first dim basically number of times you want to execute
        //second dim indicates number of parameters to the method
        return new Object[][]{  //3*2
                {3},
                {4},
                {5},
                {6},
                {7},
                {8},
                {9},
                {10}
        };
    }

}
