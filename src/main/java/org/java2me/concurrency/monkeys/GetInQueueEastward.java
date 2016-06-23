package org.java2me.concurrency.monkeys;

import org.springframework.stereotype.Component;

@Component
public class GetInQueueEastward implements MonkeyHandler {

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
