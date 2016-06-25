package org.java2me.concurrency.monkeys;

import java.util.Random;

public class RandomUtil {

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
	
}
