package com.zhiyin.juc.lock;
/**
 * �̰߳�ȫ�����ģ��
 * �ڷ����ϼ���
 * @author bixiao.zy
 *
 */
public class LockTestSyncClass implements Runnable{

	private int  number = 0;

	public void run() {
		synchronized (LockTestSyncClass.class) {
			number++;
		}
	}
	public static void main(String[] args) throws Exception{
		LockTestSyncClass t = new LockTestSyncClass();
		for(int i=0;i<100;i++){
			new Thread(t).start();
		}
		Thread.sleep(3000);
		System.out.println(t.number);
	}
	
}
