/***************************************
 * Chris Carmine
 * CS504 SU 21-22
 * Assignment 3
 * Version 0.01
 ***************************************/

package com.cs504.hw3;

public class programStateAndBookIndex {

    private State programState;
    private addressBookIndex bookIndex;

    public programStateAndBookIndex(State programState, addressBookIndex bookIndex) {
        this.programState = programState;
        this.bookIndex = bookIndex;
    }

    public State getProgramState() {
        return programState;
    }

    public void setProgramState(State programState) {
        this.programState = programState;
    }

    public addressBookIndex getBookIndex() {
        return bookIndex;
    }

    
}
