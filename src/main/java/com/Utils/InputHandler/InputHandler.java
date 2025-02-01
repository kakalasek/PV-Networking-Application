package com.Utils.InputHandler;

/**
 * Provides a few handy methods for handling user input
 */
public class InputHandler {

    /**
     * Check if the string provided is a valid long variable
     * @param s The string you want to test
     * @return If the string can be successfully parsed to long, returns true, otherwise returns false
     */
    public static boolean isValidLong(String s){
        try{
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
}
