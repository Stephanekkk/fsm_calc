package stephane.katende.fsm_calc;

import android.widget.Toast;

import java.util.Scanner;

public class ComputedState implements State {
    double _secondInput;

    @Override
    public void zero() {
        //ignore
        MainActivity.setScreen("");
    }

    @Override
    public void nonZeroDigit() {
        MainActivity.setScreen("");
        MainActivity._CONTEXT.set_buffer("");
        MainActivity._CONTEXT.set_bufferv1("");
        MainActivity._CONTEXT.add_buffer(MainActivity._lasttypedChar);
        MainActivity.updateScreen(Character.toString(MainActivity._lasttypedChar));
    }

    @Override
    public void mathOP() {
        //ignore

        MainActivity.setScreen("");
    }

    @Override
    public void equals() {

        if (factChecker()) {
            double x = showAnswer();
            String.format("%.002f", x);
            MainActivity.setScreen(String.valueOf(x));
            //show last typed operation
            MainActivity.updateLastOp("Last Input : " + MainActivity._CONTEXT.get_buffer() + " "
                    + MainActivity._CONTEXT.get_bufferv1().charAt(0) + " " + MainActivity._CONTEXT.get_bufferv1().substring(1));
        } else {
            //show last typed operation
            MainActivity.updateLastOp("Last Input : " + MainActivity._CONTEXT.get_buffer() + " "
                    + MainActivity._CONTEXT.get_bufferv1().charAt(0) + " " + MainActivity._CONTEXT.get_bufferv1().substring(1));
            //reset everything
            MainActivity.setScreen("");
            MainActivity._CONTEXT.set_buffer("");
            MainActivity._CONTEXT.set_bufferv1("");
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

            //dive by zero
            if (secondNums[0] == '/' && secondNums[1] == '0')
                return false;

            //loop through the whole thing and make sure no double operators
            for (int i = 1; i <= second.length() - 1; i++) {
                if (secondNums[i] == '+' || secondNums[i] == '*' || secondNums[i] == '/') {
                    return false;
                }
                continue;
            }
            //loop and only allow two "-"
            int negCounter = 0;
            for (int i = 0; i < second.length(); i++) {
                if (secondNums[i] == '-')
                    negCounter++;
                continue;
            }
            if (negCounter > 2)
                return false;
            //if length <= 2, second char must be a number!
            if ((second.length() == 2) && (secondNums[1] == '-' || secondNums[1] == '+' || secondNums[1] == '*' || secondNums[1] == '/'))
                return false;


            //looping and finding multiple decimals
            int decimalCounter = 0; //1 is the max allowed
            for (int i = 0; i < first.length(); i++) {
                if (firstNums[i] == '.') {
                    decimalCounter++;
                }
                continue;

            }
            int decimalCounterv2 = 0;
            for (int i = 0; i < second.length(); i++) {
                if (secondNums[i] == '.') {
                    decimalCounterv2++;
                }
                continue;

            }
            if (decimalCounter > 1 || decimalCounterv2 > 1)
                return false;
        } else {
            return false;

        }
        return true; // errors dectected all is good!
    }

    @Override
    public void clear() {
        //ignore
        MainActivity.setScreen("");

    }

    @Override
    public void allClear() {
        //ignore
        MainActivity.setScreen("");

    }

}
