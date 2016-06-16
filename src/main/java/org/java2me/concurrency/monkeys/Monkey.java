package org.java2me.concurrency.monkeys;

import java.util.List;


/**
 * @author alejandro.contreras
 * 
 * Monkey wants to cross the canyon.
 *
 */
public class Monkey implements Runnable {
	
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
	 * @param agent {@link Agent} with the traffic agent.
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
			 	Thread self = Thread.currentThread();
			 	
			 	Thread.sleep(timeToReady * 1000);
			 	threadMessage("i'm a monkey going to " + direction + " after " + timeToReady + " seconds");
			 	
			 	//add to waitingToJoin (maybe, wait for notify)
			 	agent.waitingToJoin(self);
				  
			 	//compute join list and join
			 	List<Thread> monkeysOpposite = agent.getJoin(self);
			 	threadMessage("i'm a monkey going to " + direction + " and ready for join with " +monkeysOpposite);
			 	for (Thread monkey: monkeysOpposite){
			 		monkey.join();
			 	}
			 	
			 	//after join, remove from waitingToJoin and get on the rope
			 	agent.removeFromWaitingToJoin(self);
			 	
			 	//cross after join
			 	agent.walking(self);
			 	Thread.sleep(4000);
			 	 	
			 	//go out
			 	agent.walkingOut(self);
			 	threadMessage("Game Over, " + direction + " at " + System.currentTimeMillis());
			 	
			 	
		} catch (InterruptedException e) {
			System.out.println("Maybe next time");
		}

	}


    /**
     * Display a message, preceded by the name of the current thread.
     * 
     * @param message {@link String} with the message.
     */
    static void threadMessage(String message) {
        String threadName = Thread.currentThread().getName();
        System.out.format("%-10s: %s%n",
                          threadName,
                          message);
    }
    
}
