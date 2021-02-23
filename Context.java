package stephane.katende.fsm_calc;

public class Context {
    private State _currentState;
    private boolean _pendingOP;
    public static String _state;

    public Context(){
        _currentState = new ZeroState();
        _state = "ZeroState";
    }

    public void setCurrentState(State state){
        _currentState = state;
    }

    /**
     * Set a current state using a string, ZeroState, AccumulatorState, ComputedState, ErrorState,
     * invalid inputs will NOT trigger the right state
     */
    public void setCurrentState(String state){

        switch (state){
            case "ZeroState":
                _currentState = new ZeroState();
                state = "ZeroState";
                break;
            case "AccumulatorState":
                _currentState = new AccumulatorState();
                state = "AccumulatorState";
                break;
            case "ComputedState":
                _currentState = new ComputedState();
                state = "ComputedState";
                break;
            case "ErrorState":
                _currentState = new ErrorState();
                state = "ErrorState";
                break;
        }
        _state = state;
    }


    public String get_currentStateString(){
        return _state;
    }

    /**
     *
     */
    public void zero(){
        _currentState.zero();
    }

    /**
     *
     */
    public void nonZeroDigit() {
        _currentState.nonZeroDigit();
    }

    /**
     *
     */
    public void mathOP() {
        _currentState.mathOP();
    }

    /**
     *
     */
    public void equals() {
        _currentState.equals();
    }

    /**
     * Based on currentstate, what to do when a "clear" action is received
     */
    public void clear() {
        _currentState.clear();
    }

    /**
     * Based on currentstate, what to do when a "clear" action is received
     */
    public void allClear() {
        _currentState.allClear();
    }

    /**
     *
     * @return pendingOP
     */
    public boolean pendingOP(){
        return _currentState.pendingOP();
    }


    /**
     * Change what is displayed on the calculator's screen
     * @param text the text to display, text <= MainActivity.MAX_CHARACTER_COUNT
     */
    public void upDateScreen(String text) {
        if (text.length() <= MainActivity.MAX_CHARACTER_COUNT){
            MainActivity._screen.setText(text);
        }
    }
}
