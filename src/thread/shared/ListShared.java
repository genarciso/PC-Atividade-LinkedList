package thread.shared;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ListShared {
	
	private int capacity;
	private LinkedList<Integer> buffer;
	private Lock lock;
	private Condition isRemove;
	private Condition isInsert;
	private Condition isSearch;
	
	public ListShared(int capacity) {
		this.capacity = capacity;
		this.buffer = new LinkedList<Integer>();
		this.lock = new ReentrantLock(true);
		this.isRemove = lock.newCondition();
		this.isInsert = lock.newCondition();
		this.isSearch = lock.newCondition();
		
	}
	
	public void insertItem() {
		// TODO Metodo de inserção com o bloqueio

	}
	
	public void removeItem() {
		// TODO Metodo de remoção com o bloqueio

	}
	
	public void searchItem () {
		// TODO Metodo de busca com o bloqueio
	}
}
