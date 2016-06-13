package org.java2me.concurrency.monkeys;

import java.util.Random;

/**
 * @author alejandro.contreras
 * 
 * Monkeys manager.
 * Make the monkeys run across the canyon.
 *
 */
/**
 * @author usuario
 *
 */
public class MonkeyManager implements Runnable{

	/**
	 * Traffic agent.
	 */
	private Agent agent;
	
	/**
	 * Number of monkeys to play.
	 */
	private int monkeys; 
	
	/**
	 * Default constructor.
	 */
	public MonkeyManager() {
		super();
		this.agent = new TrafficAgentFactory().makeTrafficAgent();
		this.monkeys = 50;
	}
	
	
	/**
	 * Utility to generate random numbers between a range.
	 * 
	 * @param min with the minimum.
	 * @param max with the maximum.
	 * @return value with the number generated.
	 */
	public int random(int min, int max){
		return new Random().nextInt((max - min) + 1) + min;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		
		for (int x=0; x<monkeys ;x++) {
			
			int dir = random(0,1);
			int timeToReady = random(1,8);
			final Monkey monkey= new Monkey(Direction.random(dir), timeToReady, agent);
			
			Thread tMonkey = new Thread(monkey){
				
				@Override
				public String toString(){
					return this.getName();
				}
			};
			
			tMonkey.start();
			
		}
		
	}
	
	
	/**
	 * Set the number of monkeys to play.
	 * 
	 * @param monkeys with the number of monkeys.
	 */
	public void setMonkeys(int monkeys) {
		this.monkeys = monkeys;
	}


	/**
	 * Main access.
	 * It is expected that the first argument to be the number of monkeys to play.
	 * 
	 * @param args with program arguments.
	 * 
	 */
	public static void main(String[] args) {
		
		MonkeyManager manager = new MonkeyManager();
		
		if(args != null && args.length > 0){
			manager.setMonkeys(Integer.valueOf(args[0]));
		}
		
		manager.run();
		
	}
	
}
