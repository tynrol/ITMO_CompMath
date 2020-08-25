
public class CalculatedMatrix {

    private double[][] matrix;
    private double[] result;
    private double[] infelicity;
    private double determinant=0;

    public CalculatedMatrix(double[][] matrix){
        Calculations calculations = new Calculations();
        this.matrix = calculations.calculateMatrix(matrix);
        this.result = calculations.calcResult(matrix);
        this.infelicity = calculations.calcInfelicity(matrix, result);
        this.determinant = calculations.calcDeterminant(matrix);
    }


    public double getDeterminant(){
        return this.determinant;
    }
    public void setDeterminant(double a){
        this.determinant=a;
    }
    public double[][] getMatrix(){
        return this.matrix;
    }
    public void setMatrix(double[][] a){
        this.matrix=a;
    }
    public double[] getResult(){
        return this.result;
    }
    public void setResult(double[] a){
        this.result=a;
    }
    public double[] getInfelicity(){
        return this.infelicity;
    }
    public void setInfelicity(double[] a){
        this.infelicity=a;
    }

}
