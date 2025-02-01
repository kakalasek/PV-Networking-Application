package com.Utils.InputHandler;

public class InputHandler {

    public static boolean isValidLong(String s){
        try{
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
}
