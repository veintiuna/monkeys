package org.java2me.concurrency.monkeys;

import java.util.concurrent.ThreadFactory;

/**
 * @author alejandro.contreras
 * 
 * Factory to make monkey threads.
 *
 */
public class MonkeyThreadFactory implements ThreadFactory {

	private int counter = 0;
	
	@Override
	public Thread newThread(Runnable r) {
		return new Thread(r,"Monkey-" + counter++){
			
			@Override
			public String toString(){
				return this.getName();
			}
		};
	}

}
