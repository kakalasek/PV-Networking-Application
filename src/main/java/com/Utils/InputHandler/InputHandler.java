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

    /**
     * Check if string provided is a valid port
     * @param s The string you want to test
     * @return If the string is a valid port return true, otherwise return false
     */
    public static boolean isValidPort(String s){
        try{
            int port = Integer.parseInt(s);
            if(port < 0 || port > 65535) throw new NumberFormatException();
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    /**
     * Check if string provided is a valid timeout
     * @param s The string you want to test
     * @return If the string is a valid timeout return true, otherwise return false
     */
    public static boolean isValidTimeout(String s){
        try{
            int timeout = Integer.parseInt(s);
            if(timeout < 0) throw new NumberFormatException();
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
}
