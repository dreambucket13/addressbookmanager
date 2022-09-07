/***************************************
 * Chris Carmine
 * CS504 SU 21-22
 * Assignment 3
 * Version 0.01
***************************************/

package com.cs504.hw3;

import java.util.ArrayList;

public class addressBookIndex {

    private ArrayList<addressBook> addressBooks = new ArrayList<>();
    private addressBook activeBook;
    private final String indexFileName;

    public addressBookIndex(String indexFileName) {

        this.addressBooks = fileIO.loadAddressBooks(indexFileName);
        this.indexFileName = indexFileName;
        activeBook = null;

    }

    public String getFileName(){
        return this.indexFileName;
    }

    public addressBook createBook(){
        
        System.out.println("Enter book name:");
        String bookName = userIO.getLine();

        if (this.exists(bookName)){
            System.out.println("Book with that name already exists.");
            return null;
        }

        addressBook newBook = new addressBook(bookName);
        addressBooks.add(newBook);
        activeBook = newBook;
        fileIO.saveAddressBooks(this);

        return newBook;
    }

    public boolean exists(String bookName){

        for (addressBook book : addressBooks){
            if (book.getName().equals(bookName)){
                return true;
            }
        }
        return false;
    }

    public int selectBook(String postMessage){

        System.out.println(postMessage);

        for (addressBook book : addressBooks){
            System.out.println(addressBooks.indexOf(book)+1 + ": " + book.getName() );
        }

        int selection;

        try {
            selection = Integer.parseInt(userIO.getLine());
        } catch (NumberFormatException e) {
            return -1;
        }
        
        if (selection > addressBooks.size()){
            return -1;
        }

        return selection - 1;
    }


    public addressBook openBook(){

        if (addressBooks.size() == 0){
            System.out.println("No saved books, returning...");
            return null;
        }

        int selection = selectBook("Select book to open");

        if (selection < 0){
            System.out.println("Invalid input, active book not changed.");
            return activeBook;
        }

        activeBook = addressBooks.get(selection);
        return activeBook;

    }

    public addressBook getActiveBook(){
        return activeBook;
    }

    public boolean deleteBook(){

        if (addressBooks.size() == 0){
            System.out.println("No saved books, returning...");
            return false;
        }

        int selection = selectBook("Select book to delete:");

        if (selection < 0){
            System.out.println("Invalid input, no book deleted.");
            return false;
        }

        addressBook bookToRemove = addressBooks.get(selection);

        if (bookToRemove == activeBook){
            activeBook = null;
        }

        addressBooks.remove(bookToRemove);

        fileIO.saveAddressBooks(this);
        return true;
    }

    public boolean renameBook(){

        if (addressBooks.size() == 0){
            System.out.println("No saved books, returning...");
            return false;
        }

        int selection = selectBook("Select book to rename:");

        if (selection < 0){
            System.out.println("Invalid input.");
            return false;
        }

        System.out.println("Type new name:");
        String newName = userIO.getLine();

        if (this.exists(newName)){
            System.out.println("Book with this name already exists");
            return false;
        }

        addressBooks.get(selection).setName(newName);
        fileIO.saveAddressBooks(this);

        return true;
    }

    public void printSummary(){

        if (addressBooks.size() == 0){
            System.out.println("No saved books, returning...");
            return;
        }
        System.out.println("Your summary of address books:");
        for (addressBook book : addressBooks){
            if (book == null){
                break;
            }
            System.out.println(addressBooks.indexOf(book)+1 + ": " + book.getName() + ", entries: " + book.getSize() );
        }
        System.out.println();

    }

    public ArrayList<addressBook> getIndex(){
        return addressBooks;
    }


    //wrappers for mutating entries.  I want to keep fileIO out of the state machine.
    public void editEntry(){
        activeBook.editEntry(activeBook.selectEntry());
        fileIO.saveAddressBooks(this);
    }

    public void addEntry(){
        activeBook.addEntry();
        fileIO.saveAddressBooks(this);
    }

    public void deleteEntry(){
        activeBook.deleteEntry(activeBook.selectEntry());
        fileIO.saveAddressBooks(this);
    }


    
}
