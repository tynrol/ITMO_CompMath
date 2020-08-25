import java.lang.Math;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        double matrix[][] = null;
        FileReader fileReader = new FileReader();
        ConsoleReader consoleReader = new ConsoleReader();
        System.out.println("To upload matrix try:\n\"file\" - upload from file \n\"console\" - enter from console  \n\"gen\" - generate random matrix");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine().trim();
        switch (s){
            case ("file"):
                System.out.println("Enter file path ");
                s = scanner.nextLine().trim();
                fileReader.readFile(s);
                matrix = fileReader.getMatrix();
                System.out.println("Original matrix:");
                for(int i=0;i<matrix.length;i++){
                    for(int j=0;j<matrix.length+1;j++){
                        System.out.print(fileReader.getMatrix()[i][j]+" ");
                    }
                    System.out.println();
                } break;
            case ("console"):
                consoleReader.readConsole();
                matrix = consoleReader.getMatrix();
                break;
            case ("gen"):
                matrix = generateMatrix();
                System.out.println("Original matrix:");
                for(int i=0;i<matrix.length;i++){
                    for(int j=0;j<matrix.length+1;j++){
                        System.out.print(matrix[i][j]+" ");
                    }
                    System.out.println();
                } break;
            default:
                System.out.println("Wrong, try again");
                break;
            }
        output(matrix);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
            } catch (Exception e) {
                System.err.println("Sry");
            }}));
    }

    public static void output(double[][] matrix){
        CalculatedMatrix CM = new CalculatedMatrix(matrix);

        System.out.println("Determinant : " + CM.getDeterminant());

        System.out.println("\nResult:");
        for(int i=0;i<CM.getResult().length;i++){
            System.out.print("x" + (i+1) + ": " + CM.getResult()[i] + " ");
        }

        System.out.println("\n\nInfelicity:");
        for(int i=0;i<CM.getInfelicity().length;i++){
            System.out.print("row" + (i+1) + ": " + CM.getInfelicity()[i] + " ");
        }
        System.out.println("\n\nTriangle matrix");
        matrix = CM.getMatrix();
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix.length+1;j++){
                System.out.print(CM.getMatrix()[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static double[][] generateMatrix(){
        double[][] matrix = null;
        try {
            System.out.println("Enter matrix size ");
            Scanner scanner = new Scanner(System.in);
            int size = scanner.nextInt();
            matrix = new double[size][size+1];
            for(int i=0;i<size;i++){
                for(int j=0;j<size+1;j++){
                    matrix[i][j] = Math.random()*4-2;
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Not a number");
            System.exit(1);
        }
        return matrix;
    }
}
