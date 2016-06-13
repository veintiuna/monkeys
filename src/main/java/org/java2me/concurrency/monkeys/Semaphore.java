package org.java2me.concurrency.monkeys;

import java.util.List;

/**
 * @author alejandro.contreras
 *
 * It rules traffic to eastward and westward.
 */
public interface Semaphore {
	
	/**
	 * Go to the waiting room, before crossing the canyon.
	 * The waiting room has monkeys that go to the same direction, queuing on a first come.
	 *
	 * @param monkey {@link Thread} with the monkey asking for.
	 * @throws InterruptedException if the wait was interrupted by someone.
	 */
	public void waitingToJoin(Thread monkey) throws InterruptedException;
	
	/**
	 * Get the list of monkeys in front, which take precedence over you to cross.
	 * 
	 * @param monkey {@link Thread} with the monkey asking for.
	 * @return {@link List} with the monkeys opposite, which take precedence to cross.
	 */
	public List<Thread> getJoin(Thread monkey);
	
	/**
	 * Leave the waiting room and wait one second to get on the rope.
	 * 
	 * @param monkey {@link Thread} with the monkey asking for.
	 * @throws InterruptedException if the wait was interrupted by someone.
	 */
	public void removeFromWaitingToJoin(Thread monkey) throws InterruptedException;
	
	/**
	 * Cross the canyon.
	 * 
	 * @param monkey {@link Thread} with the monkey asking for.
	 */
	public void walking(Thread monkey);
	
	/**
	 * Walk out the canyon.
	 * 
	 * @param monkey {@link Thread} with the monkey asking for.
	 */
	public void walkingOut(Thread monkey);
	
}
