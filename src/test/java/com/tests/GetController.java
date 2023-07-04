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

import java.util.ArrayList;
import java.util.List;

public class GetController {

    private String token = FrameworkConstants.getToken();



    @Test
    @TestReporter(author = "sarosh", category = "GetCategoryController")
    public void getCategoriesInEnglish() {

        RequestSpecification req = RequestBuilder.requestForGetCall().header("ContentType", ContentType.JSON)
                .header("lang", "en")
                .header("Authorization", token);
        Response res = req.get("/");
        ExtentLogger.logRequest(req);
        ExtentLogger.logResponse(res.prettyPrint());
        res.prettyPrint();
        Assertions.assertThat(res.jsonPath().getList("data.id")).hasSize(4);
    }

    @Test
    @TestReporter(author = "sarosh", category = "GetCategoryController")
    public void getCategoriesInEnglishAndVerfiyTitle() {

        RequestSpecification req = RequestBuilder.requestForGetCall().header("ContentType", ContentType.JSON)
                .header("lang", "en")
                .header("Authorization", token);
        Response res = req.get("/");
        ExtentLogger.logRequest(req);
        ExtentLogger.logResponse(res.prettyPrint());
        res.prettyPrint();
        List<String> name = new ArrayList<>();
        name.add("abc");
        name.add("Sabc");
        Assertions.assertThat(res.jsonPath().getList("data.name")).containsOnlyElementsOf(name);
    }



    @Test(dataProvider = "getData")
    @TestReporter(author = "sarosh", category = "GetCategoryController")
    public void getCategoriesByIdEnglish(Integer data) {

        RequestSpecification req = RequestBuilder.requestForGetCall().header("ContentType", ContentType.JSON)
                .header("lang", "en")
                .header("Authorization", token)
                .pathParam("id", data);

        Response res = req.get("//{id}/courses");
        ExtentLogger.logRequest(req);
        ExtentLogger.logResponse(res.prettyPrint());
        res.prettyPrint();
        Assertions.assertThat(res.getStatusCode()).isEqualTo(200);
        Assertions.assertThat(res.jsonPath().getList("data.id")).hasSize(2);
        List<String> categories = new ArrayList<>();
        categories.add("Singles");
        categories.add("Course");
        Assertions.assertThat(res.jsonPath().getList("data.name")).containsOnlyElementsOf(categories);


    }







    @Test(dataProvider = "getData")
    @TestReporter(author = "sarosh", category = "GetCategoryController")
    public void getCategoryQuotesByIdEnglish(Integer data) {
        RequestSpecification req = RequestBuilder.requestForGetCall()
                .header("ContentType", ContentType.JSON)
                .header("Authorization", token)
                .header("lang", "en")
                .pathParam("id", data);

        Response res = req.get("categories/{id}/quotes");
        res.prettyPrint();
        ExtentLogger.logRequest(req);
        ExtentLogger.logResponse(res.prettyPrint());
        Assertions.assertThat(res.getStatusCode()).isEqualTo(200);
        Assertions.assertThat(res.jsonPath().getString("data")).isNotEmpty();
    }


    @DataProvider
    public Object[][] getData() {
        //first dim basically number of times you want to execute
        //second dim indicates number of parameters to the method
        return new Object[][]{  //3*2
                {2},
                {3},
                {4},
                {5}
        };
    }


}

