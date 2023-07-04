package com.reports;


import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.SpecificationQuerier;

import java.util.Objects;

import static com.reports.ReportManager.getExtentTest;

public final class ExtentLogger {

    private ExtentLogger(){}

    public static void pass(String message){
        getExtentTest().pass(message);
    }

    public static void fail(String message){
        getExtentTest().fail(MarkupHelper.createLabel(message, ExtentColor.RED));;

    }

    public static void skip(String Message){
        getExtentTest().skip(Message);
    }

    public static void info(String message){
        getExtentTest().info(message);
    }

    public static void logResponse(String response){
        getExtentTest().info(MarkupHelper.createLabel("Response Details:", ExtentColor.BLUE));
        getExtentTest().info(MarkupHelper.createCodeBlock(response, CodeLanguage.JSON));

    }

    public static void logRequest(RequestSpecification requestSpecification){
        QueryableRequestSpecification query = SpecificationQuerier.query(requestSpecification);
        getExtentTest().info(MarkupHelper.createLabel("Request Details:", ExtentColor.BLUE));
        if(Objects.nonNull(query.getBody())) {
            getExtentTest().info(MarkupHelper.createCodeBlock(query.getBody(), CodeLanguage.JSON));
        }
        for(Header header: query.getHeaders()){
            info(header.getName()+":"+header.getValue());
        }
    }
}
