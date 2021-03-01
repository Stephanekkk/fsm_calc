package stephane.katende.fsm_calc;

import java.util.Scanner;

public class ComputedState implements State {

    @Override
    /**
     * Resets the screen as a precautionary measure
     */
    public void zero() {
        MainActivity.setScreen("");
    }

    @Override
    /**
     * Resets the two buffer & screen, adds to the appropriate buffer (updates screen too)
     */
    public void nonZeroDigit() {
        MainActivity.setScreen("");
        MainActivity._CONTEXT.set_buffer("");
        MainActivity._CONTEXT.set_secBuffer("");
        MainActivity._CONTEXT.add_buffer(MainActivity._lasttypedChar);
        MainActivity.updateScreen(Character.toString(MainActivity._lasttypedChar));
    }

    @Override
    /**
     * Resets the screen as a precautionary measure
     */
    public void mathOP() {
        MainActivity.setScreen("");
    }

    @Override
    /**
     * Finds an answer (if there is one, updates the screen too), resets the two buffer & screen
     */
    public void equals() {
        if (factChecker()) {
            double x = showAnswer();
            String.format("%.002f", x);
            MainActivity.setScreen(String.valueOf(x));
            //show last typed operation
            if (MainActivity._CONTEXT.get_buffer().length() > 0 && MainActivity._CONTEXT.get_secBuffer().length() > 0) {//saves charAt & substring from crashing incase of "" buffer
                MainActivity.updateLastOp("Last Input : " + MainActivity._CONTEXT.get_buffer() + " "
                        + MainActivity._CONTEXT.get_secBuffer().charAt(0) + " " + MainActivity._CONTEXT.get_secBuffer().substring(1));
            }

        } else {
            //show last typed operation
            if (MainActivity._CONTEXT.get_buffer().length() > 0 && MainActivity._CONTEXT.get_secBuffer().length() > 0) {
                MainActivity.updateLastOp("Last Input : " + MainActivity._CONTEXT.get_buffer() + " "
                        + MainActivity._CONTEXT.get_secBuffer().charAt(0) + " " + MainActivity._CONTEXT.get_secBuffer().substring(1));
            }
            //reset everything
            MainActivity.setScreen("");
            MainActivity._CONTEXT.set_buffer("");
            MainActivity._CONTEXT.set_secBuffer("");
        }

    }

    /**
     * Compute an answer from the two buffers
     *
     * @return the answer, 0.0 returned if nothing found
     */
    double showAnswer() {
        double x = 0, y = 0;
        x = Double.parseDouble(MainActivity._CONTEXT.get_buffer());
        //getting the second number
        Scanner x1 = new Scanner(MainActivity._CONTEXT.get_secBuffer());
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
     * @return true = an answer can be found, false = not computable (errors within either buffers)
     */
    boolean factChecker() {
        String first = MainActivity._CONTEXT.get_buffer(); //first char could be a '-'?
        String second = MainActivity._CONTEXT.get_secBuffer(); //first characters will be operations, second char could be a '-'?
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


        if (first.length() >= 1 && second.length() > 1) { //must have more than 2 characters

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
        return true; // no errors detected,  all is good (hopefully)!
    }

    @Override
    /**
     * Resets the screen as a precautionary measure
     */
    public void clear() {
        MainActivity.setScreen("");
    }

    @Override
    /**
     * Resets the screen as a precautionary measure
     */
    public void allClear() {
        MainActivity.setScreen("");
    }

}
