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
        MainActivity._CONTEXT.set_buffer(MainActivity._CONTEXT.getBuffer().substring(0, MainActivity._CONTEXT.getBuffer().length() -1));
    }

    @Override
    public void allClear() {
        MainActivity._CONTEXT.resetBuffer();
        MainActivity.upDateScreen("",true);
    }

}
