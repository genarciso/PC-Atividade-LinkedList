package thread.thread;

import thread.shared.ListShared;

public class InsertList extends Thread {
	private ListShared list;
	
	public InsertList(String name, ListShared list) {
		super(name);
		this.list = list;
		
	}
	
	// Metodo que escolhe um numero qualquer de 0 a 100 e insere na lista
	@Override
	public void run() {
		Integer value = (int) (Math.random() * 100);
		this.list.insertItem(value);
	}
}
