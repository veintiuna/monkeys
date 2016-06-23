package org.java2me.concurrency.monkeys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


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
	 * Time to wake up.
	 */
	private int timeToReady;
	
	
	/**
	 * Semaphores context for traffic control.
	 */
	@Autowired
	private SemaphoreContext semaphoreContext;
	
	/**
	 * Handler for get in queue.
	 */
	private MonkeyHandler getInQueue;

	/**
	 * Monkey Constructor.
	 * 
	 * @param direction {@link Direction} with direction to cross.
	 * @param timeToReady with the time to reach the border.
	 * @param agent {@link Semaphore} with the traffic agent.
	 */
	public Monkey(Direction direction, int timeToReady,  MonkeyHandler getInQueue) {
		super();
		this.direction = direction;
		this.timeToReady = timeToReady;
		this.getInQueue = getInQueue;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		
		 try {		 	
			 	Thread.sleep(timeToReady * 1000);
			 	System.out.println(Thread.currentThread() + " i'm a monkey going to " + direction + " after " + timeToReady + " seconds");
			 	
			 	getInQueue.handleMonkey(semaphoreContext);
			 	
			 	
		} catch (InterruptedException e) {
			logger.info("Maybe next time");
		}

	}
    
}
