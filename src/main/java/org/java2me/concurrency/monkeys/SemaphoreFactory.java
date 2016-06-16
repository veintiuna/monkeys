package org.java2me.concurrency.monkeys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author alejandro.contreras
 * 
 * Factory for make {@link Agent} instances.
 *
 */
public class SemaphoreFactory {

	/**
	 * Make and returns an map with semaphores.
	 * 
	 * @return {@link Map} with semaphores.
	 */
	public Map<Direction, Semaphore> makeSemaphores(){
		
		WaitingToJoinList watingToJoinEastward = new WaitingToJoinList();
		WaitingToJoinList watingToJoinWestward = new WaitingToJoinList();
		WalkingAcrossList walkingToEastward = new WalkingAcrossList();
		WalkingAcrossList walkingToWestward = new WalkingAcrossList();
		List<Thread> onExpectedFirstMonkeys = new ArrayList<Thread>();
		
		Semaphore se = new SemaphoreEastward(watingToJoinEastward, watingToJoinWestward, walkingToEastward, walkingToWestward, onExpectedFirstMonkeys);
		Semaphore so = new SemaphoreWestward(watingToJoinEastward, watingToJoinWestward, walkingToEastward, walkingToWestward, onExpectedFirstMonkeys);
		
		Map<Direction, Semaphore> semaphores= new HashMap<Direction, Semaphore>();
		semaphores.put(Direction.EASTWARD, se);
		semaphores.put(Direction.WESTWARD, so);
		
		return semaphores;
	}
	
}
