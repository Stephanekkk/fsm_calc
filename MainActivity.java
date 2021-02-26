package stephane.katende.fsm_calc;
/**
 * @Author Stephane Katende made with ❤ 2/20/21, the application used the design pattern described here https://fsharpforfunandprofit.com/posts/calculator-complete-v2/
 * and uses the principles described here https://en.wikipedia.org/wiki/Single-responsibility_principle
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Scanner;

// be very careful with objects and try not to make new instances of them, nothing will work how you intended it to :)
public class MainActivity extends AppCompatActivity {
    public static final int MAX_CHARACTER_COUNT = 25; //max number of char the text field can handle before overflow
    public static Context _CONTEXT = new Context(); //the context object to control states
    public static TextView _screen; // the textfield of the app
    public static TextView _help; //displays current state
    public static TextView _help2; //displays buffers
    public static TextView _help3;
    public static char _lasttypedChar;
    public static inputType _lasttypedEnum;

    public enum inputType {zero, nonZeroDigit, mathOP, equals, clear, allClear}//posible types of inputs


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _screen = findViewById(R.id.answerView);
        _help = findViewById(R.id.helperView);
        _help2 = findViewById(R.id.helperView1);
        _help3 = findViewById(R.id.helperView2);
    }

    /**
     * For Testing purposes
     *
     * @param args <3
     */
    public static void main(String[] args) {
        String x = "++1245///";
        char[] x1 = new char[x.length()];


        Scanner x2 = new Scanner(String.valueOf(x1));
        Scanner x3 = new Scanner(x);
        x3.useDelimiter("");
        System.out.print(x3.next());
        System.out.print(x3.next() + "\n");
        System.out.print(x3.nextLine());

        for (int i = 0; i < x.length(); i++) {
            while (x3.hasNext()) {
                x1[i] = x3.next().charAt(0);
            }
        }

        System.out.print(x1[0] + "\n");
        System.out.print(x1[1] + "\n");
        System.out.print(x1[2]);


    }

    /**
     * @param view
     * @return
     */
    public void buttonPressed(View view) {
        int id = view.getId(); //what button is pressed?
        char buttonValue = valuePressed(id); //what is the char value of it?
        _lasttypedChar = buttonValue;
        inputType input = buttonType(valuePressed(id)); //what type of input is it?
        _lasttypedEnum = input;
        transitionState(_CONTEXT.get_currentStateString(), input);// what state should it be sent to?
        //testing purposes
        _help.setText("Current State = " + _CONTEXT.get_currentStateString());
        _help2.setText("First buffer contains = " + _CONTEXT.get_buffer());
        _help3.setText("Second buffer contains = " + _CONTEXT.get_bufferv1());
    }

    /**
     * given a button id it will return the char value of it, '0' is returned if no id found
     *
     * @param id
     * @return the character value of each button , A for allclear & C for clear
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
        return '0';
    }


    /**
     * given a button name it will spit out the type of input it is
     *
     * @return inputType the type of input it is
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
     * Given a current State, and type of input, send me to the right state and take care of business
     *
     * @param
     * @param input
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
        /**   ALL AccumulatorState transitions                  */
        if (currentState == "ComputedState" && input == inputType.zero) {
            _CONTEXT.setCurrentState("ZeroState");
            _CONTEXT.allClear();
            _CONTEXT.zero();
        } else if (currentState == "ComputedState" && input == inputType.nonZeroDigit) {
            _CONTEXT.setCurrentState("ZeroState");
            _CONTEXT.zero();
        } else if (currentState == "ComputedState" && input == inputType.mathOP) {
            _CONTEXT.setCurrentState("ZeroState");
            _CONTEXT.mathOP();
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

    public static void updateScreen(String x) {
        if (MainActivity._lasttypedEnum == MainActivity.inputType.nonZeroDigit || MainActivity._lasttypedEnum == MainActivity.inputType.zero)//only print numbers)
            _screen.append(x);
    }

    public static void setScreen(String x) {
        // if (MainActivity._lasttypedEnum == MainActivity.inputType.nonZeroDigit || MainActivity._lasttypedEnum == MainActivity.inputType.zero)//only print numbers)
        _screen.setText(x);
    }

}

