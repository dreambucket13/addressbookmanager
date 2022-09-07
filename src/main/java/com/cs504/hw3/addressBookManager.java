/***************************************
 * Chris Carmine
 * CS504 SU 21-22
 * Assignment 3
 * Version 0.01
***************************************/

package com.cs504.hw3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class addressBookManager {

    public static void main(String[] args) {


    addressBookIndex bookIndex = new addressBookIndex("index.txt");
    addressBook activeBook;

    programStateAndBookIndex programState = new programStateAndBookIndex(State.MAIN, bookIndex); 

    ArrayList<String> menuOptions;

    do{

        menuOptions = programState.getProgramState().getOptions();
        activeBook = bookIndex.getActiveBook();

        System.out.println("State: " + programState.getProgramState().toString());
        if (activeBook != null){
            System.out.println("Active book: " + activeBook.getName());
        } else {
            System.out.println("Active book: NONE");
        }
        
        for (String m : menuOptions){
            System.out.println(m);
        }

        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        
        String userInput = "";

        try {
            userInput = keyboard.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //STATE HANDLING
        try{
            programState = programState.getProgramState().updateState(Integer.parseInt(userInput), bookIndex);
            bookIndex = programState.getBookIndex();

        } 
        catch (NumberFormatException num) {
            System.out.println("Input must be a number.");
        }

    } while (programState.getProgramState() != State.QUIT);
    
}

    
}
