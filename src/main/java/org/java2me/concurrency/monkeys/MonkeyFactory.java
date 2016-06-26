package org.java2me.concurrency.monkeys;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MonkeyFactory {

	/**
	 * Make a monkey with prototype strategy.
	 * 
	 * @param monkeyHandler {@link MonkeyHandler} with the first handler to cross the canyon.
	 * @param semaphoreContext {@link SemaphoreContext} with the semaphores to manage traffic.
	 * @return {@linkplain Monkey} with the monkey.
	 */
	@Bean(name="monkey")
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public Monkey createRuntimeMonkey(MonkeyHandler monkeyHandler, SemaphoreContext semaphoreContext) {
        return new Monkey(monkeyHandler, semaphoreContext);
    }
	
	@Bean(name="monkeyThreadFactory")
	public MonkeyThreadFactory getMonkeyThreadFactory() {
		return new MonkeyThreadFactory();
	}
	
}
