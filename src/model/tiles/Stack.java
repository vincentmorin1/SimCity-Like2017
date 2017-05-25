package model.tiles;

import java.util.NoSuchElementException;
import java.io.Serializable;
import java.util.LinkedList;

public class Stack<E> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LinkedList<E> liste;
	
	private int taille;
	
	/**
	 * Stack constructor
	 */
	public Stack(){
		this.liste = new LinkedList<E>();
		this.taille = 0;
	}

	/**
	 * 
	 * @return True if the list is empty, false in the other case
	 */
	public boolean isEmpty() {	
		return this.liste.equals(new LinkedList<E>());
	}

	/**
	 * 
	 * @param elem
	 */
	public void push(E elem) {
		this.liste.add(elem);
		this.taille ++;
	}

	/**
	 * 
	 * @return The first element from the list where this element has been removed
	 * @throws NoSuchElementException
	 */
	public E pop() throws NoSuchElementException {
		if (this.isEmpty()){
			throw new NoSuchElementException("Stack is empty");
		}
		else{
			this.taille --;
			return this.liste.remove(this.taille);
		}
	}
	
	/**
	 * 
	 * @return Size of the list
	 */
	public int size(){
		return this.taille;
	}
	
	public E peek(){
		return this.liste.get(taille);
	}

}
