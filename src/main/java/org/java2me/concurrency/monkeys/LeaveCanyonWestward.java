package org.java2me.concurrency.monkeys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author alejandro.contreras
 * 
 * Monkey handler to leave the canyon westward.
 *
 */
public class LeaveCanyonWestward implements MonkeyHandler {

	private Logger logger = LoggerFactory.getLogger(LeaveCanyonWestward.class);
	
	public void handleMonkey(SemaphoreContext semaphoreContext) throws InterruptedException {
		semaphoreContext.getWalkingToWestward().removeFromWalkingAcrossMonkeys(Thread.currentThread());
		logger.info(String.format("%-10s", Thread.currentThread().getName()) + " game over with WESTWARD reached at " + System.currentTimeMillis() + ".");

	}

}
