package org.java2me.concurrency.monkeys;

import org.springframework.stereotype.Component;

/**
 * @author alejandro.contreras
 * 
 * Monkey handler to get in queue eastward.
 *
 */
@Component
public class GetInQueueEastward implements MonkeyHandler {

	/**
	 * Next monkey handler.
	 */
	MonkeyHandler takeTime;
	
	
	public GetInQueueEastward(MonkeyHandler takeTime) {
		super();
		this.takeTime = takeTime;
	}

	public void handleMonkey(SemaphoreContext semaphoreContext) throws InterruptedException {
		semaphoreContext.getWatingToJoinEastward().addToWaitingMonkeys(Thread.currentThread());
		takeTime.handleMonkey(semaphoreContext);
	}

}
