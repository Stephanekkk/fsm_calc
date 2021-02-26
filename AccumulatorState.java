package stephane.katende.fsm_calc;

public class AccumulatorState implements State {
    int i = 0;
    boolean _opUsed;

    @Override
    public void zero() {
        if (_opUsed) {//an operation was used
            MainActivity._CONTEXT.add_bufferv1(MainActivity._lasttypedChar);
            MainActivity.setScreen(MainActivity._CONTEXT.get_bufferv1());
        } else {
            MainActivity._CONTEXT.add_buffer(MainActivity._lasttypedChar);
            MainActivity.updateScreen(Character.toString(MainActivity._lasttypedChar));
        }

    }

    @Override
    public void nonZeroDigit() {
        if (_opUsed) {//an operation was used
            MainActivity._CONTEXT.add_bufferv1(MainActivity._lasttypedChar);
            MainActivity.setScreen(MainActivity._CONTEXT.get_bufferv1());
        } else {
            MainActivity._CONTEXT.add_buffer(MainActivity._lasttypedChar);
            MainActivity.updateScreen(Character.toString(MainActivity._lasttypedChar));
        }
    }

    @Override
    public void mathOP() {
        _opUsed = true;
        if (_opUsed) {
            MainActivity._CONTEXT.add_bufferv1(MainActivity._lasttypedChar);
        } else {
            MainActivity._CONTEXT.add_buffer(MainActivity._lasttypedChar);
        }
    }

    @Override
    public void equals() {

    }

    @Override
    public void clear() {
        if (_opUsed && MainActivity._CONTEXT.get_bufferv1().length() > 1) {
            MainActivity._CONTEXT.set_bufferv1(MainActivity._CONTEXT.get_bufferv1().substring(0, MainActivity._CONTEXT.get_bufferv1().length() - 1));
            MainActivity.setScreen(MainActivity._CONTEXT.get_bufferv1());
        } else {
            if (MainActivity._CONTEXT.get_buffer().length() > 1)
                MainActivity._CONTEXT.set_buffer(MainActivity._CONTEXT.get_buffer().substring(0, MainActivity._CONTEXT.get_buffer().length() - 1));
            MainActivity.setScreen(MainActivity._CONTEXT.get_buffer());
        }

    }

    @Override
    public void allClear() {
        MainActivity._CONTEXT.set_buffer("");
        MainActivity._CONTEXT.set_bufferv1("");
        MainActivity.setScreen("");
    }


}
