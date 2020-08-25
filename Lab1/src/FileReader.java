import java.io.*;

public class FileReader {
    private double matrix[][];
    public FileReader(){
    }

    public void readFile(String path)  {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            String row;
            String[] rows = new String[20];
            int i = 0;
            while ((row = reader.readLine()) != null) {
                rows[i] = row;
                i++;
            }
            if (i == 0) System.out.println("File is empty");
            toArray(rows);
        } catch (IOException e){
            System.out.println("Input error");
            System.exit(1);
        } catch (NumberFormatException e){
            System.out.println("Not a number");
            System.exit(1);
        } catch (NullPointerException e){
            System.out.println("Input error");
            System.exit(1);
        }
     }
    public void toArray(String rows[]){
        int size = Integer.parseInt(rows[0].trim());

        double matrix[][] = new double[size][size+1];
        for(int i=0;i<size;i++){
            String[] row = rows[i+1].trim().split(" ");
            for(int j=0;j<size+1;j++){
                matrix[i][j]=Double.parseDouble(row[j].trim());
            }
            setMatrix(matrix);
        }
    }

    public double[][] getMatrix() {
        return this.matrix;
    }
    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }
}
