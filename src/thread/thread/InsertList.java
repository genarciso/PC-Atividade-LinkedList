package thread.thread;

import thread.shared.ListShared;

public class InsertList extends Thread {
	private ListShared list;
	
	public InsertList(String name, ListShared list) {
		super(name);
		this.list = list;
		
	}
	
	@Override
	public void run() {
		this.list.insertItem();
	}
}
