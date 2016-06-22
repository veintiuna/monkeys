package org.java2me.concurrency.monkeys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author alejandro.contreras
 * 
 * Monkey wanting to cross the canyon.
 *
 */
public class Monkey implements Runnable {
	
	/**
	 * Logger.
	 */
	private Logger logger = LoggerFactory.getLogger(Monkey.class);
	
	/**
	 *  Direction to cross.
	 */
	private Direction direction;
	
	/**
	 * Time to reach border.
	 */
	private int timeToReady;
	
	/**
	 * Traffic agent that rules the crossing.
	 */
	private Semaphore agent;
	

	/**
	 * Monkey Constructor.
	 * 
	 * @param direction {@link Direction} with direction to cross.
	 * @param timeToReady with the time to reach the border.
	 * @param agent {@link Semaphore} with the traffic agent.
	 */
	public Monkey(Direction direction, int timeToReady, Semaphore agent) {
		super();
		this.direction = direction;
		this.timeToReady = timeToReady;
		this.agent = agent;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		
		 try {		 	
			 	Thread.sleep(timeToReady * 1000);
			 	System.out.println(Thread.currentThread() + " i'm a monkey going to " + direction + " after " + timeToReady + " seconds");
			 	
			 	agent
			 		.getInQueue()
			 		.takeTime()
				  	.leaveQueue()
				  	.crossCanyon()
				  	.leaveCanyon();
			 	
			 	
		} catch (InterruptedException e) {
			logger.info("Maybe next time");
		}

	}
    
}
