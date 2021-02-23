package stephane.katende.fsm_calc;
public class ComputedState implements State{
    @Override
    public void zero() {

    }

    @Override
    public void nonZeroDigit() {

    }

    @Override
    public void mathOP() {

    }

    @Override
    public void equals() {
            System.out.println("I am Computed State of equals input!");
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
