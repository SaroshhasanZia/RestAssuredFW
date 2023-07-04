package com.tests;

import com.constants.FrameworkConstants;
import com.fwannotations.TestReporter;
import com.reports.ExtentLogger;
import com.requestbuilder.RequestBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class PostController {

    private String token = FrameworkConstants.getToken();
    private String referralCode = "74584MZ";

    @Test
    @TestReporter(author = "sarosh", category = "PostReferralController")
    public void generateReferralCode() {

        RequestSpecification req = RequestBuilder.requestForPutCall()
                .header("Content-Type", ContentType.JSON)
                .header("Authorization", token);

        Response res = req.post("");
        res.prettyPrint();
        ExtentLogger.logRequest(req);
        ExtentLogger.logResponse(res.prettyPrint());
        Assertions.assertThat(res.getStatusCode()).isEqualTo(200);
        Assertions.assertThat(res.jsonPath().getString("data.referralCode")).isNotEmpty();

    }

    @Test
    @TestReporter(author = "sarosh", category = "PostReferralController")
    public void consumeReferralCode() {

        RequestSpecification req = RequestBuilder.requestForPutCall()
                .header("Content-Type", ContentType.JSON)
                .header("Authorization", token)
                .pathParam("code",referralCode);

            Response res = req.post("/");
        res.prettyPrint();
        ExtentLogger.logRequest(req);
        ExtentLogger.logResponse(res.prettyPrint());
        Assertions.assertThat(res.getStatusCode()).isEqualTo(400);
        Assertions.assertThat(res.jsonPath().getString("message")).isEqualTo("You have already consumed a referral code");

    }
}
