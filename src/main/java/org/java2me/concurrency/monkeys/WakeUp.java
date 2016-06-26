package org.java2me.concurrency.monkeys;

/**
 * @author alejandro.contreras
 * 
 * Monkey handler to wake up.
 *
 */
public class WakeUp implements MonkeyHandler {

	/**
	 * Next monkey handler.
	 */
	MonkeyHandler getInQueue;
	
	/**
	 * Time to wake up.
	 */
	int timeToWakeUp;
	
	/**
	 * Where it goes?
	 */
	Direction direction;
	
	
	public WakeUp(MonkeyHandler getInQueue, int timeToWakeUp, Direction direction) {
		super();
		this.getInQueue = getInQueue;
		this.timeToWakeUp = timeToWakeUp;
		this.direction = direction;
	}


	/* (non-Javadoc)
	 * @see org.java2me.concurrency.monkeys.MonkeyHandler#handleMonkey(org.java2me.concurrency.monkeys.SemaphoreContext)
	 */
	public void handleMonkey(SemaphoreContext semaphoreContext) throws InterruptedException {
		
		Thread.sleep(timeToWakeUp * 1000);
	 	System.out.println(Thread.currentThread() + " i'm a monkey going to " + direction + " after " + timeToWakeUp + " seconds");

	 	getInQueue.handleMonkey(semaphoreContext);
	}

}
