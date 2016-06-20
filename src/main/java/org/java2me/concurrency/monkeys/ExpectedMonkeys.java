package org.java2me.concurrency.monkeys;

import java.util.ArrayList;
import java.util.List;

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
