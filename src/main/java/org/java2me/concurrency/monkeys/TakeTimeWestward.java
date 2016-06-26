package org.java2me.concurrency.monkeys;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alejandro.contreras
 * 
 * Monkey handler to take time to westward.
 *
 */
public class TakeTimeWestward implements MonkeyHandler {

	/**
	 * Next monkey handler.
	 */
	private MonkeyHandler leaveQueue;
	
	public TakeTimeWestward(MonkeyHandler leaveQueue) {
		super();
		this.leaveQueue = leaveQueue;
	}

	/* (non-Javadoc)
	 * @see org.java2me.concurrency.monkeys.MonkeyHandler#handleMonkey(org.java2me.concurrency.monkeys.SemaphoreContext)
	 */
	public synchronized void handleMonkey(SemaphoreContext semaphoreContext) throws InterruptedException {
		List<Thread> monkeys = new ArrayList<Thread>();
		Thread monkey = Thread.currentThread();
		
		if(!semaphoreContext.getWatingToJoinEastward().getMonkeysWaitingToJoin().isEmpty()){
			
			Thread firstOppositeMonkey = semaphoreContext.getWatingToJoinEastward().getMonkeysWaitingToJoin().get(0);
			//Avoid deadlock
			synchronized(semaphoreContext.getOnExpectedFirstMonkeys()){
				if (!semaphoreContext.getOnExpectedFirstMonkeys().contains(monkey)) {
					monkeys.add(firstOppositeMonkey);
					semaphoreContext.getOnExpectedFirstMonkeys().add(firstOppositeMonkey);
			    }
			}
			
		}
		
		if(!semaphoreContext.getWalkingToEastward().getMonkeysWalkingAcross().isEmpty()){
			monkeys.addAll(semaphoreContext.getWalkingToEastward().getMonkeysWalkingAcross());
		}
		
		if (monkeys.isEmpty())
			System.out.println(Thread.currentThread().getName() +  " i'm a monkey going to WESTWARD and ready to go");
	 	else
	 		System.out.println(Thread.currentThread().getName() + " i'm a monkey going to WESTWARD and waiting turn for " + monkeys);
	 		
	 	
	 	for (Thread aMonkey: monkeys){
	 		aMonkey.join();
	 	}
	 	
	 	leaveQueue.handleMonkey(semaphoreContext);
	 	
	}

}
