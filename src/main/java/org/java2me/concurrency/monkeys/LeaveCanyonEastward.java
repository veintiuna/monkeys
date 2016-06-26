package org.java2me.concurrency.monkeys;

/**
 * @author alejandro.contreras
 * 
 * Monkey handler to leave the canyon eastward.
 *
 */
public class LeaveCanyonEastward implements MonkeyHandler {

	public void handleMonkey(SemaphoreContext semaphoreContext) throws InterruptedException {
		semaphoreContext.getWalkingToEastward().removeFromWalkingAcrossMonkeys(Thread.currentThread());
		System.out.println(Thread.currentThread().getName() + " Game Over, EASTWARD at " + System.currentTimeMillis());

	}


}
