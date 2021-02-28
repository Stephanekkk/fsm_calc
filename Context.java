package stephane.katende.fsm_calc;

public class Context {
    private State _currentState;
    private static String _state;
    private String _buffer = ""; //first set of numbers
    private String _bufferv1 = ""; //second set of numbers

    public String get_buffer() {
        return _buffer;
    }

    public void set_buffer(String _buffer) {
        this._buffer = _buffer;

    }

    public void add_buffer(char x) {
        if (get_buffer().length() + 1 < MainActivity.MAX_CHARACTER_COUNT)
            _buffer += x;
    }

    public String get_bufferv1() {
        return _bufferv1;
    }

    public void set_bufferv1(String _bufferv1) {
        this._bufferv1 = _bufferv1;
    }

    public void add_bufferv1(char x) {
        if (get_bufferv1().length() + 1 < MainActivity.MAX_CHARACTER_COUNT)
            this._bufferv1 += x;
    }

    public Context() {
        _currentState = new ZeroState();
        _state = "ZeroState";
    }


    public void setCurrentState(State state) {
        _currentState = state;
    }

    /**
     * Set a current state using a string, ZeroState, AccumulatorState, ComputedState, ErrorState,
     * invalid inputs will NOT trigger the right state
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
        }
        _state = state;
    }


    public String get_currentStateString() {
        return _state;
    }

    /**
     *
     */
    public void zero() {
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
