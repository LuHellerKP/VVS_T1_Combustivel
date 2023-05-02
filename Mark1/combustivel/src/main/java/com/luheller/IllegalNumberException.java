package com.luheller;

public class IllegalNumberException extends RuntimeException {
    public IllegalNumberException(){
        super("Numero inválido");
    }
}