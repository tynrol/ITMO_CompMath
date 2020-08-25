import java.util.ArrayList;

public class Alg {

    private int count = 0;
    private ArrayList xPoint = new ArrayList();
    private ArrayList yPoint = new ArrayList();

    public Alg(){ }

    public void calculateEiler(Functions func, double x, double y, double xn, double acc){
        double h = getH(acc);
        xPoint.add(x);
        yPoint.add(y);
        count++;
        while(x<xn){
            y += h * getFuncRes(func,x,y);
            x += h;
            xPoint.add(x);
            yPoint.add(y);
            count++;
        }
    }


    public double getH(double acc){
        double h = 1;
        double hNew = 0.5;
        while (Math.abs(h-hNew)>=acc){
            h = hNew;
            hNew = h/2;
        }
        return hNew;
    }

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public ArrayList getxPoint() {
        return xPoint;
    }
    public void setxPoint(ArrayList xPoint) {
        this.xPoint = xPoint;
    }
    public ArrayList getyPoint() {
        return yPoint;
    }
    public void setyPoint(ArrayList yPoint) {
        this.yPoint = yPoint;
    }

    public double getFuncRes(Functions func, double x, double y){
        switch (func){
            case SIN:
                return -Math.sin(x)-y;
            case COS:
                return Math.cos(x)-y;
            case XYY:
                return x*y*y;
            default:
                return -Math.sin(x)-y;
        }
    }

}
