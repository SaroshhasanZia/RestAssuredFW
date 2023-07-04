package com.jiraIntegration;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)

public @interface JiraCreateIssue {


    public abstract boolean isCreateIssue();

}

