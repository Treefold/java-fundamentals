package Exceptions.Ex1;

public class DivisionByZeroException extends ArithmeticException{
    public DivisionByZeroException() {
        super("/ by zero");
    }
}
