package org.java2me.concurrency.monkeys;

public class CrossCanyonEastward implements MonkeyHandler {

	MonkeyHandler leaveCanyon;
	
	public CrossCanyonEastward(MonkeyHandler leaveCanyon) {
		super();
		this.leaveCanyon = leaveCanyon;
	}

	
	public void handleMonkey(SemaphoreContext semaphoreContext) throws InterruptedException {
		semaphoreContext.getWalkingToEastward().addToWalkingAcrossMonkeys(Thread.currentThread());
		Thread.sleep(4000);
		
		leaveCanyon.handleMonkey(semaphoreContext);
	}

	
}
