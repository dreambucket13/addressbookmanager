/***************************************
 * Chris Carmine
 * CS504 SU 21-22
 * Assignment 3
 * Version 0.01
***************************************/

package com.cs504.hw3;

import java.io.*;
import java.util.ArrayList;

import com.google.gson.Gson;

public class fileIO {
    
    public static ArrayList<addressBook> loadAddressBooks(String fileName){

        File file=new File(fileName);

        //if the index file doesn't exist yet, return an empty book index
        if (!file.exists() || file.length() == 0){
            return new ArrayList<addressBook>();
        }

        String line;
        Gson gson = new Gson();

        ArrayList<addressBook> addressBooks = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while((line=br.readLine())!=null){
                addressBook book = gson.fromJson(line, addressBook.class);
                addressBooks.add(book);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Failed to load address books.");
            return null;
        }



        return addressBooks;

    }

    public static void saveAddressBooks(addressBookIndex bookIndex){

        Gson gson = new Gson();
        String fileName = bookIndex.getFileName();

        ArrayList<addressBook> books = bookIndex.getIndex();

        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        try {
            
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));

            for (addressBook book : books){
                gson.toJson(book, writer);
                writer.print("\n");
        }

        writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}
