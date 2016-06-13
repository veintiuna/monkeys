package org.java2me.concurrency.monkeys;

/**
 * @author alejandro.contreras
 * 
 * Direction of movement on the canyon.
 *
 */
public enum Direction {
	
	WESTWARD(1), EASTWARD(0);
	
	
	/**
	 * Coded value with direction.
	 */
	private int direction;

	/**
	 * Constructor.
	 * 
	 * @param direction with direction.
	 */
	private Direction(int direction) {
		this.direction = direction;
	}
	
	/**
	 * Get direction.
	 * 
	 * @return value with direction.
	 */
	public int getDirection(){
		return this.direction;
	}
	
	/**
	 * Utility for generate {@link Direction} values randomly.
	 * 
	 * @param n a number, zero or one.
	 * @return {@link Direction} with direction.
	 */
	public static Direction random(int n){
		return (n == 0)? EASTWARD:WESTWARD; 
	}
	
}
