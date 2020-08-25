import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

public class Frame {

    JFrame frame;

    public Frame(){
        this.frame = getFrame();
    }

    public JFrame getFrame() {
        Graph graph = new Graph();
        Alg alg = new Alg();

        JFrame frame = new JFrame("Initial value problem");

        JLabel textFunction = new JLabel("Choose a function");
        textFunction.setBounds(20, 30, 150, 20);
        JRadioButton sin = new JRadioButton();
        JRadioButton cos = new JRadioButton();
        JRadioButton xyy = new JRadioButton();
        sin.setText("-sin(x)-y");
        cos.setText("cos(x)-y");
        xyy.setText("x*y^2");
        sin.setBounds(20, 50, 150, 20);
        cos.setBounds(20, 70, 150, 20);
        xyy.setBounds(20,90,150,20);
        ButtonGroup group = new ButtonGroup();
        group.add(sin);
        group.add(cos);
        group.add(xyy);


        JLabel textEnter = new JLabel("Enter:");
        JTextField x = new JTextField("0");
        JTextField y = new JTextField("0");
        JLabel textX = new JLabel("Xo:");
        JLabel textY = new JLabel("Yo:");
        textEnter.setBounds(20,140,150,20);
        x.setBounds(60,160,90,20);
        y.setBounds(60,180,90,20);
        textX.setBounds(20,160,90,20);
        textY.setBounds(20,180,90,20);

        JTextField xn = new JTextField("10");
        JTextField acc = new JTextField("0.01");
        JLabel textBorder = new JLabel("Xn:");
        JLabel accuracy = new JLabel("Acc:");
        xn.setBounds(60,210,90,20);
        acc.setBounds(60,230,90,20);
        textBorder.setBounds(20,210,90,20);
        accuracy.setBounds(20,230,90,20);

        JPanel jPanel = new JPanel(new BorderLayout());
        jPanel.setBounds(200, 30, 750, 490);

        JButton build = new JButton("Build graph");
        build.setBounds(20, 280, 150, 20);
        build.addActionListener(e -> {
            try {
                if (jPanel.isEnabled()) {
                    jPanel.removeAll();
                    alg.setxPoint(new ArrayList());
                    alg.setyPoint(new ArrayList());
                    alg.setCount(0);
                }
                JFreeChart chart;

                double sendX = Double.parseDouble(x.getText().trim());
                double sendY = Double.parseDouble(y.getText().trim());
                double sendXN = Double.parseDouble(xn.getText().trim());
                double sendAcc = Double.parseDouble(acc.getText().trim());

                if (sendX > sendXN) throw new IOException();

                switch (getSelectedButtonText(group)){
                    case "-sin(x)-y":
                        chart = graph.getChart(Functions.SIN, sendX, sendY, sendXN, sendAcc);
                        break;
                    case "cos(x)-y":
                        chart = graph.getChart(Functions.COS, sendX, sendY, sendXN, sendAcc);
                        break;
                    case "x*y^2":
                        chart = graph.getChart(Functions.XYY, sendX, sendY, sendXN, sendAcc);
                        break;
                    default:
                        throw new Exception();
                }

                ChartPanel chartPanel = new ChartPanel(chart);
                chartPanel.setPreferredSize(new java.awt.Dimension(750, 490));
                chartPanel.setDomainZoomable(true);

                jPanel.add(chartPanel, BorderLayout.WEST);
                frame.getContentPane().add(jPanel);
                frame.pack();
                frame.setSize(1000, 600);
                frame.repaint();

            } catch (IOException ex){
                JOptionPane.showMessageDialog(frame, "Correct the entering data");
            } catch (Exception ex){
                JOptionPane.showMessageDialog(frame, "Enter all of the above");
            }
        });

        JButton clear = new JButton("Clear");
        clear.setBounds(20, 500, 150, 20);
        clear.addActionListener(e -> {
            jPanel.removeAll();
            frame.pack();
            frame.setSize(1000,600);
            frame.repaint();
        });

        //render
        frame.getContentPane().add(textFunction);
        frame.getContentPane().add(sin);
        frame.getContentPane().add(cos);
        frame.getContentPane().add(xyy);

        frame.getContentPane().add(textEnter);
        frame.getContentPane().add(x);
        frame.getContentPane().add(y);
        frame.getContentPane().add(textX);
        frame.getContentPane().add(textY);

        frame.getContentPane().add(xn);
        frame.getContentPane().add(acc);
        frame.getContentPane().add(textBorder);
        frame.getContentPane().add(accuracy);

        frame.getContentPane().add(build);
        frame.getContentPane().add(clear);

        frame.setLayout(null);
        frame.pack();
        frame.setSize(1000,600);
        return frame;
    }

    public void setVisible(boolean visible){
        this.frame.setVisible(visible);
    }

    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }
}
