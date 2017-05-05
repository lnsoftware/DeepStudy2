package net.xiayule.mockito;

/**
 * Created by tan on 16-1-8.
 */
public class Return implements Answer<Object> {
    private final Object value;

    public Return(Object value) {
        this.value = value;
    }

    @Override
    public Object answer(Invocation invocation) {
        return value;
    }
}
