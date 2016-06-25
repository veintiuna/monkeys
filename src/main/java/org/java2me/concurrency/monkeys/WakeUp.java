package org.java2me.concurrency.monkeys;

public class WakeUp implements MonkeyHandler {

	MonkeyHandler getInQueue;
	int timeToWakeUp;
	Direction direction;
	
	
	public WakeUp(MonkeyHandler getInQueue, int timeToWakeUp, Direction direction) {
		super();
		this.getInQueue = getInQueue;
		this.timeToWakeUp = timeToWakeUp;
		this.direction = direction;
	}


	public void handleMonkey(SemaphoreContext semaphoreContext) throws InterruptedException {
		
		Thread.sleep(timeToWakeUp * 1000);
	 	System.out.println(Thread.currentThread() + " i'm a monkey going to " + direction + " after " + timeToWakeUp + " seconds");

	 	getInQueue.handleMonkey(semaphoreContext);
	}

}
