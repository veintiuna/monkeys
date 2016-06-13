package org.java2me.concurrency.monkeys;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alejandro.contreras
 * 
 * Factory for make {@link Agent} instances.
 *
 */
public class TrafficAgentFactory {

	/**
	 * Make and returns an agent.
	 * 
	 * @return {@link Agent} with the traffic agent,
	 */
	public Agent makeTrafficAgent(){
		
		WaitingToJoinList watingToJoinEastward = new WaitingToJoinList();
		WaitingToJoinList watingToJoinWestward = new WaitingToJoinList();
		WalkingAcrossList walkingToEastward = new WalkingAcrossList();
		WalkingAcrossList walkingToWestward = new WalkingAcrossList();
		List<Thread> onExpectedFirstMonkeys = new ArrayList<Thread>();
		
		Semaphore se = new SemaphoreEastward(watingToJoinEastward, watingToJoinWestward, walkingToEastward, walkingToWestward, onExpectedFirstMonkeys);
		Semaphore so = new SemaphoreWestward(watingToJoinEastward, watingToJoinWestward, walkingToEastward, walkingToWestward, onExpectedFirstMonkeys);
		
		return  new TrafficAgent(se, so);
	}
}
