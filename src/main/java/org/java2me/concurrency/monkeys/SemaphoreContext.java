package org.java2me.concurrency.monkeys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author alejandro.contreras
 * 
 * Contains rules for traffic control. 
 */
@Component
public class SemaphoreContext {

	private WaitingToJoinList watingToJoinEastward;
	private WaitingToJoinList watingToJoinWestward;
	private WalkingAcrossList walkingToEastward;
	private WalkingAcrossList walkingToWestward;
	private ExpectedMonkeys onExpectedFirstMonkeys;
	
	
	
	@Autowired
	public SemaphoreContext(
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
