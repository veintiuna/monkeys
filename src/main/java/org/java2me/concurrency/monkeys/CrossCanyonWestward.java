package org.java2me.concurrency.monkeys;

public class CrossCanyonWestward implements MonkeyHandler {

	MonkeyHandler leaveCanyon;
	
	public CrossCanyonWestward(MonkeyHandler leaveCanyon) {
		super();
		this.leaveCanyon = leaveCanyon;
	}

	public void handleMonkey(SemaphoreContext semaphoreContext) throws InterruptedException {
		semaphoreContext.getWalkingToWestward().addToWalkingAcrossMonkeys(Thread.currentThread());
		Thread.sleep(4000);
		
		leaveCanyon.handleMonkey(semaphoreContext);
	}

}
