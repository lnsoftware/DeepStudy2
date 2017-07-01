package practice.queue;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionQueue<E> implements SimpleQueueDemo<E> {

	private Object[] array;
    private int index = 0;
	
	private static ReentrantLock lock = new ReentrantLock();
	
	private static Condition notEmpty = lock.newCondition();
	private static Condition notFull = lock.newCondition();
	
	public ConditionQueue(int size) {
		this.array = new Object[size];
	}
	
	@Override
	public void put(E item) {
		lock.lock();
		try {
			while(isFull()) {
				notFull.await();
			}
			
			array[index++] = item;
			
			notEmpty.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public E take() {
		lock.lock();
		try {
			while(isEmpty()) {
				notEmpty.await();
			}
			
			E item = (E) array[0];
			array = Arrays.copyOfRange(array, 1, array.length + 1);
			array[array.length - 1] = null;
			index--;
			
			notFull.signal();
			return item;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return null;
	}
	
	private boolean isFull() {
		return index >= array.length;
	}
	
	private boolean isEmpty() {
		return index <= 0;
	}

}
