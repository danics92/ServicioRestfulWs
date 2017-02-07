package com.esliceu.dwes.model;

/**
 * @author Xavi Torrens
 */
public class Echo {

    private String text;

    public Echo(){

    }

    public Echo(String hola) {
        text = hola;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
