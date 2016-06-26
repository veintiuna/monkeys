package org.java2me.concurrency.monkeys;

/**
 * @author alejandro.contreras
 * 
 * Monkey handler to leave queue westward.
 *
 */
public class LeaveQueueWestward implements MonkeyHandler {

	/**
	 * Next monkey handler.
	 */
	MonkeyHandler crossCanyon;
	
	public LeaveQueueWestward(MonkeyHandler crossCanyon) {
		super();
		this.crossCanyon = crossCanyon;
	}

	public void handleMonkey(SemaphoreContext semaphoreContext) throws InterruptedException {
		Thread monkey = Thread.currentThread();
		semaphoreContext.getWatingToJoinWestward().removeFromWaitingMonkeys(monkey);
		semaphoreContext.getOnExpectedFirstMonkeys().remove(monkey);

		crossCanyon.handleMonkey(semaphoreContext);
	}

}
