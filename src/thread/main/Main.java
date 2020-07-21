package thread.main;

import thread.shared.ListShared;
import thread.thread.InsertList;
import thread.thread.RemoveList;
import thread.thread.SearchList;

public class Main {
	private static final int NUM_THREADS = 4;
	
	public static void main(String[] args) {
		ListShared list = new ListShared();
		
		InsertList[] insert = new InsertList[NUM_THREADS];
		RemoveList[] remove = new RemoveList[NUM_THREADS];
		SearchList[] search = new SearchList[NUM_THREADS];
		
		for (int i = 0; i < NUM_THREADS; i++) {
			insert[i] = new InsertList("Insert " + (i+1), list);
			remove[i] = new RemoveList("Remove " + (i+1), list);
			search[i] = new SearchList("Search " + (i+1), list);
		}
		
		for (int i = 0; i < NUM_THREADS; i++) {
			insert[i].start();
			remove[i].start();
			search[i].start();
		}
		
		try {
			for (int i = 0; i < NUM_THREADS; i++) {
				insert[i].join();
				remove[i].join();
				search[i].join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
 
	}

}
