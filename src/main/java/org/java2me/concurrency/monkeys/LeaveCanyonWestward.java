package org.java2me.concurrency.monkeys;

/**
 * @author alejandro.contreras
 * 
 * Monkey handler to leave the canyon westward.
 *
 */
public class LeaveCanyonWestward implements MonkeyHandler {

	public void handleMonkey(SemaphoreContext semaphoreContext) throws InterruptedException {
		semaphoreContext.getWalkingToWestward().removeFromWalkingAcrossMonkeys(Thread.currentThread());
		System.out.println(Thread.currentThread().getName() + " Game Over, WESTWARD at " + System.currentTimeMillis());

	}

}
