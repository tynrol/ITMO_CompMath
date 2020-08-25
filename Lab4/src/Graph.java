import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.util.ArrayList;

public class Graph {

    public Graph(){}

    public JFreeChart getChart(Functions func, double x, double y, double xn, double acc) {
        Alg alg = new Alg();
        XYSeries graph = new XYSeries("Function");

        alg.calculateEiler(func,x,y,xn,acc);
        ArrayList xPoint = alg.getxPoint();
        ArrayList yPoint = alg.getyPoint();

        for(int i=0;i<alg.getCount();i++) {
            double addX = Double.parseDouble(xPoint.get(i).toString());
            double addY = Double.parseDouble(yPoint.get(i).toString());
            if (addY>=128) addY = 128;
            if (addY<=-128) addY = -128;
            graph.add(addX,addY);
        }


        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(graph);
        JFreeChart chart = ChartFactory
                .createXYLineChart("","x","y",
                        dataset, PlotOrientation.VERTICAL,
                        false,false,false);

        final XYPlot plot = chart.getXYPlot();

        plot.setDomainGridlinePaint(Color.gray);
        plot.setRangeGridlinePaint (Color.gray);
        plot.setDomainZeroBaselineVisible(true);
        plot.setRangeZeroBaselineVisible(true);

        plot.setAxisOffset(new RectangleInsets(1.0, 1.0, 1.0, 1.0));

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesLinesVisible (0, true);
        renderer.setSeriesShapesVisible(0, false);
        plot.setRenderer(renderer);

        return chart;
    }
}
