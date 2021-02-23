package stephane.katende.fsm_calc;

public class ZeroState implements State{
    private boolean _pendingOp = false; //false indicates nothing has been typed yet, true means a valid argument has already been given to the user
    private String _operation; //the number user typed

    @Override
    public void zero() {
        if(_pendingOp){//not the first time here!



        }else{//first time here



            _pendingOp = true;
        }

    }

    @Override
    public void nonZeroDigit() {
        if(_pendingOp){//not the first time here!



        }else{//first time here



            _pendingOp = true;
        }

    }

    @Override
    public void mathOP() {
        if(_pendingOp){//not the first time here!



        }else{//first time here



            _pendingOp = true;
        }

    }

    @Override
    public void equals() {
        if(_pendingOp){//not the first time here!



        }else{//first time here



            _pendingOp = true;
        }

    }

    @Override
    public void clear() {
        if(_pendingOp){//not the first time here!



        }else{//first time here



            _pendingOp = true;
        }
    }

    @Override
    public void allClear() {
        if(_pendingOp){//not the first time here!



        }else{//first time here



            _pendingOp = true;
        }

    }

    @Override
    public boolean pendingOP() {
        return false;
    }


}
