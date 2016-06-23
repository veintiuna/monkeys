package org.java2me.concurrency.monkeys;


/**
 * @author alejandro.contreras
 * 
 * Defines a step in the responsibility chain  to cross the canyon.
 *
 */
public interface MonkeyHandler {

	/**
	 * Step to cross the canyon.
	 * 
	 * @param semaphoreContext {@link SemaphoreContext} with the semaphores to traffic control.
	 * @throws InterruptedException when someone breaks.
	 */
	public void handleMonkey(SemaphoreContext semaphoreContext) throws InterruptedException;
	
}
