
public class Alg {

    public Alg(){    }

    public double[] calcLogAB(double[][] arr, int n){
        double sumLnX = 0;
        double sumLnXX = 0;
        double sumY = 0;
        double sumYLnX = 0;
        for(int i=0; i<=n-1; i++){
            sumLnX += Math.log(arr[i][0]);
            sumLnXX += Math.log(arr[i][0]*arr[i][0]);
            sumY += arr[i][1];
            sumYLnX += arr[i][1] * Math.log(arr[i][0]);
        }
        double a = (sumYLnX * n - sumLnX * sumY) / (sumLnXX * n - sumLnX * sumLnX);
        double b = (sumY * 1/n - a/n * sumLnX);
        double[] ab = {a,b,0};
        return ab;
    }

    public double[] calcAB(double[][] arr, int n){
        double sumX = 0;
        double sumXX = 0;
        double sumY = 0;
        double sumXY = 0;
        for (int i=0; i<=n-1; i++){
            sumX += arr[i][0];
            sumXX += arr[i][0] * arr[i][0];
            sumY += arr[i][1];
            sumXY += arr[i][0] * arr[i][1];
        }
        double a = (sumXY * n - sumX * sumY) / (sumXX * n - sumX * sumX);
        double b = (sumXX * sumY - sumX * sumXY) / (sumXX * n - sumX * sumX);
        double[] ab = {a,b,0};
        return ab;
    }
    public double[] calcABC(double[][] arr, int n){
        double sumX = 0;
        double sumXX = 0;
        double sumXXX = 0;
        double sumXXXX = 0;
        double sumY = 0;
        double sumXY = 0;
        double sumXXY = 0;
        for (int i = 0; i <= n-1; i++) {
            sumX += arr[i][0];
            sumXX += arr[i][0] * arr[i][0];
            sumXXX += arr[i][0] * arr[i][0] *arr[i][0];
            sumXXXX += arr[i][0] * arr[i][0] *arr[i][0] *arr[i][0];
            sumY += arr[i][1];
            sumXY += arr[i][0] * arr[i][1];
            sumXXY += arr[i][0] *arr[i][0] * arr[i][1];
        }
        double[][] matrix = {{(double)n,sumX,sumXX,sumY},{sumX,sumXX,sumXXX,sumXY},{sumXX,sumXXX,sumXXXX,sumXXY}};
        matrix = calculateMatrix(matrix);
        double[] abc = calcResult(matrix);

        double swap = abc[2];
        abc[2]=abc[0];
        abc[0]=swap;

        return abc;
    }
    public double[][] calculateMatrix(double[][] matrix){
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
        return matrix;
    }
    public double[] calcResult(double[][] matrix) {
        double[] result = new double[matrix.length];
        for (int i = matrix.length - 1; i >= 0; i--) {
            double b = matrix[i][matrix[0].length - 1];
            for (int j = matrix.length - 1; j >= 0; j--) {
                b -= matrix[i][j] * result[j];
            }
            result[i] = b / matrix[i][i];
        }
        return result;
    }
    public int getMaxError(double a, double b, double c, double[][] arr, int n){
        double maxError = 0;
        int maxErrorIteration = 0;
        for(int i=0;i<=n;i++){
            double f = a*arr[i][0]*arr[i][0] + b*arr[i][0] + c;
            if (Math.abs(f-arr[i][1])>maxError){
                maxError=Math.abs(f-arr[i][1]);
                maxErrorIteration=i;
            }
        }
        return maxErrorIteration;
    }
    public int getMaxErrorLog(double a, double b, double[][] arr, int n){
        double maxError = 0;
        int maxErrorIteration = 0;
        for(int i=1;i<=n;i++){
            double f = Math.log(a*Math.log(i)+b);
            if (Math.abs(f-arr[i][1])>maxError){
                maxError=Math.abs(f-arr[i][1]);
                maxErrorIteration=i;
            }
        }
        return maxErrorIteration;
    }
}
