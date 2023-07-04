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

public class GetDetails {

    private String token = FrameworkConstants.getToken();
//    private String version = "4.0.9.0";
//    private String platform = "2";

    @Test(dataProvider="getData")
    @TestReporter(author = "sarosh", category = "GetConstantDetails")
    public void getVersionCheck(String version, String platform) {

        RequestSpecification req = RequestBuilder.requestForPutCall()
                .header("Content-Type", ContentType.JSON)
                .header("Authorization", token)
                .pathParam("version",version)
                .queryParam("platform",platform);

        Response res = req.get("/");
        res.prettyPrint();
        ExtentLogger.logRequest(req);
        ExtentLogger.logResponse(res.prettyPrint());
        Assertions.assertThat(res.getStatusCode()).isEqualTo(200);
        Assertions.assertThat(res.jsonPath().getString("data.platform")).isEqualTo(platform);
        Assertions.assertThat(res.jsonPath().getString("data.isForceUpdateRequired")).isEqualTo("true");
    }

    @DataProvider
    public Object[][] getData() {
        //first dim basically number of times you want to execute
        //second dim indicates number of parameters to the method

        return new Object[][]{
                {"4.0.9.0","2"},{"1.0.7.0","1"},{"4.0.8.0","2"}

        };
    }

    @Test
    @TestReporter(author = "sarosh", category = "GetConstantDetails")
    public void versionCheckInvalidVersion() {

        RequestSpecification req = RequestBuilder.requestForPutCall()
                .header("Content-Type", ContentType.JSON)
                .header("Authorization", token)
                .pathParam("version","1.0.5")
                .queryParam("platform","1");

        Response res = req.get("/}");
        res.prettyPrint();
        ExtentLogger.logRequest(req);
        ExtentLogger.logResponse(res.prettyPrint());
        Assertions.assertThat(res.getStatusCode()).isEqualTo(404);
        Assertions.assertThat(res.jsonPath().getString("code")).isEqualTo("4000");
    }

    @Test
    @TestReporter(author = "sarosh", category = "GetConstantDetails")
    public void getReferralConstants() {

            RequestSpecification req = RequestBuilder.requestForPutCall()
                    .header("Content-Type", ContentType.JSON)
                    .header("Authorization", token);

            Response res = req.get("/");
            res.prettyPrint();
            ExtentLogger.logRequest(req);
            ExtentLogger.logResponse(res.prettyPrint());
            Assertions.assertThat(res.getStatusCode()).isEqualTo(200);
            Assertions.assertThat(res.jsonPath().getString("message")).isEqualTo("Request completed successfully");
            Assertions.assertThat(res.jsonPath().getString("data.sessionLimit")).isEqualTo("3");
            Assertions.assertThat(res.jsonPath().getString("data.maxRewardDays")).isEqualTo("30");
            Assertions.assertThat(res.jsonPath().getString("data.rewardConsumptionLimit")).isEqualTo("180");

    }


}
