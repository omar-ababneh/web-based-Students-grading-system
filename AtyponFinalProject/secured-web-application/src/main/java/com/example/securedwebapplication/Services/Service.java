package com.example.securedwebapplication.Services;


public class Service {
    //Item4
    private Service(){
        throw new AssertionError();
    }

    public static String ExtractTypeUser(String Email){
        if (!Email.contains("@")||!Email.contains("."))
            return " ";
        String[] Words=Email.split("[@.]");
        return Words[1];
    }
}