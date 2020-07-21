package thread.thread;

import thread.shared.ListShared;

public class RemoveList extends Thread {
	private ListShared list;
	
	public RemoveList(String name, ListShared list) {
		super(name);
		this.list = list;
		
	}
	
	@Override
	public void run() {
		int value = (int) (Math.random() * 100);
		list.removeItem(value);
	}
}
