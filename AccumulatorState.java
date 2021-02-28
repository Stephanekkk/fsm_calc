package stephane.katende.fsm_calc;

public class AccumulatorState implements State {
    boolean _opUsed; //keeps track if an operation has been used yet

    @Override
    /**
     * Adds zero to the appropriate buffer (updates screen too)
     */
    public void zero() {
        if (_opUsed) {//an operation was used
            MainActivity._CONTEXT.add_bufferv1(MainActivity._lasttypedChar);
            MainActivity.setScreen(MainActivity._CONTEXT.get_secBuffer());
        } else {
            MainActivity._CONTEXT.add_buffer(MainActivity._lasttypedChar);
            MainActivity.updateScreen(Character.toString(MainActivity._lasttypedChar));
        }

    }

    @Override
    /**
     * Adds the char to the appropriate buffer (updates screen too)
     */
    public void nonZeroDigit() {
        if (_opUsed) {//an operation was used
            MainActivity._CONTEXT.add_bufferv1(MainActivity._lasttypedChar);
            MainActivity.setScreen(MainActivity._CONTEXT.get_secBuffer());
        } else {
            MainActivity._CONTEXT.add_buffer(MainActivity._lasttypedChar);
            MainActivity.updateScreen(Character.toString(MainActivity._lasttypedChar));
        }
    }

    @Override
    /**
     *Adds the char to the appropriate buffer & turns on _opUsed
     */
    public void mathOP() {
        _opUsed = true;
        if (_opUsed) {
            MainActivity._CONTEXT.add_bufferv1(MainActivity._lasttypedChar);
        } else {
            MainActivity._CONTEXT.add_buffer(MainActivity._lasttypedChar);
        }
    }

    @Override
    /**
     * Ignores
     */
    public void equals() {

    }

    @Override
    /**
     * Removes the last char of the appropriate buffer (updates screen too)
     */
    public void clear() {
        if (_opUsed && MainActivity._CONTEXT.get_secBuffer().length() > 1) {
            MainActivity._CONTEXT.set_secBuffer(MainActivity._CONTEXT.get_secBuffer().substring(0, MainActivity._CONTEXT.get_secBuffer().length() - 1));
            MainActivity.setScreen(MainActivity._CONTEXT.get_secBuffer());
        } else {
            if (MainActivity._CONTEXT.get_buffer().length() > 1)
                MainActivity._CONTEXT.set_buffer(MainActivity._CONTEXT.get_buffer().substring(0, MainActivity._CONTEXT.get_buffer().length() - 1));
            MainActivity.setScreen(MainActivity._CONTEXT.get_buffer());
        }

    }

    @Override
    /**
     * Resets the two buffer & screen
     */
    public void allClear() {
        MainActivity._CONTEXT.set_buffer("");
        MainActivity._CONTEXT.set_secBuffer("");
        MainActivity.setScreen("");
    }


}
