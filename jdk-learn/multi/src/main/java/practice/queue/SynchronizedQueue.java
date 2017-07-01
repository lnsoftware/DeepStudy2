package practice.queue;

import java.util.Arrays;

public class SynchronizedQueue<E> implements SimpleQueueDemo<E> {

    private Object[] array;
	private int index = 0;
	
	public SynchronizedQueue(int size) {
		array = new Object[size];
	}
	
	@Override
	public synchronized void put(E item) {
		while(isFull()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		array[index++] = item;
		
		this.notifyAll();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public synchronized E take() {
		while(isEmpty()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		E item = (E) array[0];
		array = Arrays.copyOfRange(array, 1, array.length + 1);
		array[array.length - 1] = null;
		index--;
		
		this.notifyAll();
		return item;
	}
	
	private boolean isFull() {
		return index >= array.length;
	}
	
	private boolean isEmpty() {
		return index <= 0;
	}

}
