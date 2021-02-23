package stephane.katende.fsm_calc;

import android.widget.Toast;

public class ErrorState implements State{
    @Override
    public void zero() {

    }

    @Override
    public void nonZeroDigit() {
        System.out.println("You reached me!");


    }

    @Override
    public void mathOP() {
        System.out.println("I am ErrorState with mathOP action");
    }

    @Override
    public void equals() {
        System.out.println("I am Error State of equals input!");
    }

    @Override
    public void clear() {
        MainActivity._CONTEXT.set_buffer(MainActivity._CONTEXT.getBuffer().substring(0, MainActivity._CONTEXT.getBuffer().length() -1));
    }

    @Override
    public void allClear() {
        MainActivity._CONTEXT.resetBuffer();
        MainActivity.upDateScreen("",true);
    }
}
