package org.java2me.concurrency.monkeys;

import java.util.List;

/**
 * @author alejandro.contreras
 *
 * It rules traffic to eastward and westward.
 */
public interface Semaphore {
	
	/**
	 * Get in line, before crossing the canyon.
	 * The queue has monkeys that go to the same direction, queuing on a first come.
	 *
	 * @throws InterruptedException if the wait was interrupted by someone.
	 */
	public Semaphore getInQueue() throws InterruptedException;
	
	/**
	 * Get the list of monkeys in front, which take precedence over you to cross.
	 * 
	 * @return {@link List} with the monkeys opposite, which take precedence to cross.
	 */
	public Semaphore takeTime() throws InterruptedException;
	
	/**
	 * Leave the waiting room and wait one second to get on the rope.
	 * 
	 * @throws InterruptedException if the wait was interrupted by someone.
	 */
	public Semaphore leaveQueue() throws InterruptedException;
	
	/**
	 * Cross the canyon.
	 * 
	 */
	public Semaphore crossCanyon() throws InterruptedException;
	
	/**
	 * Walk out the canyon.
	 * 
	 */
	public void leaveCanyon();
	
}
