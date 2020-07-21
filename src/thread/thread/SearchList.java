package thread.thread;

import thread.shared.ListShared;

public class SearchList extends Thread {
	private ListShared list;
	
	public SearchList(String name, ListShared list) {
		super(name);
		this.list = list;
		
	}
	
	@Override
	public void run() {
		Integer value = (int) (Math.random() * 100);
		this.list.searchItem(value);
	}
}
