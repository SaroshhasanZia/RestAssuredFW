package com.tests;

import com.constants.FrameworkConstants;
import com.fwannotations.TestReporter;
import com.jiraIntegration.JiraCreateIssue;
import com.reports.ExtentLogger;
import com.requestbuilder.RequestBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;

public class GetUser {

    private String token = FrameworkConstants.getToken();


    @Test
    @TestReporter(author = "sarosh",category = "GetUserController")
    public void GetUserSessionsProgress(){
        RequestSpecification req = RequestBuilder.requestForGetCall()
                    .header("Content-Type", ContentType.JSON)
                    .header("lang", "en")
                    .header("Authorization", token);
            Response res = req.get("/users/progress");
            res.prettyPrint();
        ExtentLogger.logRequest(req);
        ExtentLogger.logResponse(res.prettyPrint());
        Assertions.assertThat(res.getStatusCode()).isEqualTo(200);


    }

    @Test
    @TestReporter(author = "sarosh",category = "GetUserController")
    public void GetUserDetails(){
        RequestSpecification req = RequestBuilder.requestForGetCall()
                .header("Content-Type", ContentType.JSON)
                .header("lang", "en")
                .header("Authorization", token);
        Response res = req.get("/");
        res.prettyPrint();
        ExtentLogger.logRequest(req);
        ExtentLogger.logResponse(res.prettyPrint());
        Assertions.assertThat(res.getStatusCode()).isEqualTo(200);


    }

    @Test
    @TestReporter(author = "sarosh",category = "UserControllerAPIs")
    public void GetUserSessions(){
        RequestSpecification req = RequestBuilder.requestForGetCall()
                .header("Content-Type", ContentType.JSON)
                .header("lang", "en")
                .header("Authorization", token);
        Response res = req.get("/");
        res.prettyPrint();
//        ExtentLogger.logRequest(req);
//        ExtentLogger.logResponse(res.prettyPrint());
        Assertions.assertThat(res.getStatusCode()).isEqualTo(200);


    }

    @Test
    @JiraCreateIssue(isCreateIssue = true)
    @TestReporter(author = "sarosh",category = "UserControllerAPIs")
    public void GetUserEncryptedData(){
        RequestSpecification req = RequestBuilder.requestForGetCall()
                .header("Content-Type", ContentType.JSON)
                .header("Authorization", token);
        Response res = req.get("/");
        res.prettyPrint();
        ExtentLogger.logRequest(req);
        ExtentLogger.logResponse(res.prettyPrint());
        Assertions.assertThat(res.getStatusCode()).isEqualTo(200);


    }

    @Test
    @TestReporter(author = "sarosh",category = "UserControllerAPIs")
    public void GetUserDataInSheet() throws IOException {
        RequestSpecification req = RequestBuilder.requestForGetCall()
                .header("Content-Type", ContentType.JSON)
                .header("lang", "en")
                .header("Authorization", token);
        Response res = req.get("/");
        res.prettyPrint();

        ExtentLogger.logRequest(req);
        ExtentLogger.logResponse(res.prettyPrint());
//
//        String downloadFolder = "Downloads";
//        File outputPath = new File(downloadFolder);
//        outputPath.mkdirs();
//
//        File outputFile = new File(outputPath.getPath(), "sheet.xlsx");
//        byte[] fileContents = res.then().extract().asByteArray();
//
//        // output contents to file
//        OutputStream outStream=null;
//
//        try {
//
//            outStream = new FileOutputStream(outputFile);
//            outStream.write(fileContents);
//
//        }catch(Exception e){
//
//            System.out.println("Error writing file " + outputFile.getAbsolutePath());
//
//        }finally {
//
//            if(outStream!=null){
//                outStream.close();
//            }
//        }

    //    ApiUtils.storeStringAsJsonFile(System.getProperty("user.dir"+"/sheet.csv"),res);
//        ExtentLogger.logRequest(req);
//        ExtentLogger.logResponse(res.prettyPrint());
        Assertions.assertThat(res.getStatusCode()).isEqualTo(200);
        Assertions.assertThat(res.getBody()).isNotNull();



    }

    @Test(dataProvider = "getData")
    @TestReporter(author = "sarosh",category = "UserControllerAPIs")
    public void GetUserSessionProgressById(Integer id) throws IOException {
        RequestSpecification req = RequestBuilder.requestForGetCall()
                .header("Content-Type", ContentType.JSON)
                .header("lang", "en")
                .header("Authorization", token)
                .pathParam("id", id);

        Response res = req.get("/");
        res.prettyPrint();
        ExtentLogger.logRequest(req);
        ExtentLogger.logResponse(res.prettyPrint());
        Assertions.assertThat(res.getStatusCode()).isEqualTo(200);
        Assertions.assertThat(res.getBody()).isNotNull();


    }

    @DataProvider
    public Object[][] getData() {
        //first dim basically number of times you want to execute
        //second dim indicates number of parameters to the method

        return new Object[][]{  //3*2
                {2},{3}, {4}, {5}

        };
    }

}
