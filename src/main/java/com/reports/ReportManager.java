package com.reports;

import com.aventstack.extentreports.ExtentTest;


    public class ReportManager {

        private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();


         public static ExtentTest getExtentTest() {

            return test.get();
        }

         static void setExtentTest(ExtentTest testvalue) {

             test.set(testvalue);
        }

         static void unload() {
            test.remove();
        }
    }


