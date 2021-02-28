package stephane.katende.fsm_calc;
/**
 * @Author Stephane Katende made with ❤ 2/20/21, the application used (tried to anyway) the design pattern described here https://fsharpforfunandprofit.com/posts/calculator-complete-v2/
 * and uses the principles described here https://en.wikipedia.org/wiki/Single-responsibility_principle
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    public static final int MAX_CHARACTER_COUNT = 25; //max number of char the text field can handle before overflow
    public static Context _CONTEXT = new Context(); //the context object to control states
    public static TextView _screen; // the text field of the calc
    public static TextView _showOp; //shows last typed op
    public static char _lasttypedChar; //gives the last typed char
    public static inputType _lasttypedEnum; //gives the last typed inputType

    public enum inputType {zero, nonZeroDigit, mathOP, equals, clear, allClear}//an enum of all the possible types of inputs

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _screen = findViewById(R.id.answerView);
        _showOp = findViewById(R.id.opView);

    }

    /**
     * For Testing purposes
     *
     * @param args <3
     */
    public static void main(String[] args) {
//        String x = "++1245///";
//        char[] x1 = new char[x.length()];
//
//
//        Scanner x2 = new Scanner(String.valueOf(x1));
//        Scanner x3 = new Scanner(x);
//        x3.useDelimiter("");
//        System.out.print(x3.next());
//        System.out.print(x3.next() + "\n");
//        System.out.print(x3.nextLine());
//
//        for (int i = 0; i < x.length(); i++) {
//            while (x3.hasNext()) {
//                x1[i] = x3.next().charAt(0);
//            }
//        }
//
//        System.out.print(x1[0] + "\n");
//        System.out.print(x1[1] + "\n");
//        System.out.print(x1[2]);

        String x = "-.0";
        String y = "-5";
        System.out.println(Double.valueOf(x));
        System.out.println(Double.parseDouble(y));
        boolean xy = !false;
        boolean xyy = !(!false); //? hmm

        System.out.println(2 / 0);

    }

    /**
     * Actions taken when a button is pressed
     *
     * @param view the app view
     */
    public void buttonPressed(View view) {
        int id = view.getId(); //what button is pressed?
        char buttonValue = valuePressed(id); //what is the char value of it?
        _lasttypedChar = buttonValue;
        inputType input = buttonType(valuePressed(id)); //what type of input is it?
        _lasttypedEnum = input;
        transitionState(_CONTEXT.get_currentStateString(), input);// what state should it be sent to?
    }

    /**
     * Given a button id it will return the char value of it, 'E' is returned if no id found
     *
     * @param id the button id
     * @return the character value of each button , 'A' for all clear, 'C' for all clear
     */
    char valuePressed(int id) {
        switch (id) {
            case R.id.button_one:
                return '1';
            case R.id.button_two:
                return '2';
            case R.id.button_three:
                return '3';
            case R.id.button_four:
                return '4';
            case R.id.button_five:
                return '5';
            case R.id.button_six:
                return '6';
            case R.id.button_seven:
                return '7';
            case R.id.button_eight:
                return '8';
            case R.id.button_nine:
                return '9';
            case R.id.button_zero:
                return '0';
            case R.id.button_plus:
                return '+';
            case R.id.button_minus:
                return '-';
            case R.id.button_divide:
                return '/';
            case R.id.button_multiply:
                return '*';
            case R.id.button_decimal:
                return '.';
            case R.id.button_equals:
                return '=';
            case R.id.ac_button:
                return 'A';
            case R.id.c_button:
                return 'C';
        }
        return 'E';
    }


    /**
     * Gives an inputType based on the char
     *
     * @return inputType the type of input it is, i.e '9' will return InputType.nonZeroDigit
     */
    inputType buttonType(char x) {
        if (x == '0') {
            return inputType.zero;
        } else if (x == '/' || x == '-' || x == '+' || x == '*') {//operations
            return inputType.mathOP;
        } else if (x == '=') {
            return inputType.equals;
        } else if (x == 'A') {
            return inputType.allClear;
        } else if (x == 'C') {
            return inputType.clear;
        } else {
            return inputType.nonZeroDigit;
        }
    }

    /**
     * Given a current State and type of input, send me to the right state and take care of business
     *
     * @param currentState the state currently in
     * @param input        the type of input
     */
    public static void transitionState(String currentState, inputType input) {
        /**   ALL ZeroState transitions                  */
        if (currentState == "ZeroState" && input == inputType.zero) {
            _CONTEXT.zero();
        } else if (currentState == "ZeroState" && input == inputType.nonZeroDigit) {
            _CONTEXT.nonZeroDigit();
            _CONTEXT.setCurrentState("AccumulatorState");
        } else if (currentState == "ZeroState" && input == inputType.mathOP) {
            _CONTEXT.mathOP();
            _CONTEXT.setCurrentState("AccumulatorState");
        } else if (currentState == "ZeroState" && input == inputType.equals) {
            _CONTEXT.equals();
        } else if (currentState == "ZeroState" && input == inputType.clear) {
            _CONTEXT.clear();
        } else if (currentState == "ZeroState" && input == inputType.allClear) {
            _CONTEXT.allClear();
        }
        /**   ALL AccumulatorState transitions                  */
        if (currentState == "AccumulatorState" && input == inputType.zero) {
            _CONTEXT.zero();
        } else if (currentState == "AccumulatorState" && input == inputType.nonZeroDigit) {
            _CONTEXT.nonZeroDigit();
        } else if (currentState == "AccumulatorState" && input == inputType.mathOP) {
            _CONTEXT.mathOP();
        } else if (currentState == "AccumulatorState" && input == inputType.equals) {
            _CONTEXT.setCurrentState("ComputedState");
            _CONTEXT.equals();
        } else if (currentState == "AccumulatorState" && input == inputType.clear) {
            _CONTEXT.clear();
        } else if (currentState == "AccumulatorState" && input == inputType.allClear) {
            _CONTEXT.allClear();
            _CONTEXT.setCurrentState("ZeroState");
        }
        /**   ALL ComputedState transitions                  */
        if (currentState == "ComputedState" && input == inputType.zero) {
            _CONTEXT.setCurrentState("ZeroState");
            _CONTEXT.allClear();
            _CONTEXT.zero();
        } else if (currentState == "ComputedState" && input == inputType.nonZeroDigit) {
            _CONTEXT.nonZeroDigit();
            _CONTEXT.setCurrentState("AccumulatorState");
        } else if (currentState == "ComputedState" && input == inputType.mathOP) {

        } else if (currentState == "ComputedState" && input == inputType.equals) {
            _CONTEXT.setCurrentState("ZeroState");
            _CONTEXT.equals();
        } else if (currentState == "ComputedState" && input == inputType.clear) {
            _CONTEXT.setCurrentState("ZeroState");
            _CONTEXT.clear();
        } else if (currentState == "ComputedState" && input == inputType.allClear) {
            _CONTEXT.setCurrentState("ZeroState");
            _CONTEXT.allClear();
        }

    }

    /**
     * Append text to the calc's screen
     *
     * @param x the string
     */
    public static void updateScreen(String x) {
        if (_lasttypedEnum == inputType.nonZeroDigit || _lasttypedEnum == inputType.zero || _lasttypedChar == '-')//only print numbers and negative operation
            _screen.append(x);
    }

    /**
     * Set text to the calc's screen
     *
     * @param x the string
     */
    public static void setScreen(String x) {
        _screen.setText(x); //only print numbers and operations )

    }

    /**
     * Set text to the calc's lastOp screen
     *
     * @param x
     */
    public static void updateLastOp(String x) {
        _showOp.setText(x);
    }
}

