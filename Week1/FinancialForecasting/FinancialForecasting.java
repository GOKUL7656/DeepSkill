public class FinancialForecasting {

    public static double calculateFutureValue(double val, double rate, int yrs) {
        if (yrs == 0) {
            return val;
        }
        double prevYearVal = calculateFutureValue(val, rate, yrs - 1);
        return prevYearVal * (1 + rate);
    }

    public static void main(String[] args) {
        double initInv = 1000.0;
        double growth = 0.05;
        int years = 10;

        System.out.println("--- Our Financial Forecast ---");
        System.out.println("Starting Money: $" + initInv);
        System.out.println("Yearly Growth: " + (growth * 100) + "%");
        System.out.println("Total Years to Forecast: " + years);

        double finalVal = calculateFutureValue(initInv, growth, years);
        System.out.printf("Your money will be: $%.2f after %d years%n", finalVal, years);
        double initAmt2 = 500.0;
        double growth2 = 0.10;
        int yrs2 = 5;
        System.out.printf("Starting with $%.2f at %.0f%% growth for %d years, you'll have: $%.2f%n",
                          initAmt2, growth2 * 100, yrs2, finalVal);
    }
}
