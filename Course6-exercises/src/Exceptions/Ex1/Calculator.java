package Exceptions.Ex1;

public interface Calculator {
    double add (Object a, Object b) throws MyExceptions;
    double divide (Object numerator, Object denominator) throws MyExceptions;
    double average (Object array) throws MyExceptions;
}
