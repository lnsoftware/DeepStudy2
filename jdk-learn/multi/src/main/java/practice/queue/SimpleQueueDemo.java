package practice.queue;

public interface SimpleQueueDemo<E> {
    void put(E e);
	
	E take();
}