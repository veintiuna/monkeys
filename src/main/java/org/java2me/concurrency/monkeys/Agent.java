package org.java2me.concurrency.monkeys;

import java.util.List;

/**
 * @author alejandro.contreras
 * 
 * Monkey communication with traffic agent, by reason to cross the canyon.
 *
 */
public interface Agent {
	
	/**
	 * Go to the waiting room, before crossing the canyon.
	 * The waiting room has monkeys that go to the same direction, queuing on a first come.
	 * 
	 * @param direction {@link Direction} with direction to cross.
	 * @param monkey {@link Thread} with the monkey asking for.
	 * @throws InterruptedException if the wait was interrupted by someone.
	 */
	public void waitingToJoin(Direction direction, Thread monkey) throws InterruptedException;
	
	/**
	 * Get the list of monkeys in front, which take precedence over you to cross.
	 * 
	 * @param direction {@link Direction} with direction to cross.
	 * @param monkey {@link Thread} with the monkey asking for.
	 * @return {@link List} with the monkeys opposite, which take precedence to cross.
	 */
	public List<Thread> getJoin(Direction direction, Thread monkey);
	
	/**
	 * Leave the waiting room and wait one second to get on the rope.
	 * 
	 * @param direction {@link Direction} with direction to cross.
	 * @param monkey {@link Thread} with the monkey asking for.
	 * @throws InterruptedException if the wait was interrupted by someone.
	 */
	public void removeFromWaitingToJoin(Direction direction, Thread monkey) throws InterruptedException;
	
	/**
	 * Cross the canyon.
	 * 
	 * @param direction {@link Direction} with direction to cross.
	 * @param monkey {@link Thread} with the monkey asking for.
	 */
	public void walking(Direction direction, Thread monkey);
	
	
	/**
	 * Walk out the canyon.
	 * 
	 * @param direction {@link Direction} with direction to cross.
	 * @param monkey {@link Thread} with the monkey asking for.
	 */
	public void walkingOut(Direction direction, Thread monkey);
	
}
