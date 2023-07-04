package com.requestbuilder;

import com.enums.PropertyType;
import com.utils.PropertyUtil;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public final class RequestBuilder {

    private RequestBuilder(){}

    public static RequestSpecification requestForGetCall(){
        return given()
                .baseUri(PropertyUtil.getValue(PropertyType.BASEURL))
                .log()
                .all();
    }

    public static RequestSpecification requestForPostCall(){
        return given()
                .baseUri(PropertyUtil.getValue(PropertyType.BASEURL))
                .log()
                .all()
                .contentType(ContentType.JSON);
    }

    public static RequestSpecification requestForPutCall(){
        return given()
                .baseUri(PropertyUtil.getValue(PropertyType.BASEURL))
                .log()
                .all()
                .contentType(ContentType.JSON);
    }


}
