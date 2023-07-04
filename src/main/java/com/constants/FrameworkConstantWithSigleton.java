package com.constants;

public class FrameworkConstantWithSigleton {  //Single ton-->Single instance for an class at a particular point of time
    //creational design pattern

    private static FrameworkConstantWithSigleton INSTANCE=null;

    private FrameworkConstantWithSigleton(){}

    public static synchronized FrameworkConstantWithSigleton getInstance(){ //10 threads
        if(INSTANCE==null){
            INSTANCE=new FrameworkConstantWithSigleton();
        }
        return INSTANCE;
    }

    private  final String requestJsonFile = System.getProperty("user.dir")+"/requestfile/test.json";
    private  final String responseJsonFile = System.getProperty("user.dir")+"/responsefile";
}
