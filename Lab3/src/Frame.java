import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javax.swing.*;
import java.awt.*;

public class Frame {

    private double[][] arr = new double[100][2];
    private int count=0;

    public void start() {
        Graph graph = new Graph();

        JFrame frame = new JFrame("Approximation");

        JLabel text1 = new JLabel("Choose a function");
        text1.setBounds(20, 30, 150, 20);
        JRadioButton linear = new JRadioButton();
        JRadioButton binomial = new JRadioButton();
        JRadioButton exp = new JRadioButton();
        linear.setText("ax+b");
        binomial.setText("ax^2+bx+c");
        exp.setText("a*log(x)+b");
        linear.setBounds(20, 50, 150, 20);
        binomial.setBounds(20, 70, 150, 20);
        exp.setBounds(20,90,150,20);
        ButtonGroup group = new ButtonGroup();
        group.add(linear);
        group.add(binomial);
        group.add(exp);
        JLabel points = new JLabel();

        JLabel text4 = new JLabel("Add Point");
        JButton addPoint = new JButton("Add");
        JTextField x = new JTextField();
        JTextField y = new JTextField();
        JLabel textX = new JLabel("X:");
        JLabel textY = new JLabel("Y:");
        text4.setBounds(20,130,150,20);
        addPoint.setBounds(20,190,150,20);
        x.setBounds(40,160,40,20);
        textX.setBounds(20,160,20,20);
        y.setBounds(110,160,40,20);
        textY.setBounds(90,160,20,20);
        addPoint.addActionListener(e -> {
            try {
                arr[count][0] = Double.parseDouble(x.getText().trim());
                arr[count][1] = Double.parseDouble(y.getText().trim());
                count++;
                points.setText("<html>Points: ");
                for(int i=0;i<count;i++)
                    points.setText(points.getText() + "<br>x:" + String.format("%.3f", arr[i][0]) + " " + "y:" + String.format("%.3f", arr[i][1]));
                points.setText(points.getText() + "</html>");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Enter coordinates first");
            }
        });


        JLabel text3 = new JLabel("Points to random");
        text3.setBounds(800, 30, 150, 20);
        JTextField numPoints = new JTextField();
        numPoints.setBounds(800, 60, 150, 20);
        numPoints.setInputVerifier(new InputVerifier() {
            public boolean verify(JComponent input) {
                try {
                    int num = Integer.parseInt(numPoints.getText().trim());
                    if (num > 100) {
                        JOptionPane.showMessageDialog(frame, "I think that's more than enough");
                        return false;
                    }
                    return true;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Enter a number");
                    return false;
                }
            }
        });

        points.setBounds(800, 120, 150, 350);
        points.setVerticalAlignment(SwingConstants.TOP);
        JButton randomize = new JButton("Randomize");
        randomize.setBounds(800, 90, 150, 20);
        randomize.addActionListener(e -> {
            try {
                int rand = Integer.parseInt(numPoints.getText().trim());
                for (int i=0;i<rand;i++) {
                    arr[count][0] = Math.random() * 10;
                    arr[count][1] = Math.random() * 10;
                    count++;
                }
                points.setText("<html>Points: ");
                for (int i=0;i<count;i++)
                    points.setText(points.getText() + "<br>x:" + String.format("%.3f", arr[i][0]) + " " + "y:" + String.format("%.3f", arr[i][1]));
                points.setText(points.getText() + "</html>");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Enter a number first");
            }
        });

        JPanel jPanel = new JPanel(new BorderLayout());
        JLabel jLabel = new JLabel();
        jPanel.setBounds(200, 30, 550, 450);
        jLabel.setBounds(200,500,550,40);

        JButton build = new JButton("Build graph");
        build.setBounds(20, 500, 150, 20);
        build.addActionListener(e -> {
            try {
                if (count<3) {
                    JOptionPane.showMessageDialog(frame, "Need more points");
                    throw new Exception();
                }
                if (jPanel.isEnabled()) jPanel.removeAll();
                JFreeChart chart;
                if (linear.isSelected()) {
                    chart = graph.getChart(0, count, arr);
                    double[] odds = graph.getOdds();
                    double[] newOdds = graph.getNewOdds();
                    jLabel.setText("<html>Old odds: a=" + odds[0] + "   b=" + odds[1] + "<br>" + "New odds: a=" + newOdds[0] + "  b=" + newOdds[1] + "</html>");
                } else if (binomial.isSelected()) {
                    chart = graph.getChart(1, count, arr);
                    double[] odds = graph.getOdds();
                    double[] newOdds = graph.getNewOdds();
                    jLabel.setText("<html> Old odds: a=" + odds[0] + "  b=" + odds[1] + "   c=" + odds[2] +
                            "<br> New odds: a=" + newOdds[0] + "    b=" + newOdds[1] + "    c=" + newOdds[2] + "</html>");
                } else if (exp.isSelected()) {
                    chart = graph.getChart(2, count, arr);
                    double[] odds = graph.getOdds();
                    double[] newOdds = graph.getNewOdds();
                    jLabel.setText("<html>Old odds: a=" + odds[0] + "   b=" + odds[1] + "<br>" + "New odds: a=" + newOdds[0] + "  b=" + newOdds[1] + "</html>");
                } else {
                    JOptionPane.showMessageDialog(frame, "Enter all of the above");
                    throw new Exception();
                }

                ChartPanel chartPanel = new ChartPanel(chart);
                chartPanel.setPreferredSize(new java.awt.Dimension(550, 450));
                chartPanel.setDomainZoomable(true);


                jPanel.add(chartPanel, BorderLayout.WEST);
                frame.getContentPane().add(jPanel);
                frame.getContentPane().add(jLabel);
                frame.pack();
                frame.setSize(1000, 600);
                frame.repaint();
            } catch (Exception ex) {

            }
        });

        JButton clear = new JButton("Clear points");
        clear.setBounds(800, 500, 150, 20);
        clear.addActionListener(e -> {
            arr = new double[200][2];
            count = 0;
            points.setText("");
            jPanel.removeAll();
            jLabel.setText("");
            frame.pack();
            frame.setSize(1000,600);
            frame.repaint();
        });

        //Render
        frame.getContentPane().add(text1);
        frame.getContentPane().add(linear);
        frame.getContentPane().add(binomial);
        frame.getContentPane().add(exp);

        frame.getContentPane().add(text4);
        frame.getContentPane().add(textX);
        frame.getContentPane().add(textY);
        frame.getContentPane().add(addPoint);
        frame.getContentPane().add(x);
        frame.getContentPane().add(y);

        frame.getContentPane().add(text3);
        frame.getContentPane().add(numPoints);
        frame.getContentPane().add(randomize);
        frame.getContentPane().add(points);


        frame.getContentPane().add(clear);
        frame.getContentPane().add(build);

        frame.setLayout(null);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1000, 600);
//        frame.show();

    }
}
