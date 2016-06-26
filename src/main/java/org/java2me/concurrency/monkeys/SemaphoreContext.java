package org.java2me.concurrency.monkeys;

import org.springframework.stereotype.Component;

/**
 * @author alejandro.contreras
 * 
 * Contains rules for traffic control. 
 */
@Component
public class SemaphoreContext {

	/**
	 * Waiting room to go eastward.
	 */
	private WaitingToJoinList watingToJoinEastward;
	
	/**
	 * Waiting room to go westward.
	 */
	private WaitingToJoinList watingToJoinWestward;
	
	/**
	 * Walking on the rope eastward.
	 */
	private WalkingAcrossList walkingToEastward;
	
	/**
	 * Walking on the rope westward.
	 */
	private WalkingAcrossList walkingToWestward;
	
	/**
	 * Monkeys who are being expected on the other side.
	 */
	private ExpectedMonkeys onExpectedFirstMonkeys;
	
	
	public SemaphoreContext(
			WaitingToJoinList watingToJoinEastward,
			WaitingToJoinList watingToJoinWestward,
			WalkingAcrossList walkingToEastward,
			WalkingAcrossList walkingToWestward,
			ExpectedMonkeys onExpectedFirstMonkeys) {
		
		super();
		this.watingToJoinEastward = watingToJoinEastward;
		this.watingToJoinWestward = watingToJoinWestward;
		this.walkingToEastward = walkingToEastward;
		this.walkingToWestward = walkingToWestward;
		this.onExpectedFirstMonkeys = onExpectedFirstMonkeys;
	}

	public WaitingToJoinList getWatingToJoinEastward() {
		return watingToJoinEastward;
	}

	public WaitingToJoinList getWatingToJoinWestward() {
		return watingToJoinWestward;
	}

	public WalkingAcrossList getWalkingToEastward() {
		return walkingToEastward;
	}

	public WalkingAcrossList getWalkingToWestward() {
		return walkingToWestward;
	}

	public ExpectedMonkeys getOnExpectedFirstMonkeys() {
		return onExpectedFirstMonkeys;
	}
	
	
}
