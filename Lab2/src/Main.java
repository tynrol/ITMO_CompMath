public class Main {
    public static void main(String[] args) {
        System.out.println("Program for integration Simpson's method with precision selection");
        try {
            Integral integral = null;
            ConsoleReader consoleReader = new ConsoleReader();
            consoleReader.readConsole();
            integral = consoleReader.getIntegral();
            Integral answer = SimsonMethod.calculate(integral);
            output(answer);
        } catch (Exception e){
            System.out.println("Something went wrong");
            System.exit(-1);
        }
    }
    public static void output(Integral integral){
        System.out.println("\nResult: " + integral.getResult() +
                        "\nAmount of divisions: " + integral.getAmountDivision() +
                        "\nInfelicity: " + integral.getInfelicity() + "\n"
                        );
    }
}
