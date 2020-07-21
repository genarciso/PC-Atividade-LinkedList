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
		Integer value = 1;
		if (this.list.sizeBuffer() > 0) {
		 value = (int) (Math.random() * 100) % this.list.sizeBuffer();
		}
		list.removeItem(value);
	}
}
