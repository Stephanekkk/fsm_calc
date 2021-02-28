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
        if (MainActivity._lasttypedChar == '-'){
            MainActivity._CONTEXT.add_buffer(MainActivity._lasttypedChar);
            MainActivity.updateScreen(Character.toString(MainActivity._lasttypedChar));
        }
        //ignore
        MainActivity.setScreen("");
    }

    @Override
    public void equals() {
        //ignore
        MainActivity.setScreen("");
    }

    @Override
    public void clear() {
        //ignore
        MainActivity.setScreen("");
    }

    @Override
    public void allClear() {
        //ignore
        MainActivity._CONTEXT.set_buffer("");
        MainActivity._CONTEXT.set_bufferv1("");
        MainActivity.setScreen("");
    }


}
