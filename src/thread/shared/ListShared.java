package thread.shared;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import thread.lock.LinkedLock;

public class ListShared {
	
	// Buffer a ser compartilhado
	private LinkedList<Integer> buffer;
	// Lock e suas variaveis de condição
	private Lock lock;
	private LinkedLock lockLinked;
	private Condition isEmpty;

	// Inicialização das variaveis no construtor
	public ListShared() {
		this.buffer = new LinkedList<Integer>();
		
		this.lock = new ReentrantLock(true);
		this.lockLinked = new LinkedLock();

		this.isEmpty = lock.newCondition();

	}

	// Metodo para inserção dos itens
	public void insertItem(Integer value) {
		// Bloqueio da seção critica 
		lock.lock();

		try {
			lockLinked.lockInsert();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.printf("--> Iniciar inserção de elemento... \n", value);
		System.out.printf("\t Inserir elemento %d na lista... \n", value);
		// Inserção do valor na ultima posição da lista
		buffer.addLast(value);
		System.out.printf("\t Elemento %d inserido! \n", value);

		// Impressão da lista
		showBuffer();
		
		// Sinalização das threads de remoção
		isEmpty.signal();
		
		// Desbloqueio da seção critica
		lock.unlock();
		lockLinked.unlockInsert();

	}

	// Metodo para a remoção dos itens
	public void removeItem(int index) {
		
		// Bloqueio da seção critica
		lock.lock(); 
		
		try {
			lockLinked.lockRemove();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
			
		try {
			System.out.printf("--> Iniciar remoção da lista... \n", index);
			// Verificação de existencia de elementos na lista
			while (buffer.size() == 0) {
				System.out.println("\t A lista está vazia!");
				System.out.println("\t Indo dormir...");
				// Colocando a thread para a espera
				isEmpty.await();
			}
			
			// Pegar um index válido para o indice da lista
			index = index % buffer.size();
			
			System.out.printf("\t Remover elemento da posição %d da lista... \n", index);
			
			// Remoção do elemento
			Integer valueRemoved = buffer.remove(index); 
			if ( valueRemoved != null) {
				System.out.printf("\t Elemento %d foi removido \n", valueRemoved);
			} else {
				System.out.println("\t Não foi possível remover o elemento dessa posição");
			}
			
			// Impressão da lista
			showBuffer();	
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			
			// Desbloqueio da seção critica
			try {
				lockLinked.unlockRemove();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			lock.unlock();
		}
			
	}

	public void searchItem (Integer item) {
		//Bloqueio da seção critica
		try {
			lockLinked.lockSearch();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
			
		System.out.println("--> Iniciar busca de elemento...");
		System.out.printf("\t Buscando pelo elemento %d na lista... \n", item);
		// Verificação da existência do elemento na lista
		if (buffer.contains(item)) {
			System.out.printf("\t Elemento %d: encontrado! \n", item);
		} else {
			System.out.printf("\t Elemento %d: não encontrado! \n", item);
		}

		// Desbloqueio da seção critica
		lockLinked.unlockSearch();
	}
	
	// Metódo para a impressão da lista
	private void showBuffer() {
		for (int i = 0; i < buffer.size(); i++) {
			System.out.printf("[ %d ]", buffer.get(i));
		}
		System.out.println("\n");
	}
}
