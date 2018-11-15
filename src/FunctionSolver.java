import java.util.ArrayList;
import java.util.Scanner;

public class FunctionSolver {

    private String inputEquation;
    private Solver solver;
    private double value;
    private double maxValue;
    private double increment;
    private ArrayList<Double> solutions;

    /*
     * Constructors
     */

    public FunctionSolver () {
        solver = new Solver();
    }

    public FunctionSolver (String pInputEquation, double pValue, double pMaxValue, double pIncrement) {
        solver = new Solver(pInputEquation);
        inputEquation = pInputEquation;
        value = pValue;
        maxValue = pMaxValue;
        increment = pIncrement;
        solutions = new ArrayList<>();


    }

    public FunctionSolver (String pInputEquation) {
        inputEquation = pInputEquation;
        solver = new Solver(inputEquation);
    }

    /* 
     * Action methods
     */

    public String solveEachFunction () {

        if (checkForVariable()) {
            while (value <= maxValue) {
                solutions.add(solver.solve(value));
                value += increment;
            }
            return getFunctionTable(solutions);
        } else {
            return Double.toString(solver.solve());
        }

    }
     
    public double solveFunction() {
        return solver.solve();
    }

    private boolean checkForVariable () {
        Scanner in = new Scanner(inputEquation);
        int counter = 0;
        while (in.hasNext()) {
            if (in.next().equals("x")) {
                counter++;
            }
        }
        System.out.println(counter);
        if (counter > 0) {
            return true;
        } else {
            return false;
        }

    }

    /*
     * Mutator methods
     */

     public void setInputEquation (String pInputEquation) {
         inputEquation = pInputEquation;
     }

    /*
     * Accessor methods
     */

    public String getInputEquation () {
        return inputEquation;
    }

    private String getFunctionTable(ArrayList<Double> pSolutions) {
        String output = "";
        for (Double d : pSolutions) {
            output = d + "\n";
        }
        return output;
    }
    

}