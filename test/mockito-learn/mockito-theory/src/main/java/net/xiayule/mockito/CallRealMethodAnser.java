package net.xiayule.mockito;

/**
 * Created by tan on 16-1-6.
 */
public class CallRealMethodAnser implements Answer<Object> {
    @Override
    public Object answer(Invocation invocation) {
        return invocation.callRealMethod();
    }
}
