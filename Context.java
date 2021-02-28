package stephane.katende.fsm_calc;

public class Context {
    private State _currentState;
    private static String _state;
    private String _buffer = ""; //first set of numbers
    private String _secBuffer = ""; //second set of numbers

    public Context() {
        _currentState = new ZeroState();
        _state = "ZeroState";
    }

    /**
     * Get the first buffer
     *
     * @return the buffer
     */
    public String get_buffer() {
        return _buffer;
    }

    /**
     * Set the first buffer
     */
    public void set_buffer(String _buffer) {
        this._buffer = _buffer;

    }

    /**
     * Add to the first buffer
     *
     * @param x the char to add
     */
    public void add_buffer(char x) {
        if (get_buffer().length() + 1 < MainActivity.MAX_CHARACTER_COUNT)
            _buffer += x;
    }

    /**
     * Get the second buffer
     *
     * @return the buffer
     */
    public String get_secBuffer() {
        return _secBuffer;
    }

    /**
     * Set the second buffer
     *
     * @param _secBuffer the string
     */
    public void set_secBuffer(String _secBuffer) {
        this._secBuffer = _secBuffer;
    }

    /**
     * Add to the first buffer
     *
     * @param x the char to add
     */
    public void add_bufferv1(char x) {
        if (get_secBuffer().length() + 1 < MainActivity.MAX_CHARACTER_COUNT)
            this._secBuffer += x;
    }

    /**
     * Set a current state, ZeroState is the default should input be invalid ("blah blah")
     *
     * @param state the state i.e ZeroState, AccumulatorState, ComputedState, ErrorState,
     */
    public void setCurrentState(String state) {

        switch (state) {
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
            default:
                _currentState = new ZeroState();
        }
        _state = state;
    }

    /**
     * Get the current State
     *
     * @return the current state
     */
    public String get_currentStateString() {
        return _state;
    }

    /**
     * Call zero
     */
    public void zero() {
        _currentState.zero();
    }

    /**
     * Call nonZeroDigit
     */
    public void nonZeroDigit() {
        _currentState.nonZeroDigit();
    }

    /**
     * Call mathOP
     */
    public void mathOP() {
        _currentState.mathOP();
    }

    /**
     * Call equals
     */
    public void equals() {
        _currentState.equals();
    }

    /**
     * Call clear
     */
    public void clear() {
        _currentState.clear();
    }

    /**
     * Call allclear
     */
    public void allClear() {
        _currentState.allClear();
    }


}
