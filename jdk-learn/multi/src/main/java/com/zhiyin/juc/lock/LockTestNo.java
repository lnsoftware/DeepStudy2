package com.zhiyin.juc.lock;
/**
 * ���̰߳�ȫ�����ģ��
 * @author bixiao.zy
 *
 */
public class LockTestNo implements Runnable{

	private int number = 0;

	public void run() {
		number++;
	}
	public static void main(String[] args) throws Exception{
		LockTestNo t = new LockTestNo();
		for(int i=0;i<100;i++){
			new Thread(t).start();
		}
		System.out.println(t.number);
	}
	
}
