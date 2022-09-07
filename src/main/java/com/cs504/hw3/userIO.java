/***************************************
 * Chris Carmine
 * CS504 SU 21-22
 * Assignment 3
 * Version 0.01
***************************************/

package com.cs504.hw3;

import java.io.*;

public class userIO {

    public static String getLine(){
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        try {
            return userInput.readLine();
        } catch (IOException e) {
            return "Input read error.";
        }
    }

    
}
