package com.zhiyin.juc.lock;
/**
 * �̰߳�ȫ�����ģ��
 * �ڷ������ڲ�
 * @author bixiao.zy
 *
 */
public class LockTestSyncCode implements Runnable{

	private int number = 0;

	public void run() {
		synchronized (this) {
			number++;
		}
	}
	public static void main(String[] args) throws Exception{
		LockTestSyncCode t = new LockTestSyncCode();
		for(int i=0;i<100;i++){
			new Thread(t).start();
		}
		System.out.println(t.number);
	}
	
}
