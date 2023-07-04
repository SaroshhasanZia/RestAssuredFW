package com.tests;

import com.constants.FrameworkConstants;
import com.github.javafaker.Faker;
import com.pojo.employee;
import com.requestbuilder.RequestBuilder;
import com.utils.ApiUtils;
import com.utils.RandomUtils;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jdk.nashorn.internal.runtime.regexp.joni.exception.SyntaxException;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import static com.reports.ExtentLogger.logRequest;
import static com.reports.ExtentLogger.logResponse;
import static io.restassured.RestAssured.given;

public class PostRequest {

    @Test
    public void postViaString(){
        Response response = given().header("Content-Type", ContentType.JSON)
                .baseUri("https://dummy.restapiexample.com/api/v1")
                .basePath("/create")
                .body("\t{\"name\":\"test\",\"salary\":\"123\",\"age\":\"23\"}")
                .log()
                .all()
                .post();
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test
    public void postViaJsonFile() throws IOException {

        String body = ApiUtils.getStringFromJsonFile(FrameworkConstants.getRequestJsonFile())
        .replace("ageval", String.valueOf(RandomUtils.getId()));

        RequestSpecification req = RequestBuilder.requestForPostCall()
                .basePath("/create")
                .body(body);

        logRequest(req);

        Response response = req
                .post();
        response.prettyPrint();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);
        ApiUtils.storeStringAsJsonFile(FrameworkConstants.getResponseJsonFile()+"/response.json",response);
        logResponse(response.prettyPrint());

    }

    @Test
    public void postViaMaps() throws IOException {
        //{} Map
        //[] list

        Map<String,Object> obj = new HashMap<>();
        obj.put("name",new Faker().name().firstName());
        obj.put("salary","250");
        obj.put("age",new Faker().number().numberBetween(100,150));

        Response response = given().header("Content-Type", ContentType.JSON)
                .baseUri("https://dummy.restapiexample.com/api/v1")
                .basePath("/create")
                .body(obj)
                .log()
                .all()
                .post();
        response.prettyPrint();
        logResponse(response.prettyPrint());
        Assert.assertEquals(response.getStatusCode(),201);


    }

    @Test
    public void postViaPOJO() throws IOException {
        employee body = employee.builder().setAge("25").setName("TesterQA").setSalary("2500").build();

        Response response = given().header("Content-Type", ContentType.JSON)
                .baseUri("https://dummy.restapiexample.com/api/v1")
                .basePath("/create")
                .body(body)
                .log()
                .all()
                .post();
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(),200);
//        employee emp = response.as(employee.class);
//        System.out.println(emp.getAge());
//        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));

        Files.write(Paths.get(System.getProperty("user.dir") + "/response.json"),response.asByteArray());
    }

}
