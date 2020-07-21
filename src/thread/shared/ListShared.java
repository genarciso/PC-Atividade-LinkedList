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

		System.out.printf("--> Iniciar inserção de elemento... \n", value);
		System.out.printf("\t Inserir elemento %d na lista... \n", value);
		buffer.addLast(value);
		System.out.printf("\t Elemento %d inserido! \n", value);

		System.out.println("\t Acordar as threads de remoção e inserção");
		isRemove.signal();
		isInsert.signal();
		System.out.println("------------------------------------------");
		lock.unlock();


	}

	public void removeItem(int index) {
		lock.lock();
		try {
			System.out.printf("--> Iniciar remoção da lista... \n", index);
			while (buffer.size() == 0) {
				System.out.println("\t A lista está vázia");
				System.out.println("\t Indo dormir...");
				isRemove.await();
			}

			index = index % buffer.size();
			System.out.printf("\t Remover elemento da posição %d da lista... \n", index);

			Integer valueRemoved = buffer.remove(index); 
			if ( valueRemoved != null) {
				System.out.printf("\t Elemento %d foi removido \n", valueRemoved);
			} else {
				System.out.println("\t Não foi possível remover o elemento dessa posição");
			}

			System.out.println("\t Acordar as threads de busca, inserção e remoção");
			isSearch.signal();
			isInsert.signal();
			System.out.println("---------------------------------------------");

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}

	public void searchItem (Integer item) {
		System.out.println("--> Iniciar busca de elemento...");
		System.out.printf("\t Buscando pelo elemento %d na lista... \n", item);
		if (buffer.contains(item)) {
			System.out.printf("\t Elemento %d: encontrado! \n", item);
		} else {
			System.out.printf("\t Elemento %d: não encontrado \n", item);
		}
		System.out.println("--------------------------------------------");
	}

	public int sizeBuffer() {
		return buffer.size();
	}
}
