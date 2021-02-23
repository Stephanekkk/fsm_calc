package stephane.katende.fsm_calc;
/**
 * @Author Stephane Katende made with ❤ 2/18/21, the application used the design pattern described here https://fsharpforfunandprofit.com/posts/calculator-complete-v2/
 * and uses the principles described here https://en.wikipedia.org/wiki/Single-responsibility_principle
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

// be very careful with objects and try not to make new instances of them, nothing will work how you intended it to :)
public class MainActivity extends AppCompatActivity {
    public static final int MAX_CHARACTER_COUNT = 30; //max number of char the text field can handle before overflow
    public static Context _CONTEXT = new Context(); //the context object to control states
    public static TextView _screen; // the textfield of the app
    public enum inputType {zero, nonZeroDigit, mathOP, equals, clear, allClear}//posible types of inputs



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _screen = findViewById(R.id.answerView);

    }

    /**
     * For Testing purposes
     *
     * @param args <3
     */
    public static void main(String[] args) {

    int y = 0;

    System.out.println("Hello");



    }

    /**
     * @param view
     * @return
     */
    public void buttonPressed(View view) {
        int id = view.getId(); //what button is pressed?
        char buttonValue = valuePressed(id); //what is the char value of it?
        inputType input = buttonType(valuePressed(id)); //what type of input is it?
        transitionState(_CONTEXT.get_currentStateString(), input);// what state should it be sent to?

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
        }else if(currentState == "ZeroState" && input == inputType.nonZeroDigit){

        }else if (currentState == "ZeroState" && input == inputType.mathOP){

        }else if (currentState == "ZeroState" && input == inputType.equals){

        }else if (currentState == "ZeroState" && input == inputType.clear){

        }else if (currentState == "ZeroState" && input == inputType.allClear){

        }
        /**   ALL AccumulatorState transitions                  */
        if (currentState == "AccumulatorState" && input == inputType.zero) {

        }else if(currentState == "AccumulatorState" && input == inputType.nonZeroDigit){

        }else if (currentState == "AccumulatorState" && input == inputType.mathOP){

        }else if (currentState == "AccumulatorState" && input == inputType.equals){

        }else if (currentState == "AccumulatorState" && input == inputType.clear){

        }else if (currentState == "AccumulatorState" && input == inputType.allClear){

        }
        /**   ALL ComputedState transitions                  */
        if (currentState == "ComputedState" && input == inputType.zero) {

        }else if(currentState == "ComputedState" && input == inputType.nonZeroDigit){

        }else if (currentState == "ComputedState" && input == inputType.mathOP){

        }else if (currentState == "ComputedState" && input == inputType.equals){

        }else if (currentState == "ComputedState" && input == inputType.clear){

        }else if (currentState == "ComputedState" && input == inputType.allClear){

        }
        /**   ALL ErrorState transitions                  */
        if (currentState == "ErrorState" && input == inputType.zero) {

        }else if(currentState == "ErrorState" && input == inputType.nonZeroDigit){

        }else if (currentState == "ErrorState" && input == inputType.mathOP){

        }else if (currentState == "ErrorState" && input == inputType.equals){

        }else if (currentState == "ErrorState" && input == inputType.clear){

        }else if (currentState == "ErrorState" && input == inputType.allClear){
            _CONTEXT.setCurrentState("ZeroState");

        }


    }
}

