public class SimsonMethod {

    public static Integral calculate(Integral integral){
        int n = 1;
        double s1,s;

        s1=simpson(integral, n);
        do {
           s=s1;
           n = 2*n;
           s1=simpson(integral, n);
        } while (Math.abs(s1-s)>integral.getPrecision());

        integral.setAmountDivision(n);
        integral.setResult(s1);
        integral.setInfelicity(Math.abs(s1-s)/15);
        return integral;
    }
    public static double simpson(Integral integral, int n) {
        double h = (integral.getHighLimit() - integral.getLowLimit()) / n;
        double k1 = 0, k2 = 0;
        for (int i = 1; i < n; i += 2) {
            k1 += (double) integral.getFunction().apply(integral.getLowLimit() + i*h);
            k2 += (double) integral.getFunction().apply(integral.getLowLimit() + (i + 1) * h);
        }
        return h/3 *((double) integral.getFunction().apply(integral.getLowLimit()) + 4*k1 + 2*k2);
    }
}
