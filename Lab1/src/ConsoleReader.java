import java.util.Scanner;

public class ConsoleReader {
    private double matrix[][];
    public ConsoleReader(){
    }

    public void readConsole() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter matrix size");
            String s = scanner.nextLine().trim();
            int size = Integer.parseInt(s);
            double[][] matrix = new double[size][size + 1];
            System.out.println("Enter matrix\nDivide numbers with space\n");
            for (int i = 0; i < size; i++) {
                System.out.println("Enter matrix row");
                s = scanner.nextLine().trim();
                String[] row = s.split(" ");
                for (int j = 0; j < size + 1; j++) {
                    matrix[i][j] = Double.parseDouble(row[j]);
                }
                setMatrix(matrix);
            }
        } catch (NumberFormatException e){
            System.out.println("Not a number");
            System.exit(1);
        } catch (NullPointerException e){
            System.out.println("Input error");
            System.exit(1);
        }
    }

    public double[][] getMatrix() {
        return this.matrix;
    }
    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }
}
