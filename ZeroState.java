package stephane.katende.fsm_calc;

public class ZeroState implements State{


    @Override
    public void zero() {
            //ignore
    }

    @Override
    public void nonZeroDigit() {
        MainActivity._CONTEXT.addtoBuffer(MainActivity._lasttypedChar);
    }

    @Override
    public void mathOP() {
        // ignore
    }

    @Override
    public void equals() {
        // ignore
    }

    @Override
    public void clear() {
        MainActivity._CONTEXT.set_buffer(MainActivity._CONTEXT.getBuffer().substring(0, MainActivity._CONTEXT.getBuffer().length() -1));
    }

    @Override
    public void allClear() {
        MainActivity._CONTEXT.resetBuffer();
        MainActivity.upDateScreen("",true);

    }




}
