package org.SafinAndAinur.zipApp;

public class Leaf extends Node{

    private final int symbol;

    public Leaf(int symbol) {
        if (symbol < 0){
            throw new IllegalArgumentException("символ меньше 0");
        }
        this.symbol = symbol;
    }

    public int getSymbol() {
        return symbol;
    }
}
