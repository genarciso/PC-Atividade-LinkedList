package thread.shared;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ListShared {

	private LinkedList<Integer> buffer;
	private Lock lock;
	private Condition isRemove;
	private Condition isInsert;
	private Condition isSearch;

	public ListShared() {
		this.buffer = new LinkedList<Integer>();
		this.lock = new ReentrantLock(true);
		this.isRemove = lock.newCondition();
		this.isInsert = lock.newCondition();
		this.isSearch = lock.newCondition();

	}

	public void insertItem(Integer value) {
		lock.lock();

		System.out.printf("Inserir elemento %d na lista... \n", value);
		buffer.addLast(value);
		System.out.printf("Elemento %d inserido! \n", value);

		System.out.println("Acordar as threads de remoção e inserção");
		isRemove.signal();
		isInsert.signal();

		lock.unlock();


	}

	public void removeItem(int index) {
		lock.lock();
		System.out.printf("Remover elemento da posição %d da lista \n", index);
		try {
			while (this.buffer.size() == 0) {
				System.out.println("A lista está vázia");
				System.out.println("Indo dormir...");
				isRemove.await();
			}

			Integer valueRemoved = buffer.remove(index); 
			if ( valueRemoved != null) {
				System.out.printf("Elemento %d foi removido \n", valueRemoved);
			} else {
				System.out.println("Não foi possível remover o elemento dessa posição");
			}

			System.out.println("Acordar as threads de busca, inserção e remoção");
			isSearch.signal();
			isInsert.signal();
			isRemove.signal();

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}


		// TODO Metodo de remoção com o bloqueio

	}

	public void searchItem (Integer item) {
		System.out.printf("Buscando pelo elemento %d na lista ... \n", item);
		if (buffer.contains(item)) {
			System.out.printf("Elemento %d: encontrado! \n", item);
		} else {
			System.out.printf("Elemento %d: não encontrado \n", item);
		}
	}

	public int sizeBuffer() {
		return buffer.size();
	}
}
