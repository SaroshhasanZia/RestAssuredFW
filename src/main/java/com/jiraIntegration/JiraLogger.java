package com.jiraIntegration;


import com.constants.FrameworkConstants;
import com.enums.PropertyType;
import com.utils.PropertyUtil;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.ITestResult;


public final class JiraLogger {

    private JiraLogger(){}
    private static final String url = FrameworkConstants.getJiraURL();
    private static final String username =  FrameworkConstants.getJiraUser();
    private static final String pass = FrameworkConstants.getJiraPassword();
    private static final String project = PropertyUtil.getValue(PropertyType.JIRAPROJECT);

    public static void logJira(ITestResult result) {

        boolean islogIssue = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(JiraCreateIssue.class).isCreateIssue();

        if (islogIssue) {

            JiraServiceProvider JiraServiceProvider = new JiraServiceProvider(url,

                    username, pass, project);

            String issueDescription = "Failure Reason from Automation Testing\n\n" + result.getThrowable().getMessage()

                    + "\n";

            issueDescription.concat(ExceptionUtils.getFullStackTrace(result.getThrowable()));


            String issueSummary = result.getMethod().getConstructorOrMethod().getMethod().getName()

                    + " Failed in Automation Testing";



            JiraServiceProvider.createJiraIssue("Bug", issueSummary, issueDescription, "Appium Automated Testing");

        }

    }
}
