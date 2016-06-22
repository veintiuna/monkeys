package org.java2me.concurrency.monkeys;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author alejandro.contreras
 * 
 * Semaphore that rules traffic to westward.
 *
 */
@Component("semaphoreWestward")
public class SemaphoreWestward implements Semaphore {
	
	/**
	 * List with monkeys in the waiting room, to go to eastward.
	 */
	private WaitingToJoinList watingToJoinEastward;
	
	/**
	 * List with monkeys in the waiting room, to go to westward.
	 */
	private WaitingToJoinList watingToJoinWestward;
	
	/**
	 * List with monkeys walking eastward.
	 */
	private WalkingAcrossList walkingToEastward;
	
	/**
	 * List with monkeys walking westward.
	 */
	private WalkingAcrossList walkingToWestward;
	
	/**
	 * List with monkeys that are already being expected.
	 * It avoids dead lock with expected monkeys each other.
	 * 
	 * Note that list don't need to be synchronized.
	 */
	private ExpectedMonkeys onExpectedFirstMonkeys;
	
	/**
	 * Constructor.
	 * 
	 * @param watingToJoinEastward {@link WaitingToJoinList} with monkeys in the waiting room, to go to eastward.
	 * @param watingToJoinWestward {@link WaitingToJoinList} with monkeys in the waiting room, to go to westward. 
	 * @param walkingToEastward {@link WalkingAcrossList} with monkeys walking eastward.
	 * @param walkingToWestward {@link WalkingAcrossList} monkeys walking westward.
	 * @param onExpectedFirstMonkeys {@link List} with monkeys that are already being expected.
	 */
	@Autowired
	public SemaphoreWestward(
			@Qualifier("waitingEastward") WaitingToJoinList watingToJoinEastward,
			@Qualifier("waitingWestward") WaitingToJoinList watingToJoinWestward,
			@Qualifier("walkingEastward") WalkingAcrossList walkingToEastward,
			@Qualifier("walkingWestward") WalkingAcrossList walkingToWestward,
			ExpectedMonkeys onExpectedFirstMonkeys) {
		super();
		this.watingToJoinEastward = watingToJoinEastward;
		this.watingToJoinWestward = watingToJoinWestward;
		this.walkingToEastward = walkingToEastward;
		this.walkingToWestward = walkingToWestward;
		this.onExpectedFirstMonkeys = onExpectedFirstMonkeys;
	}
	
	
	/* (non-Javadoc)
	 * @see org.java2me.concurrency.monkeys.Semaphore#getInQueue()
	 */
	public Semaphore getInQueue() throws InterruptedException {
		watingToJoinWestward.addToWaitingMonkeys(Thread.currentThread());
		return this;
	}

	
	/* (non-Javadoc)
	 * @see org.java2me.concurrency.monkeys.Semaphore#takeTime()
	 */
	public synchronized Semaphore takeTime() throws InterruptedException {
		List<Thread> monkeys = new ArrayList<Thread>();
		Thread monkey = Thread.currentThread();
		
		if(!watingToJoinEastward.getMonkeysWaitingToJoin().isEmpty()){
			
			Thread firstOppositeMonkey = watingToJoinEastward.getMonkeysWaitingToJoin().get(0);
			//Avoid deadlock
			synchronized(onExpectedFirstMonkeys){
				if (!onExpectedFirstMonkeys.contains(monkey)) {
					monkeys.add(firstOppositeMonkey);
					onExpectedFirstMonkeys.add(firstOppositeMonkey);
			    }
			}
			
		}
		
		if(!walkingToEastward.getMonkeysWalkingAcross().isEmpty()){
			monkeys.addAll(walkingToEastward.getMonkeysWalkingAcross());
		}
		
		if (monkeys.isEmpty())
			System.out.println(Thread.currentThread().getName() +  " i'm a monkey going to WESTWARD and ready to go");
	 	else
	 		System.out.println(Thread.currentThread().getName() + " i'm a monkey going to WESTWARD and waiting turn for " + monkeys);
	 		
	 	
	 	for (Thread aMonkey: monkeys){
	 		aMonkey.join();
	 	}
	 	
		
		return this;
	}

	
	/* (non-Javadoc)
	 * @see org.java2me.concurrency.monkeys.Semaphore#leaveQueue()
	 */
	public Semaphore leaveQueue() throws InterruptedException {
		Thread monkey = Thread.currentThread();
		watingToJoinWestward.removeFromWaitingMonkeys(monkey);
		onExpectedFirstMonkeys.remove(monkey);
		return this;
	}

	
	/* (non-Javadoc)
	 * @see org.java2me.concurrency.monkeys.Semaphore#crossCanyon()
	 */
	public Semaphore crossCanyon() throws InterruptedException{
		walkingToWestward.addToWalkingAcrossMonkeys(Thread.currentThread());
		Thread.sleep(4000);
		return this;
	}

	
	/* (non-Javadoc)
	 * @see org.java2me.concurrency.monkeys.Semaphore#leaveCanyon()
	 */
	public void leaveCanyon() {
		walkingToWestward.removeFromWalkingAcrossMonkeys(Thread.currentThread());
		System.out.println(Thread.currentThread().getName() + " Game Over, WESTWARD at " + System.currentTimeMillis());
	}

}
