package org.java2me.concurrency.monkeys;

import java.util.ArrayList;
import java.util.List;

public class TakeTimeEastward implements MonkeyHandler {

	private MonkeyHandler leaveQueue;
	
	
	public TakeTimeEastward(MonkeyHandler leaveQueue) {
		super();
		this.leaveQueue = leaveQueue;
	}

	public synchronized void handleMonkey(SemaphoreContext semaphoreContext) throws InterruptedException {
		List<Thread> monkeys = new ArrayList<Thread>();
		Thread monkey = Thread.currentThread();
		
		if(!semaphoreContext.getWatingToJoinWestward().getMonkeysWaitingToJoin().isEmpty()){
			
			Thread firstOppositeMonkey = semaphoreContext.getWatingToJoinWestward().getMonkeysWaitingToJoin().get(0);
			//Avoid deadlock
			synchronized(semaphoreContext.getOnExpectedFirstMonkeys()){
				if (!semaphoreContext.getOnExpectedFirstMonkeys().contains(monkey)){
					monkeys.add(firstOppositeMonkey);
					semaphoreContext.getOnExpectedFirstMonkeys().add(firstOppositeMonkey);
				}
			}
			
		}
		
		if(!semaphoreContext.getWalkingToWestward().getMonkeysWalkingAcross().isEmpty()){
			monkeys.addAll(semaphoreContext.getWalkingToWestward().getMonkeysWalkingAcross());
		}
		
		if (monkeys.isEmpty())
			System.out.println(Thread.currentThread().getName() +  " i'm a monkey going to EASTWARD and ready to go");
	 	else
	 		System.out.println(Thread.currentThread().getName() +  " i'm a monkey going to EASTWARD and waiting turn for " + monkeys);
	 		
	 	
	 	for (Thread aMonkey: monkeys){
	 		aMonkey.join();
	 	}
	 	
	 	leaveQueue.handleMonkey(semaphoreContext);

	}


}
