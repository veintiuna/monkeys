package org.java2me.concurrency.monkeys;

import org.springframework.stereotype.Component;

@Component
public class GetInQueueWestward implements MonkeyHandler {

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
