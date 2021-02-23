package stephane.katende.fsm_calc;


/**
 * A state, all the possible inputs a state needs to deal with
 */
public interface State {
    /**
     * When a zero is pressed
     */
    public void zero();

    /**
     * When a none zero digit is pressed 1-9
     */
    public void nonZeroDigit();

    /**
     * When a math operator is pressed (+,-,*,/)
     */
    public void mathOP();

    /**
     * When an equals is pressed
     */
    public void equals();

    /**
     * When a clear is pressed
     */
    public void clear();

    /**
     * When an allClear is pressed
     */
    public void allClear();






}


