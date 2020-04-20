package Exceptions.Ex1;

public class CalculatorDouble implements Calculator{
    @Override
    public double add(Object a, Object b) throws MyExceptions {
        if (a == null || b == null) {
            throw new NullParameterException();
        }
        double sum = (double) a + (double) b;
        if (sum == Double.POSITIVE_INFINITY) {
            throw new OverflowException();
        }
        if (sum == Double.NEGATIVE_INFINITY) {
            throw new UnderflowException();
        }
        return sum;
    }

    @Override
    public double divide(Object numerator, Object denominator) throws MyExceptions {
        if (numerator == null || denominator == null) {
            throw new NullParameterException();
        }
        if ((double) denominator == 0) {
            throw new DivisionByZeroException();
        }
        return (double) numerator / (double) denominator;
    }

    @Override
    public double average(Object array) throws MyExceptions {
        if (array == null) {
            throw new NullParameterException();
        }
        double result = 0;
        try {
            double s = 0;
            for (double d : (double[]) array) {
                s = add(s, d);
            }
            result = s / ((double[]) array).length;
        }catch (DivisionByZeroException e) {
            result = 0; // no elements <=> avg 0
        }catch (MyExceptions e) {
            throw e;
        }finally {
            return result;
        }
    }
}
