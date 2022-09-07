/***************************************
 * Chris Carmine
 * CS504 SU 21-22
 * Assignment 3
 * Version 0.01
 ***************************************/

package com.cs504.hw3;

import java.util.ArrayList;

public enum State {

    MAIN {
        @Override
        programStateAndBookIndex updateState(int selection, addressBookIndex bookIndex) {
            
            activeBook = bookIndex.getActiveBook();

            switch (selection) {
                default:
                    System.out.println("Invalid selection");
                    return new programStateAndBookIndex(this, bookIndex);
                case 1:
                    //create book
                    
                    activeBook = bookIndex.createBook();

                    if (activeBook == null){
                        return new programStateAndBookIndex(MAIN, bookIndex);
                    } else {
                        return new programStateAndBookIndex(getBookState(activeBook), bookIndex);
                    }
   
                case 2:
                    // open book
                    bookIndex.openBook();
                    activeBook = bookIndex.getActiveBook();

                    return new programStateAndBookIndex(this.getBookState(activeBook), bookIndex);
                case 3:
                    // rename it

                    bookIndex.renameBook();

                    return new programStateAndBookIndex(this, bookIndex);
                case 4:
                    // delete a book

                    bookIndex.deleteBook();

                    return new programStateAndBookIndex(MAIN, bookIndex);
                case 5:
                    // print summary
                    bookIndex.printSummary();

                    return new programStateAndBookIndex(this, bookIndex);
                case 6:
                    // quit
                    System.out.println("Quitting...");
                    return new programStateAndBookIndex(QUIT, null);
            }

        }

        @Override
        ArrayList<String> getOptions() {
            ArrayList<String> mainMenu = new ArrayList<>();
            mainMenu.add("1: Create address book");
            mainMenu.add("2: Open existing address book");
            mainMenu.add("3: Rename existing address book");
            mainMenu.add("4: Delete existing address book");
            mainMenu.add("5: Print summary of address books");
            mainMenu.add("6: Quit");
            return mainMenu;
        }
    },
    BOOK_EMPTY {
        @Override
        programStateAndBookIndex updateState(int selection, addressBookIndex bookIndex) {

            activeBook = bookIndex.getActiveBook();

            switch (selection) {
                default:
                    System.out.println("Invalid selection");
                    return new programStateAndBookIndex(this, bookIndex);
                case 1:
                    // add new person

                    System.out.println(activeBook.addEntry());
                    fileIO.saveAddressBooks(bookIndex);
                    

                    return new programStateAndBookIndex(BOOK_SINGLE, bookIndex);
                case 2:
                    // go back to main
                    return new programStateAndBookIndex(MAIN, bookIndex);
            }

        }

        @Override
        ArrayList<String> getOptions() {
            ArrayList<String> createMenuZeroEntries = new ArrayList<>();
            createMenuZeroEntries.add("1: Add new person");
            createMenuZeroEntries.add("2: Return to main menu");
            return createMenuZeroEntries;
        }
    },
    BOOK_SINGLE {
        @Override
        programStateAndBookIndex updateState(int selection, addressBookIndex bookIndex) {

            activeBook = bookIndex.getActiveBook();

            switch (selection) {
                default:
                    System.out.println("Invalid selection");
                    return new programStateAndBookIndex(this, bookIndex);
                case 1:
                    // add a new person

                    bookIndex.addEntry();

                    return new programStateAndBookIndex(BOOK_MULTIPLE, bookIndex);
                case 2:
                    // edit an entry
                    
                    bookIndex.editEntry();

                    return new programStateAndBookIndex(this, bookIndex);
                case 3:
                    // delete an entry

                    bookIndex.deleteEntry();

                    return new programStateAndBookIndex(BOOK_EMPTY, bookIndex);
                case 4:
                    // print entries
                
                    activeBook.printSummary();

                    return new programStateAndBookIndex(this, bookIndex);
                case 5:
                    // back to main

                    return new programStateAndBookIndex(MAIN, bookIndex);
            }

        }

        @Override
        ArrayList<String> getOptions() {
            ArrayList<String> createMenuSingleEntry = new ArrayList<>();
            createMenuSingleEntry.add("1: Add new person");
            createMenuSingleEntry.add("2: Edit entry");
            createMenuSingleEntry.add("3: Delete entry");
            createMenuSingleEntry.add("4: Print entries");
            createMenuSingleEntry.add("5: Return to main menu");
            return createMenuSingleEntry;
        }
    },
    BOOK_MULTIPLE {
        @Override
        programStateAndBookIndex updateState(int selection, addressBookIndex bookIndex) {
            
            activeBook = bookIndex.getActiveBook();

            switch (selection) {
                default:
                    System.out.println("Invalid selection");
                    return new programStateAndBookIndex(this, bookIndex);
                case 1:
                    // add a new person

                    bookIndex.addEntry();

                    return new programStateAndBookIndex(BOOK_MULTIPLE, bookIndex);
                case 2:
                    // edit an entry

                    bookIndex.editEntry();

                    return new programStateAndBookIndex(BOOK_MULTIPLE, bookIndex);
                case 3:
                    // delete an entry

                    bookIndex.deleteEntry();

                    return new programStateAndBookIndex(this.getBookState(activeBook), bookIndex);
                case 4:
                    //sort by last name
                    activeBook.sortByName();
                    fileIO.saveAddressBooks(bookIndex);
                    

                    return new programStateAndBookIndex(BOOK_MULTIPLE, bookIndex);
                case 5:
                    //sort by zip
                    activeBook.sortByZip();
                    fileIO.saveAddressBooks(bookIndex);

                    return new programStateAndBookIndex(BOOK_MULTIPLE, bookIndex);
                case 6:
                    
                    activeBook.printSummary();
                    
                    return new programStateAndBookIndex(BOOK_MULTIPLE, bookIndex);
                case 7:
                    // go to main

                    return new programStateAndBookIndex(MAIN, bookIndex);
            }

        }

        @Override
        ArrayList<String> getOptions() {
            ArrayList<String> createMenuMultipleEntry = new ArrayList<>();
            createMenuMultipleEntry.add("1: Add new person");
            createMenuMultipleEntry.add("2: Edit entry");
            createMenuMultipleEntry.add("3: Delete entry");
            createMenuMultipleEntry.add("4: Sort entries by last name");
            createMenuMultipleEntry.add("5: Sort entries by ZIP code");
            createMenuMultipleEntry.add("6: Print entries");
            createMenuMultipleEntry.add("7: Return to main menu");
            return createMenuMultipleEntry;
        }

    },
    QUIT {
        @Override
        programStateAndBookIndex updateState(int selection, addressBookIndex bookIndex) {

            return new programStateAndBookIndex(QUIT, bookIndex);
        }

        @Override
        ArrayList<String> getOptions() {
            return null;
        }
    };

    private static addressBook activeBook;
    

    abstract programStateAndBookIndex updateState(int selection, addressBookIndex bookIndex);
    abstract ArrayList<String> getOptions();

    protected State getBookState(addressBook book){

        if (book == null){
            return MAIN;
        }

        switch (book.getSize()){
            default:
                return BOOK_MULTIPLE;
            case 0:
                return BOOK_EMPTY;
            case 1:
                return BOOK_SINGLE;
        }

    }

}
