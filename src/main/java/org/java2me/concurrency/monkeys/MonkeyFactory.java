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
	 * @param direction {@link Direction} with direction.
	 * @param timeToReady Value with time to wake up.
	 * @param monkeyHandler {@link MonkeyHandler} with the first handler to cross the canyon.
	 * @return {@linkplain Monkey} with the monkey.
	 */
	@Bean(name="monkey")
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public Monkey createRuntimeMonkey(MonkeyHandler monkeyHandler, SemaphoreContext semaphoreContext) {
        return new Monkey(monkeyHandler, semaphoreContext);
    }
	
	
}
