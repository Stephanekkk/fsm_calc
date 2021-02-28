package stephane.katende.fsm_calc;

public class ZeroState implements State {

    @Override
    /**
     * Ignore
     */
    public void zero() {

    }

    @Override
    /**
     * Adds the char to first buffer (updates screen)
     */
    public void nonZeroDigit() {
        MainActivity._CONTEXT.add_buffer(MainActivity._lasttypedChar);
        MainActivity.updateScreen(Character.toString(MainActivity._lasttypedChar));

    }

    @Override
    /**
     * Appends '-' to buffer( updates screen too), or ignores & resets screen
     */
    public void mathOP() {
        if (MainActivity._lasttypedChar == '-') {
            MainActivity._CONTEXT.add_buffer(MainActivity._lasttypedChar);
            MainActivity.updateScreen(Character.toString(MainActivity._lasttypedChar));
        }
        MainActivity.setScreen("");
    }

    @Override
    /**
     * Resets the screen
     */
    public void equals() {
        MainActivity.setScreen("");
    }

    @Override
    /**
     * Resets the screen
     */
    public void clear() {
        MainActivity.setScreen("");
    }

    @Override
    /**
     * Resets the two buffer & screen
     */
    public void allClear() {
        //ignore
        MainActivity._CONTEXT.set_buffer("");
        MainActivity._CONTEXT.set_secBuffer("");
        MainActivity.setScreen("");
    }


}
