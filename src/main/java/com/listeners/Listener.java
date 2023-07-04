package com.listeners;


import com.enums.PropertyType;
import com.fwannotations.TestReporter;
import com.reports.ExtentLogger;
import com.reports.ExtentReport;
import com.utils.FakerUtils;
import com.utils.PropertyUtil;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;


import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import static com.database.DatabaseExecution.updateCQM;
import static com.jiraIntegration.JiraLogger.logJira;


public class Listener implements ITestListener, ISuiteListener {

     String startTime = null;
     String endTime = null;
     Integer passedCount = 0;
     Integer failedCount = 0;
     Integer skippedCount = 0;
     String coverage = PropertyUtil.getValue(PropertyType.COVERAGE);
     String componentID = PropertyUtil.getValue(PropertyType.COMPONENTID);


    String Iquery = "INSERT INTO sonardb.automation_report (Project_Name,Platform,Build,Environment,Component_id,Commit_Number,Branch_Name,Execution_Date,Total_Cases,Passed,Failed,Skipped,Execution_Start_Time,Execution_End_Time,Coverage,IsCoverageFromTestRail,Repository_Name)\n" +
            "\tVALUES ('Hilton','BE','1','QA',ComponentId,'null','null','executionDate',totalcases,passed,failed,skipped,'executionStartTime','executionEndTime',coverage,0,'Testing');";

    @Override
    public void onTestStart(ITestResult result) {
        ExtentReport.createTest(result.getName());


            if (result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(TestReporter.class)!=null){
            String[] authors = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(TestReporter.class).author();
            ExtentReport.addAuthor(authors);
            String[] category = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(TestReporter.class).category();
            ExtentReport.addCategory(category);






        }
    }



    @Override
    public  void onTestSuccess(ITestResult result) {
        ExtentLogger.pass(result.getName()+ " is passed");
        passedCount+=1;

    }

    @Override
    public  void onTestFailure(ITestResult result) {
        ExtentLogger.fail(String.valueOf(result.getThrowable()));
        failedCount+=1;
//        logJira(result);

    }

    @Override
    public  void onTestSkipped(ITestResult result) {
        ExtentLogger.skip(result.getName()+ " is skipped");
        skippedCount+=1;

    }


    @Override
    public  void onStart(ISuite suite) {
        ExtentReport.initReport();
        startTime = FakerUtils.getDateTime();

    }

    @Override
    public  void onFinish(ISuite suite) {
        ExtentReport.tearDownReport();
        int totalcases = passedCount + failedCount + skippedCount;
        endTime = FakerUtils.getDateTime();
        String query = Iquery.replace("executionDate", (startTime)).replace("totalcases", String.valueOf(totalcases)).replace("passed", String.valueOf(passedCount))
                .replace("failed", String.valueOf(failedCount)).replace("skipped", String.valueOf(skippedCount)).replace("executionStartTime", (startTime))
                .replace("executionEndTime", (endTime)).replace("coverage", coverage).replace("ComponentId", componentID);
        try {
            updateCQM(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);


        }


    }
}
