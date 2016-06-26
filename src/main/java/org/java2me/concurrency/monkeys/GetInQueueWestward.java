package org.java2me.concurrency.monkeys;

import org.springframework.stereotype.Component;

/**
 * @author alejandro.contreras
 * 
 * Monkey handler to get in queue westward.
 *
 */
@Component
public class GetInQueueWestward implements MonkeyHandler {

	/**
	 * Next monkey handler.
	 */
	MonkeyHandler takeTime;
	
	
	public GetInQueueWestward(MonkeyHandler takeTime) {
		super();
		this.takeTime = takeTime;
	}

	public void handleMonkey(SemaphoreContext semaphoreContext) throws InterruptedException {
		semaphoreContext.getWatingToJoinWestward().addToWaitingMonkeys(Thread.currentThread());
		takeTime.handleMonkey(semaphoreContext);
	}

}
