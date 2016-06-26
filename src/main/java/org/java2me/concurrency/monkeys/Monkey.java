package org.java2me.concurrency.monkeys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author alejandro.contreras
 * 
 * Monkeys on the canyon going from here to there.
 *
 */
public class Monkey implements Runnable {
	
	/**
	 * Logger.
	 */
	private Logger logger = LoggerFactory.getLogger(Monkey.class);
	
	/**
	 * Semaphores context for traffic control.
	 */
	private SemaphoreContext semaphoreContext;
	
	/**
	 * Handler for wake up and then, cross the canyon.
	 */
	private MonkeyHandler wakeUp;

	
	public Monkey(MonkeyHandler wakeUp, SemaphoreContext semaphoreContext) {
		super();	
		this.wakeUp = wakeUp;
		this.semaphoreContext = semaphoreContext;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		
		 try {		
			 	wakeUp.handleMonkey(semaphoreContext);	 	
			 	
		} catch (InterruptedException e) {
			logger.info("Maybe next time");
		}

	}
    
}
