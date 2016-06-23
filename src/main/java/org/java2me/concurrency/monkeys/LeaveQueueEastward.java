package org.java2me.concurrency.monkeys;

public class LeaveQueueEastward implements MonkeyHandler {

	MonkeyHandler crossCanyon;
	
	public LeaveQueueEastward(MonkeyHandler crossCanyon) {
		super();
		this.crossCanyon = crossCanyon;
	}

	public void handleMonkey(SemaphoreContext semaphoreContext) throws InterruptedException {
		Thread monkey = Thread.currentThread();
		semaphoreContext.getWatingToJoinEastward().removeFromWaitingMonkeys(monkey);
		semaphoreContext.getOnExpectedFirstMonkeys().remove(monkey);

		crossCanyon.handleMonkey(semaphoreContext);
	}


}
