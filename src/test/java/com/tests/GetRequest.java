package com.tests;
import com.fwannotations.TestReporter;
import com.requestbuilder.RequestBuilder;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetRequest {

    @Test
    public void get(){

        Response response =
        RequestBuilder.requestForGetCall()
                .basePath("/api/list/category-list")
                .queryParam("categoryType","interior")
                .get();
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(),200    );
    }
}
