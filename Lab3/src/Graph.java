import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;

public class Graph {

    public Graph(){}

    private double[] odds = new double[3];
    private double[] newOdds = new double[3];

    public JFreeChart getChart(int func, int n, double[][] arr) {

        Alg alg = new Alg();

        XYSeries points = new XYSeries("Points");
        for(int i=0;i<n;i++){
            points.add(arr[i][0],arr[i][1]);
        }

        XYSeries firstFunc = new XYSeries("FirstFunc");
        XYSeries secondFunc = new XYSeries("SecondFunc");

        if (func==0) {
            double[] AB = alg.calcAB(arr,n);
            odds = AB;
            for(int i=0;i<=10;i++)
                firstFunc.add(i,AB[0]*i+AB[1]);

            //deleting an element from array
            int j = alg.getMaxError(0,AB[0],AB[1],arr,n);
            double[] newAB = alg.calcAB(delete(j,arr,n),n-1);
            newOdds = newAB;
            for(int i=0;i<=10;i++)
                secondFunc.add(i,newAB[0]*i+newAB[1]);
        } else if(func==1) {
            double[] ABC = alg.calcABC(arr,n);
            odds = ABC;
            for(int i=0;i<=10;i++)
                firstFunc.add(i,ABC[0]*i*i+ABC[1]*i+ABC[2]);

            //deleting an element from array
            int j = alg.getMaxError(ABC[0],ABC[1],ABC[2],arr,n);
            double[] newABC = alg.calcABC(delete(j,arr,n),n-1);
            newOdds = newABC;
            for(int i=0;i<=10;i++)
                secondFunc.add(i,newABC[0]*i*i+newABC[1]*i+newABC[2]);
        } else {
            double[] AB = alg.calcLogAB(arr,n);
            odds = AB;
            for(double i=0.1;i<=10;i+=0.1)
                firstFunc.add(i,AB[0]*Math.log(i)+AB[1]);

            //deleting an element from array
            int j = alg.getMaxErrorLog(AB[0],AB[1],arr,n);
            double[] newAB = alg.calcLogAB(delete(j,arr,n),n-1);
            newOdds = newAB;
            for(double i=0.1;i<=10;i+=0.1)
                secondFunc.add(i,newAB[0]*Math.log(i)+newAB[1]);

        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(points);
        dataset.addSeries(firstFunc);
        dataset.addSeries(secondFunc);
        JFreeChart chart = ChartFactory
                .createXYLineChart("","x","y",
                        dataset, PlotOrientation.VERTICAL,
                        true,false,false);

        final XYPlot plot = chart.getXYPlot();

        plot.setDomainGridlinePaint(Color.gray);
        plot.setRangeGridlinePaint (Color.gray);
        plot.setDomainZeroBaselineVisible(true);
        plot.setRangeZeroBaselineVisible(true);

        plot.setAxisOffset(new RectangleInsets(1.0, 1.0, 1.0, 1.0));

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesLinesVisible (0, false);
        renderer.setSeriesLinesVisible (1, true);
        renderer.setSeriesLinesVisible (2, true);

        renderer.setSeriesShapesVisible(0, true);
        renderer.setSeriesShapesVisible(1, false);
        renderer.setSeriesShapesVisible(2, false);

        plot.setRenderer(renderer);

        return chart;
    }

    public double[] getNewOdds() {
        return newOdds;
    }
    public double[] getOdds() {
        return odds;
    }
    public double[][] delete(int j, double arr[][], int n){
        double[][] newArr = new double[n][2];
        for(int i=0, k=0;i<n;i++, k++){
            if(i!=j) {
                newArr[i][0] = arr[k][0];
                newArr[i][1] = arr[k][1];
            } else {
                i--;
                j=-1;
            }
        }
        return newArr;
    }
}
