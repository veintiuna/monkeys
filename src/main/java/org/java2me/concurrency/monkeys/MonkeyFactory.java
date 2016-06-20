package org.java2me.concurrency.monkeys;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MonkeyFactory {

	@Bean(name="monkey")
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public Monkey createRuntimeMonkey(Direction direction, int timeToReady, Semaphore semaphore) {
        return new Monkey(direction, timeToReady, semaphore);
    }
	
}
