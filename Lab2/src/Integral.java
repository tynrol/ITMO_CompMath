import java.util.function.Function;

public class Integral {
    private Function function;
    private double lowLimit;
    private double highLimit;
    private double precision;
    private double result;
    private int amountDivision;
    private double infelicity;

    public Integral(Function function, double lowLimit, double highLimit, double precision){
        this.function = function;
        this.lowLimit = lowLimit;
        this.highLimit = highLimit;
        this.precision = precision;
    }

    public Function getFunction() {
        return function;
    }
    public void setFunction(Function function) {
        this.function = function;
    }
    public double getLowLimit(){
        return lowLimit;
    }
    public void setLowLimit(double lowLimit){
        this.lowLimit = lowLimit;
    }
    public double getHighLimit(){
        return highLimit;
    }
    public void setHighLimit(double highLimit){
        this.highLimit = highLimit;
    }
    public double getPrecision() {
        return precision;
    }
    public void setPrecision(double precision) {
        this.precision = precision;
    }
    public double getResult() {
        return result;
    }
    public void setResult(double result) {
        this.result = result;
    }
    public int getAmountDivision() {
        return amountDivision;
    }
    public void setAmountDivision(int amountDivision) {
        this.amountDivision = amountDivision;
    }
    public double getInfelicity() {
        return infelicity;
    }
    public void setInfelicity(double infelicity) {
        this.infelicity = infelicity;
    }
}
