package thread.thread;

import thread.shared.ListShared;

public class RemoveList extends Thread {
	private ListShared list;
	
	public RemoveList(String name, ListShared list) {
		super(name);
		this.list = list;
		
	}
	
	//	Metodo que escolhe um indece qualquer de 0 a 10 que será removido da lista
	@Override
	public void run() {
		int value = (int) (Math.random() * 10);
		list.removeItem(value);
	}
}
