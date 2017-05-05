package net.xiayule.mockito;

/**
 * Created by tan on 16-1-7.
 */
public class EmptyValueAnswer implements Answer<Object> {
    @Override
    public Object answer(Invocation invocation) {
        return null;
    }
}
