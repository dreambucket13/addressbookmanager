/***************************************
 * Chris Carmine
 * CS504 SU 21-22
 * Assignment 3
 * Version 0.01
***************************************/

package com.cs504.hw3;

import java.util.ArrayList;
import java.util.Comparator;

public class addressBook {

    private ArrayList<bookEntry> entries;
    private int size;
    private String bookName;

    public addressBook(String bookName) {

        this.bookName = bookName;
        entries = new ArrayList<>();
        size = entries.size();

    }

    public addressBook() {
        this.bookName = "";
        entries = new ArrayList<>();
        size = entries.size();
    }

    public void printAddressBook(){

        for (bookEntry b : entries ){
            System.out.println(b.toString());
        }

    }

    public String getName(){
        return this.bookName;
    }

    public void setName(String newName){
        this.bookName = newName;
    }

    public ArrayList<bookEntry> getEntries(){
        return entries;
    }

    public bookEntry selectEntry(){

        System.out.println("Select entry or hit 'b' to go back:");
        for (bookEntry entry : entries){
            System.out.println(entries.indexOf(entry)+1 + ": " + entry.entryID() );
        }

        int selection = Integer.parseInt(userIO.getLine());

        //protect against invalid input
        if (selection > entries.size()){
            return null;
        }

        return entries.get(selection-1);
    }

    public String addEntry(){

        System.out.println("Format for new entry: First Name, Last Name, Address, City, State, ZIP, Phone Number");
        String input = userIO.getLine();
        bookEntry newEntry = new bookEntry(input);

        if (!newEntry.isValid()){
            return "Invalid input, no entry added";
        }

        if (!entries.contains(newEntry)){
            entries.add(newEntry);
            size = entries.size();
            return "Added entry successfully\n";
        } else {
            return "Duplicate entry, no modifications made.\n";
        }


    }

    public boolean editEntry(bookEntry entryToEdit){
        System.out.println("Format for editing an entry: Address, City, State, ZIP, Phone Number");

        String input = userIO.getLine();

        String[] tokens = input.split(",");

        if (tokens.length != 5){
            System.out.println("Invalid edit, no changes made");
            return false;
        }

        if (!tokens[0].isEmpty()){
            entryToEdit.setAddress(tokens[0]);
        }

        if (!tokens[1].isEmpty()){
            entryToEdit.setCity(tokens[1]);
        }

        if (!tokens[2].isEmpty()){
            entryToEdit.setState(tokens[2]);
        }

        if (!tokens[3].isEmpty()){
            entryToEdit.setZip(tokens[3]);
        }

        if (!tokens[4].isEmpty()){
            entryToEdit.setPhoneNumber(tokens[4]);
        }

        return true;
    
    }

    public String deleteEntry(bookEntry entry){

        if (entries.contains(entry)){
            entries.remove(entry);
            size = entries.size();
            return "Deleted entry successfully\n";
        } else {
            return "Entry not found, no modifications made.\n";
        }

    }

    public void printSummary(){

        System.out.printf("Summary for book %s:\n",this.getName());

        for (bookEntry entry : entries){
            System.out.println(entry.toString());
            System.out.println("------------------------");
        }
    }
    
    public int getSize(){
        return size;
    }

    public void sortByName(){
        entries.sort(Comparator.comparing(bookEntry::getLastName));
        //breaking the tie
        String priorLastName = "invalid";
        String currentLastName;
        int subListStart;
        int subListEnd;

        for (bookEntry b : entries) {
            currentLastName = b.getLastName();

            if (currentLastName.equals(priorLastName)){
                subListStart = entries.indexOf(b) - 1;
                subListEnd = entries.indexOf(b);

                while (currentLastName.equals(priorLastName)){

                    if (subListEnd + 1 >= entries.size()){
                        break;
                    }

                    b = entries.get(subListEnd + 1);
                    priorLastName = currentLastName;
                    subListEnd++;
                }

                //sort the sublist
                entries.subList(subListStart, subListEnd+1).sort(Comparator.comparing(bookEntry::getFirstName));
            }   

            priorLastName = currentLastName;
        }


    }

    public void sortByZip(){
        entries.sort(Comparator.comparing(bookEntry::getZip));

        //breaking the tie
        String priorZip = "invalid";
        String currentZip;
        int subListStart;
        int subListEnd;

        for (bookEntry b : entries) {
            currentZip = b.getZip();

            if (currentZip.equals(priorZip)){
                subListStart = entries.indexOf(b) - 1;
                subListEnd = entries.indexOf(b);

                while (currentZip.equals(priorZip)){

                    if (subListEnd + 1 >= entries.size()){
                        break;
                    }

                    b = entries.get(subListEnd + 1);
                    priorZip = currentZip;
                    subListEnd++;
                }

                //sort the sublist
                entries.subList(subListStart, subListEnd+1).sort(Comparator.comparing(bookEntry::getLastName));
            }   

            priorZip = currentZip;
        }



    }




    
    
}
