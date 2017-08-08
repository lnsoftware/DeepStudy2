package nio.asyn.multithread;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Worker implements Runnable {

	private List<EventData> queue = new LinkedList<EventData>();

	public void processData(EventData event) {
		synchronized (queue) {
			queue.add(event);
			queue.notify();
		}
	}

	public void run() {
		EventData eventData;
		while (true) {
			// Wait for data to become available
			synchronized (queue) {
				while (queue.isEmpty()) {
					try {
						System.out
								.println("No data to process, worker thread will sleep.");
						queue.wait();
					} catch (InterruptedException e) {
					}
					eventData = (EventData) queue.remove(0);
					System.out.println("Client event is:"
							+ eventData.getMessage());
				}

			}
		}
	}
	
	public static void main(String[] arg){
		List<String> list = new ArrayList<String>();
		list.add("aaaaaa");
		list.add("BBBBBBBBBBB");
		list.add("cccccccccc");
//		for(;list.size()>0;){
//			list.add("dddd");
//			System.out.println(list.remove(1));
//		}
//		for(Iterator<String> itr1 = list.iterator();itr1.hasNext();){
//			System.out.println("----------------");
//			for(Iterator<String> itr = list.iterator();itr.hasNext();){
//				System.out.println("++++++++++++++++");
//				System.out.println(itr.next());
//				itr.remove();
//				System.out.println(list.size());
//			}
//		}
//		for(int i=0;i<list.size();i++){
//			System.out.println(list.size());
//			System.out.println(list.remove(i));
//			list.add("ddddddd");
//		}
//		for(String s:list){
//			System.out.println(list.size());
//			System.out.println(list.remove(0));
//		}
	}
}
