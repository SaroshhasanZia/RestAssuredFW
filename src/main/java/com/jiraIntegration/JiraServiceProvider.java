package com.jiraIntegration;// JiraServiceProvider.java

import net.rcarz.jiraclient.*;

import java.util.ArrayList;


public class JiraServiceProvider {


    private final JiraClient Jira;

    private final String project;

    private static String JiraUrl;

    public JiraServiceProvider(String JiraUrl, String username, String password, String project) {

        JiraServiceProvider.JiraUrl = JiraUrl;

        // create basic authentication object

        BasicCredentials creds = new BasicCredentials(username, password);

        // initialize the Jira client with the url and the credentials

        Jira = new JiraClient(JiraUrl, creds);

        this.project = project;

    }


    public void createJiraIssue(String issueType, String summary, String description, String reporterName) {


        try {

            //Avoid Creating Duplicate Issue

            Issue.SearchResult sr = Jira.searchIssues("summary ~ \"" + summary + "\"");

            if (sr.total != 0) {

                System.out.println("Same Issue Already Exists on Jira");

                return;

            }


            //Create issue if not exists

            Issue.FluentCreate fleuntCreate = Jira.createIssue(project, issueType);

            fleuntCreate.field(Field.SUMMARY, summary);

            fleuntCreate.field(Field.DESCRIPTION, description);

            fleuntCreate.field(Field.LABELS,new ArrayList() {{add("T&M");}});
//            fleuntCreate.field(Field.ASSIGNEE,new ArrayList() {{add("\"sarosh.hasan@venturedive.com\"");}});


            Issue newIssue = fleuntCreate.execute();

            System.out.println("********************************************");

            System.out.println("New issue created in Jira with ID: " + newIssue);

            System.out.println("New issue URL is :" + JiraUrl + "/browse/" + newIssue);

            System.out.println("*******************************************");

        } catch (JiraException e) {

            e.printStackTrace();

        }

    }

}