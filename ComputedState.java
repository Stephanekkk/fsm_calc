package stephane.katende.fsm_calc;

import android.widget.Toast;

import java.util.Scanner;

public class ComputedState implements State {
    @Override
    public void zero() {
        //ignore
    }

    @Override
    public void nonZeroDigit() {
        //ignore
    }

    @Override
    public void mathOP() {
        //ignore
    }

    @Override
    public void equals() {

        if (factChecker()) {
            double x = showAnswer();
            String.format("%.2f", x);
            MainActivity.setScreen(String.valueOf(x));
        }

    }

    /**
     * computes an answer of the first input [last operator used] second input(as doubles), assumes the inputs are valid!
     *
     * @return first input [last operator used] second input, i.e "1" + "1" will give results "1.0"
     */
    double showAnswer() {
        double x = 0, y = 0;
        x = Double.parseDouble(MainActivity._CONTEXT.get_buffer());
        //getting the second number
        Scanner x1 = new Scanner(MainActivity._CONTEXT.get_bufferv1());
        x1.useDelimiter("");
        String x2 = x1.next(); //gets rid of the operation
        y = Double.parseDouble(x1.nextLine());

        switch (x2) {
            case "+":
                return x + y;
            case "-":
                return x - y;
            case "*":
                return x * y;
            case "/":
                return x / y;
        }

        return 0.0;
    }

    /**
     * Can an answer be computed?
     *
     * @return true = an answer can be found, false = not computable
     */
    boolean factChecker() {
        String first = MainActivity._CONTEXT.get_buffer();
        String second = MainActivity._CONTEXT.get_bufferv1(); //first characters will be operations
        Scanner secondScanner = new Scanner(second);
        secondScanner.useDelimiter("");
        char[] firstNums = new char[first.length()];
        char[] secondNums = new char[second.length()];
        //put all elements in a char[]
        for (int i = 0; i < first.length(); i++) {
            firstNums[i] = first.charAt(i);
        }

        for (int i = 0; i < second.length(); i++) {
            secondNums[i] = second.charAt(i);
        }

        /** DEALING WITH MOST COMMON ERROR */
        if (second.length() > 1) { //must have more than 2 characters
            if (secondNums[1] == '0') {
                return false; //dive by 0
            }


            //loop through the whole thing and make sure no double operators
            for (int i = 1; i <= second.length() - 1; i++) {
                if (secondNums[i] == '+' || secondNums[i] == '-' || secondNums[i] == '*' || secondNums[i] == '/') {
                    return false;
                }
                continue;
            }

            //looping and finding multiple decimals
            int decimalCounter = 0; //2 is the max allowed
            for (int i = 0, j = 0; i < first.length() && j < second.length(); i++, j++) {
                if (firstNums[i] == '.' || secondNums[i] == '.') {
                    decimalCounter++;
                }
                continue;

            }

            if (decimalCounter > 1) {
                return false;
            }


        }else{
            return false;

        }
        return true;

    }

    @Override
    public void clear() {
        //ignore
    }

    @Override
    public void allClear() {
        //ignore
    }

}
