package com.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.constants.FrameworkConstants;

import java.util.Objects;

import static com.reports.ReportManager.unload;

public class ExtentReport {


    static ExtentReports extent;

    public static void initReport() {
        if (Objects.isNull(extent)) {
            extent = new ExtentReports();
            ExtentSparkReporter spark = new ExtentSparkReporter(FrameworkConstants.getReportPath());
            spark.config().setTheme(Theme.DARK);
            spark.config().setReportName("API Automation Report");
            spark.config().setDocumentTitle("Tests");
            extent.attachReporter(spark);
        }
    }


    public static void tearDownReport() {
        if (Objects.nonNull(extent)) {
            extent.flush();
            unload();
        }
    }

    public static void createTest(String testName) {

        ReportManager.setExtentTest(extent.createTest(testName));
    }
    public static void addAuthor(String[] authors){
        for(String author:authors) {
            ReportManager.getExtentTest().assignAuthor(author);
        }
    }

    public static void addCategory(String[] categories){
        for(String category:categories) {
            ReportManager.getExtentTest().assignCategory(category);
        }
    }
}
