package org.java2me.concurrency.monkeys;

/**
 * @author alejandro.contreras
 * 
 * Monkey handler to cross canyon westward.
 *
 */
public class CrossCanyonWestward implements MonkeyHandler {

	/**
	 * Next monkey handler.
	 */
	MonkeyHandler leaveCanyon;
	
	public CrossCanyonWestward(MonkeyHandler leaveCanyon) {
		super();
		this.leaveCanyon = leaveCanyon;
	}

	/* (non-Javadoc)
	 * @see org.java2me.concurrency.monkeys.MonkeyHandler#handleMonkey(org.java2me.concurrency.monkeys.SemaphoreContext)
	 */
	public void handleMonkey(SemaphoreContext semaphoreContext) throws InterruptedException {
		semaphoreContext.getWalkingToWestward().addToWalkingAcrossMonkeys(Thread.currentThread());
		Thread.sleep(4000);
		
		leaveCanyon.handleMonkey(semaphoreContext);
	}

}
