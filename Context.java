package stephane.katende.fsm_calc;

public class Context {
    private State _currentState;
    public static String _state;
    public String _buffer = ""; //the buffer holds all the inputs (none spaced)

    /**
     * get the current buffer (inputs typed)
     * @return
     */
    public String getBuffer() {
        return _buffer;
    }

    public void resetBuffer(){
        _buffer = "";
    }

    /**
     * set the buffer & display to the screen
     */
    public void set_buffer(String text){
        if (_buffer.length() > 1){
            this._buffer = text;
            MainActivity.upDateScreen(_buffer,true); }

    }

    /**
     * add to the buffer (the inputs) & display to the screen
     * @param _buffer
     */
    public void addtoBuffer(char _buffer) {
        this._buffer += _buffer;
        MainActivity.upDateScreen(Character.toString(MainActivity._lasttypedChar));



    }

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



}
