import java.util.Scanner;
import java.util.function.Function;

public class ConsoleReader {
    private Integral integral;
    private Function<Double, Double> sin = Math::sin;
    private Function<Double, Double> exp = Math::exp;
    private Function<Double, Double> reverse = x -> 1/x;
    private Function<Double, Double> parabola = x -> Math.pow(x,2);

    public ConsoleReader(){}

    public void readConsole() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Choose function:\n" +
                    "1 y=sin(x)\n" +
                    "2 y=e^x\n" +
                    "3 y=1/x\n" +
                    "4 y=x^2\n" +
                    "Enter only number. Example: 4"
                    );

            int equation = scanner.nextInt();
            Function function = null;
            switch (equation){
                case (1):
                    function = sin;
                    break;
                case (2):
                    function = exp;
                    break;
                case (3):
                    function = reverse;
                    break;
                case (4):
                    function = parabola;
                    break;
                default:
                    System.out.println("No such function");
                    System.exit(1);
            }
            System.out.println("Enter the lower boarder");
            double lowLimit = scanner.nextDouble();
            System.out.println("Enter the higher boarder");
            double highLimit = scanner.nextDouble();
            System.out.println("Enter the precision");
            double precision = scanner.nextDouble();
            integral = new Integral(function,lowLimit,highLimit,precision);
        } catch (NumberFormatException e){
            System.out.println("Not a number");
            System.exit(1);
        } catch (NullPointerException e) {
            System.out.println("Input error");
            System.exit(1);
        }
    }

    public Integral getIntegral() {
        return integral;
    }
    public void setIntegral(Integral integral) {
        this.integral = integral;
    }
}
