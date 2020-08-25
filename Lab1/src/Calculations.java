import java.lang.Math;
public class Calculations {


    public static double[][] calculateMatrix(double[][] matrix){
        if (!checkMatrix(matrix)) {
            System.out.println("System has no solution ");
            System.exit(1);
        } else {
            int freeMem=matrix[0].length-1;
            for(int i=0;i<matrix.length;i++){
                int l=i;
                for(int j=i+1;j<matrix.length;j++){
                    if( Math.abs(matrix[l][i]) > Math.abs(matrix[j][i]) ) l=j;
                }
                if (l!=i){
                    double swap;
                    for(int j=i;j<matrix.length;j++){
                        swap=matrix[i][j];
                        matrix[i][j]=matrix[l][j];
                        matrix[l][j]=swap;
                    }
                    swap=matrix[i][freeMem];
                    matrix[i][freeMem]=matrix[l][freeMem];
                    matrix[l][freeMem]=swap;
                }
                for(int k=i+1;k<matrix.length;k++){
                    double c = matrix[k][i]/matrix[i][i];
                    matrix[k][i]=0;
                    for(int n=i+1;n<matrix.length;n++){
                        matrix[k][n]-=c*matrix[i][n];
                    }
                    matrix[k][freeMem]-=c*matrix[i][freeMem];
                }

            }
        }
        return matrix;
    }
    public static boolean checkMatrix(double[][] matrix){
        double sum1;
        double sum2;
        for(int i=0;i<matrix.length;i++){
            sum1=0;
            sum2=0;
            for(int j=0;j<matrix.length;j++){
                sum1+=matrix[i][j];
                sum2+=matrix[j][i];
            }
            if (sum1 == 0 || sum2 == 0) return false;
        }
        return true;
    }
    public static double[] calcResult(double[][] matrix){
        double[] result = new double[matrix.length];
        for(int i=matrix.length-1;i>=0;i--){
            double b=matrix[i][matrix[0].length-1];
            for(int j=matrix.length-1;j>=0;j--){
                b-=matrix[i][j]*result[j];
            }
            result[i]=b/matrix[i][i];
        }
        return result;
    }
    public static double[] calcInfelicity(double[][] matrix, double[] result){
        double[] infelicity = new double[matrix.length];
        double sum=0;
        for(int i=0; i<matrix.length; i++){
            sum=0;
            for(int j=0; j<matrix.length; j++){
                sum+=matrix[i][j]*result[j];
            }
            infelicity[i]=matrix[i][matrix[0].length-1]-sum;
        }
        return infelicity;
    }
    public static double calcDeterminant(double[][] matrix){
        double determinant=1;
        for(int i=0; i<matrix.length;i++){
            determinant=determinant*matrix[i][i];
        }
        return determinant;
    }
}
