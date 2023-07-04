package com.utils;

import io.restassured.response.Response;
import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class ApiUtils {

    private ApiUtils(){}
    @SneakyThrows
    public static String getStringFromJsonFile(String path)  {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    @SneakyThrows
    public static void storeStringAsJsonFile(String path, Response response)  {
         Files.write(Paths.get(path), response.asByteArray());
    }
}
