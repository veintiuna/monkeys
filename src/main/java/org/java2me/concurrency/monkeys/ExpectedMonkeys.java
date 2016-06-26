package org.java2me.concurrency.monkeys;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alejandro.contreras
 * 
 * List of monkeys who are being expected on the other side.
 *
 */
public class ExpectedMonkeys {

	private List<Thread> expectedMonkeys = new ArrayList<Thread>(); 
	
	public boolean contains(Object obj) {
		return expectedMonkeys.contains(obj);
	}
	
	public boolean add(Thread monkey) {
		return expectedMonkeys.add(monkey);
	}
	
	public boolean remove(Thread monkey) {
		return expectedMonkeys.remove(monkey);
	}
}
