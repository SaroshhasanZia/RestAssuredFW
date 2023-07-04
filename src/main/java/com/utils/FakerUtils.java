package com.utils;

import com.github.javafaker.DateAndTime;
import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class FakerUtils {

    private FakerUtils(){}

    private static Faker faker = new Faker();

     public static int getNumber(int startValue, int endValure){
        return faker.number().numberBetween(startValue,endValure);

    }

    public static String getFirstName(){
        return faker.name().firstName().toLowerCase();
    }

    public static String getLastName(){
        return faker.name().lastName().toLowerCase();
    }


    public static String getCommments(){
         return faker.shakespeare().hamletQuote();
    }

    public static String getDateTime(){

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            return dtf.format(now);
        }

    public static String getDateTimeForFeedback(){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

   }




