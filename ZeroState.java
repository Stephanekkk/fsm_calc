package stephane.katende.fsm_calc;

public class ZeroState implements State {

    @Override
    public void zero() {
        //ignore
    }

    @Override
    public void nonZeroDigit() {
        MainActivity._CONTEXT.add_buffer(MainActivity._lasttypedChar);
        MainActivity.updateScreen(Character.toString(MainActivity._lasttypedChar));

    }

    @Override
    public void mathOP() {
        //ignore
    }

    @Override
    public void equals() {
        //ignore
    }

    @Override
    public void clear() {
        //ignore
    }

    @Override
    public void allClear() {
        //ignore
        MainActivity._CONTEXT.set_buffer("");
        MainActivity._CONTEXT.set_bufferv1("");
        MainActivity.setScreen("");
    }


}
