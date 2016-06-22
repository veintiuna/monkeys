package org.java2me.concurrency.monkeys;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author alejandro.contreras
 * 
 * Semaphore that rules traffic to eastward.
 *
 */
@Component("semaphoreEastward")
public class SemaphoreEastward implements Semaphore {
	
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
	public SemaphoreEastward(
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
		watingToJoinEastward.addToWaitingMonkeys(Thread.currentThread());
		return this;
	}

	
	/* (non-Javadoc)
	 * @see org.java2me.concurrency.monkeys.Semaphore#takeTime()
	 */
	public synchronized Semaphore takeTime() throws InterruptedException {
		List<Thread> monkeys = new ArrayList<Thread>();
		Thread monkey = Thread.currentThread();
		
		if(!watingToJoinWestward.getMonkeysWaitingToJoin().isEmpty()){
			
			Thread firstOppositeMonkey = watingToJoinWestward.getMonkeysWaitingToJoin().get(0);
			//Avoid deadlock
			synchronized(onExpectedFirstMonkeys){
				if (!onExpectedFirstMonkeys.contains(monkey)){
					monkeys.add(firstOppositeMonkey);
					onExpectedFirstMonkeys.add(firstOppositeMonkey);
				}
			}
			
		}
		
		if(!walkingToWestward.getMonkeysWalkingAcross().isEmpty()){
			monkeys.addAll(walkingToWestward.getMonkeysWalkingAcross());
		}
		
		if (monkeys.isEmpty())
			System.out.println(Thread.currentThread().getName() +  " i'm a monkey going to EASTWARD and ready to go");
	 	else
	 		System.out.println(Thread.currentThread().getName() +  " i'm a monkey going to EASTWARD and waiting turn for " + monkeys);
	 		
	 	
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
		watingToJoinEastward.removeFromWaitingMonkeys(monkey);
		onExpectedFirstMonkeys.remove(monkey);
		return this;
	}

	
	
	/* (non-Javadoc)
	 * @see org.java2me.concurrency.monkeys.Semaphore#crossCanyon()
	 */
	public Semaphore crossCanyon() throws InterruptedException {
		walkingToEastward.addToWalkingAcrossMonkeys(Thread.currentThread());
		Thread.sleep(4000);
		return this;
	}

	
	
	/* (non-Javadoc)
	 * @see org.java2me.concurrency.monkeys.Semaphore#leaveCanyon()
	 */
	public void leaveCanyon() {
		walkingToEastward.removeFromWalkingAcrossMonkeys(Thread.currentThread());
		System.out.println(Thread.currentThread().getName() + " Game Over, WESTWARD at " + System.currentTimeMillis());
	}

}
