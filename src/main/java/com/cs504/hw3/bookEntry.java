/***************************************
 * Chris Carmine
 * CS504 SU 21-22
 * Assignment 3
 * Version 0.01
***************************************/

package com.cs504.hw3;

public class bookEntry {

    private final String firstName;
    private final String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;
    private boolean isValid;
    
    public bookEntry(String input) {

        String[] tokens = input.split(",");

        if (tokens.length != 7){

            isValid = false;

            this.firstName = "Invalid input";
            this.lastName = "Invalid input";
            this.address = "Invalid input";
            this.city = "Invalid input";
            this.state = "Invalid input";
            this.zip = "Invalid input";
            this.phoneNumber = "Invalid input";

            return;
        }

        //clean up leading or trailing spaces
        for (int i = 0; i < tokens.length; ++i){
            tokens[i] = tokens[i].trim();
        }

        isValid = true;
        this.firstName = tokens[0];
        this.lastName = tokens[1];
        this.address = tokens[2];
        this.city = tokens[3];
        this.state = tokens[4];
        this.zip = tokens[5];
        this.phoneNumber = tokens[6];
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + "\n" +
               address + "\n" +
               city + ", " + state + " " + zip + "\nPhone: " +
               phoneNumber;
    }

    public String entryID(){
        return firstName + " " + lastName;
    }



    public boolean isValid() {
        return isValid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    

    
}
