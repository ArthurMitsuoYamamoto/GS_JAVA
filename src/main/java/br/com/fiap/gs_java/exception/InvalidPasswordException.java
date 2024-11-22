package br.com.fiap.gs_java.exception;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(){
        super("Invalid password");
    }
}
