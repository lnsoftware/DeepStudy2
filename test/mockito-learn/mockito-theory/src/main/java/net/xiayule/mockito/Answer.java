package net.xiayule.mockito;

/**
 * Created by tan on 16-1-6.
 */
public interface Answer<T> {
    T answer(Invocation invocation);
}
