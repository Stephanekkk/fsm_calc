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

    }

    @Override
    public void allClear() {

    }

    @Override
    public boolean pendingOP() {
        return false;
    }


    public void upDateScreen() {

    }
}
