package org.java2me.concurrency.monkeys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author alejandro.contreras
 * 
 * It rules the monkeys, going eastward and westward.
 *
 */
public class TrafficAgent implements Agent{

	/**
	 * Semaphores in canyon.
	 */
	private Map<Direction, Semaphore> semaphores;
	
	/**
	 * Constructor.
	 * 
	 * @param se {@link Semaphore} with the semaphore to go eastward.
	 * @param so {@link Semaphore} with the semaphore to go westward.
	 */
	public TrafficAgent(Semaphore se, Semaphore sw) {	
		super();
		
		this.semaphores = new HashMap<Direction, Semaphore>();
		semaphores.put(Direction.EASTWARD, se);
		semaphores.put(Direction.WESTWARD, sw);
		
	}
	
	
	/* (non-Javadoc)
	 * @see org.java2me.concurrency.monkeys.Agent#waitingToJoin(org.java2me.concurrency.monkeys.Direction, java.lang.Thread)
	 */
	public void waitingToJoin(Direction direction, Thread monkey) throws InterruptedException {
		semaphores.get(direction).waitingToJoin(monkey);
	}
	
	/* (non-Javadoc)
	 * @see org.java2me.concurrency.monkeys.Agent#getJoin(org.java2me.concurrency.monkeys.Direction, java.lang.Thread)
	 */
	public synchronized List<Thread> getJoin(Direction direction, Thread monkey) {
		return semaphores.get(direction).getJoin(monkey);
	}
	
	/* (non-Javadoc)
	 * @see org.java2me.concurrency.monkeys.Agent#removeFromWaitingToJoin(org.java2me.concurrency.monkeys.Direction, java.lang.Thread)
	 */
	public void removeFromWaitingToJoin(Direction direction, Thread monkey) throws InterruptedException {
		semaphores.get(direction).removeFromWaitingToJoin(monkey);
	}
	
	/* (non-Javadoc)
	 * @see org.java2me.concurrency.monkeys.Agent#walking(org.java2me.concurrency.monkeys.Direction, java.lang.Thread)
	 */
	public void walking(Direction direction, Thread monkey) {
		semaphores.get(direction).walking(monkey);
	}
	
	/* (non-Javadoc)
	 * @see org.java2me.concurrency.monkeys.Agent#walkingOut(org.java2me.concurrency.monkeys.Direction, java.lang.Thread)
	 */
	public void walkingOut(Direction direction, Thread monkey) {
		semaphores.get(direction).walkingOut(monkey);
	}	
	
}
