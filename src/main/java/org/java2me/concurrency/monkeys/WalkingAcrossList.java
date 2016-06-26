package org.java2me.concurrency.monkeys;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alejandro.contreras
 * 
 * Monkeys walking on the rope between canyon banks. 
 *
 */
public class WalkingAcrossList {
	
	/**
	 * List with monkeys walking across canyon.
	 */
	private List<Thread> monkeysWalkingAcross = new ArrayList<Thread>();
	
	
	/**
	 * Add a monkey to the rope.
	 * 
	 * @param monkey {@link Thread} with the monkey that get on the rope.
	 */
	public synchronized void addToWalkingAcrossMonkeys(Thread monkey) {
		monkeysWalkingAcross.add(monkey);
	}
	
	/**
	 * Removes a monkey from the rope.
	 * 
	 * @param monkey {@link Thread} with the monkey leaving the rope.
	 */
	public synchronized void removeFromWalkingAcrossMonkeys(Thread monkey) {
		monkeysWalkingAcross.remove(monkey);	
	}

	/**
	 * Returns monkeys on the rope, across the canyon.
	 * 
	 * @return {@link List} with monkeys on the rope.
	 */
	public synchronized List<Thread> getMonkeysWalkingAcross() {
		return monkeysWalkingAcross;
	}

}
