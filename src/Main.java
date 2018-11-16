import java.util.Scanner;

public class Main {

    public static void main (String[] args) {
        Main main = new Main();
        main.run();
    }

    public void run () {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter an equation to solve: ");
        String inEquation = in.nextLine();
        FunctionSolver solveIt = new FunctionSolver(inEquation, 1, 10, 1);
        // FunctionSolver solveIt = new FunctionSolver(inEquation);
        String answer = solveIt.solveEachFunction();
        System.out.println("The answer: " + answer);
    }

}