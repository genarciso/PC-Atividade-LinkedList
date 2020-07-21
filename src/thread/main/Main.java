package thread.main;

import thread.shared.ListShared;
import thread.thread.InsertList;
import thread.thread.RemoveList;
import thread.thread.SearchList;

public class Main {
	// Quantidade threads para serem usadas
	private static final int NUM_THREADS = 4;
	
	public static void main(String[] args) {
		ListShared list = new ListShared();
		
		// Arrays com as threads das operações
		InsertList[] insert = new InsertList[NUM_THREADS];
		RemoveList[] remove = new RemoveList[NUM_THREADS];
		SearchList[] search = new SearchList[NUM_THREADS];
		
		// Inicialização das threads
		for (int i = 0; i < NUM_THREADS; i++) {
			insert[i] = new InsertList("Insert " + (i+1), list);
			remove[i] = new RemoveList("Remove " + (i+1), list);
			search[i] = new SearchList("Search " + (i+1), list);
		}
		
		// Start das threads
		for (int i = 0; i < NUM_THREADS; i++) {
			insert[i].start();
			remove[i].start();
			search[i].start();
		}
		
		// Finalização das threads
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
