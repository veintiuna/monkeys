package org.java2me.concurrency.monkeys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author alejandro.contreras
 * 
 * Monkey handler to leave the canyon eastward.
 *
 */
public class LeaveCanyonEastward implements MonkeyHandler {

	private Logger logger = LoggerFactory.getLogger(LeaveCanyonEastward.class);
	
	public void handleMonkey(SemaphoreContext semaphoreContext) throws InterruptedException {
		semaphoreContext.getWalkingToEastward().removeFromWalkingAcrossMonkeys(Thread.currentThread());
		logger.info(String.format("%-10s", Thread.currentThread().getName()) + " game over with EASTWARD reached at " + System.currentTimeMillis() + ".");

	}

}
