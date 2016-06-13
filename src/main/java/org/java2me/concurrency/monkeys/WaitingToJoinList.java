package org.java2me.concurrency.monkeys;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alejandro.contreras
 * 
 * Waiting room.
 *
 */
public class WaitingToJoinList {
	
	/**
	 * List with monkeys in the waiting room.
	 */
	private List<Thread> monkeysWaitingToJoin = new ArrayList<Thread>();
	
	/**
	 * Add a monkey to the waiting list.
	 * 
	 * @param monkey {@link Thread} with the added monkey.
	 * @throws InterruptedException if the wait was interrupted by someone.
	 */
	public synchronized void addToWaitingMonkeys(Thread monkey) throws InterruptedException {
		monkeysWaitingToJoin.add(monkey);
		
		boolean isFirst = (monkeysWaitingToJoin.indexOf(monkey) == 0);
		
		while(!isFirst){
			wait();
			isFirst = (monkeysWaitingToJoin.indexOf(monkey) == 0);
		}	
	}
	
	/**
	 * Remove a monkey from the waiting list, and take a break before leaving.
	 * 
	 * @param monkey {@link Thread} with the monkey leaving the waiting room.
	 * @throws InterruptedException if the monkey was interrupted by someone.
	 */
	public synchronized void removeFromWaitingMonkeys(Thread monkey) throws InterruptedException {
			monkeysWaitingToJoin.remove(monkey);
			Thread.sleep(1000);
			notifyAll();
	}

	/**
	 * Returns monkeys in the waiting room.
	 * 
	 * @return {@link List} with monkeys in the waiting room
	 */
	public synchronized List<Thread> getMonkeysWaitingToJoin() {
		return monkeysWaitingToJoin;
	}
	
	
	
}
