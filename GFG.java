import java.util.*;

public class GFG {

    public static void main(String[] args) {

        // Define tax brackets
        List<List<Double>> levels = new ArrayList<>();

        levels.add(Arrays.asList(10000.0, 0.3));  // First bracket: 0 to 10,000 at 30%
        levels.add(Arrays.asList(20000.0, 0.2));  // Second bracket: 10,000 to 20,000 at 20%
        levels.add(Arrays.asList(30000.0, 0.1));  // Third bracket: 20,000 to 30,000 at 10%
        levels.add(Arrays.asList(null, 0.1));     // Fourth bracket: anything above 30,000 at 10%

        double salary = 45000;  // Example salary
        double tax = calculateTax(levels, salary);

        System.out.println("Total tax: " + tax);  // Should print the total tax for the salary
    }

    // Function to calculate the total tax based on the given levels and salary
    public static double calculateTax(List<List<Double>> levels, double salary) {
        double tax = 0.0;  // Accumulated tax
        double previousLimit = 0.0;  // The lower limit of the current bracket

        // Iterate over each tax bracket
        for (List<Double> level : levels) {
            Double upperLimit = level.get(0);  // The upper limit of the current bracket (can be null)
            Double rate = level.get(1);        // The tax rate for the current bracket

            // If there is an upper limit for the current bracket
            if (upperLimit != null) {
                // If the salary exceeds the upper limit of this bracket, tax the entire bracket
                if (salary > upperLimit) {
                    tax += (upperLimit - previousLimit) * rate;
                } 
                // Otherwise, tax only the portion of the salary that falls within this bracket
                else {
                    tax += (salary - previousLimit) * rate;
                    break;  // Salary fully taxed, no need to process further brackets
                }
            } 
            // If no upper limit (last bracket), tax the remaining salary at the current rate
            else {
                tax += (salary - previousLimit) * rate;
                break;  // Since this is the last bracket, we can stop
            }

            // Move the lower limit to the upper limit for the next bracket
            previousLimit = upperLimit;
        }

        return tax;  // Return the total calculated tax
    }
}
