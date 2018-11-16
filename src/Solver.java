import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Scanner;
import java.lang.Double;
import java.lang.Math;

public class Solver {

    private Stack<String> operators;
    private Stack<Double> operands;
    private String equation;
    private StringTokenizer tokens;
    private double valueOfX;
    
    /*
     * Constructors
     */
    public Solver () {
        operators = new Stack<>();
        operands = new Stack<>();
    }

    public Solver (String pEquation) {
        equation = pEquation;
        tokens = new StringTokenizer(pEquation);
        operators = new Stack<>();
        operands = new Stack<>();
    }

    public Solver (String pEquation, double pVariableValue) {
        equation = pEquation;
        tokens = new StringTokenizer(pEquation);
        operators = new Stack<>(); // store + - / * ( )
        operands = new Stack<>(); // store numbers
        valueOfX = pVariableValue;

    }


    /*
     * Methods
     */
    
    public double solve () {
        String currentToken;
        while (tokens.hasMoreTokens()) {
            currentToken = tokens.nextToken();
            if (isDouble(currentToken)) { // need to write isDoubleMethod
                Double d = Double.parseDouble(currentToken);
                operands.push(d);
            } else {
                if (operators.size() != 0 && checkOperatorRules(currentToken)) {
                    topEval();
                } else {
                    operators.push(currentToken);
                }
            }
        }
        return finalEval(operands.pop());
    }

    public double solve (double pX) {
        String currentToken;
        while (tokens.hasMoreTokens()) {
            currentToken = tokens.nextToken();
            if (isDouble(currentToken)) { // need to write isDoubleMethod
                Double d = Double.parseDouble(currentToken);
                operands.push(d);
            } else if (currentToken.equals("x")) {
                operands.push(pX);
            } else {
                if (operators.size() != 0 && checkOperatorRules(currentToken)) {
                    topEval();
                } else {
                    operators.push(currentToken);
                }
            }
        }
        return finalEval(operands.pop());
    }

    private boolean checkOperatorRules (String pOperator) {
        String previousOperator = operators.peek();
        if (previousOperator == "*" || previousOperator == "/" && pOperator == "+" || pOperator == "-") {
           return true;
        } else {
            return false;
        }
    }

    private static boolean isDouble(String inToken) {
        try {
            Double d = Double.parseDouble(inToken);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void topEval () {
        double num1 = operands.pop();
        double num2 = operands.pop();
        String prevOperator = operators.pop();
        double ans = 0;

        if (prevOperator.equals("+")) {
            ans = MathOperations.add(num1, num2);
        } else if (prevOperator.equals("-")) {
            ans = MathOperations.subtract(num1, num2);
        } else if (prevOperator.equals("*")) {
            ans = MathOperations.multiply(num1, num2);
        } else {
            ans = MathOperations.divide(num1, num2);
        }

        operands.push(ans);

    }

    private double finalEval(double currentTotal) {
        while(!operands.isEmpty()) {
            double nextOperand = operands.pop();
            String operator = operators.pop();
            if (operator.equals("+")) {
                return finalEval(MathOperations.add(currentTotal, nextOperand));
            } else if (operator.equals("-")) {
                return finalEval(MathOperations.subtract(currentTotal, nextOperand));
            } else if (operator.equals("*")) {
                return finalEval(MathOperations.multiply(currentTotal, nextOperand));
            } else if (operator.equals("/")) {
                return finalEval(MathOperations.divide(currentTotal, nextOperand));
            }
        }
        return currentTotal;
    }


}