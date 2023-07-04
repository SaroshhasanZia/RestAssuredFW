package com.utils;

public final class RandomUtils {

    private RandomUtils(){}

    public static int getId(){
        return FakerUtils.getNumber(100,200);
    }

    public static String getFirstName(){
        return FakerUtils.getFirstName();
    }

    public static String getlastName(){
        return FakerUtils.getLastName();
    }

}
