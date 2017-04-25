package model.tiles;

import java.util.NoSuchElementException;
import java.util.LinkedList;

public class Stack<E> {
	
	private LinkedList<E> liste;
	private int taille;
	
	public Stack(){
		this.liste = new LinkedList<E>();
		this.taille = 0;
	}

	public boolean isEmpty() {	
		return this.liste.equals(new LinkedList<E>());
	}

	public void push(E elem) {
		this.liste.add(elem);
		this.taille ++;
	}

	public E pop() throws NoSuchElementException {
		if (this.isEmpty()){
			throw new NoSuchElementException("Stack is empty");
		}
		else{
			this.taille --;
			return this.liste.remove(this.taille);
		}
	}
	
	public int size(){
		return this.taille;
	}
	
	public E peek(){
		return this.liste.get(taille);
	}

}
