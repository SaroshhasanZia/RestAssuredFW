package com.constants;

import lombok.Getter;

public final class FrameworkConstants {

    private static @Getter final String requestJsonFile = System.getProperty("user.dir")+"/requestfile/test.json";
    private static @Getter final String responseJsonFile = System.getProperty("user.dir")+"/responsefile";
    private static @Getter final String propertyFile = System.getProperty("user.dir")+"/src/main/resources/config.properties";
    private static @Getter final String reportPath = System.getProperty("user.dir") + "/index.html";

    private static @Getter final String token = System.getenv("Token");
    private static @Getter final String dbHost = System.getenv("Sonarurl");
    private static @Getter final String dbUserName = System.getenv("Sonaruser");
    private static @Getter final String dbPassword = System.getenv("Sonarpwd");

    private static @Getter final String jiraURL = System.getenv("JiraUrl");
    private static @Getter final String jiraUser = System.getenv("JiraUsername");
    private static @Getter final String jiraPassword = System.getenv("JiraPass");

}
