package org.java2me.concurrency.monkeys;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;


/**
 * @author alejandro.contreras
 * 
 * Factory for make semaphore instances.
 *
 */
@Configuration
@Repository
public class SemaphoreFactory {

	
	@Bean(name="waitingEastward")
	@Scope(value=BeanDefinition.SCOPE_SINGLETON)
	public WaitingToJoinList watingToJoinEastward(){
		return new WaitingToJoinList();
	}
	
	@Bean(name="waitingWestward")
	@Scope(value=BeanDefinition.SCOPE_SINGLETON)
	public WaitingToJoinList watingToJoinWestward(){
		return new WaitingToJoinList();
	}
	
	@Bean(name="walkingEastward")
	@Scope(value=BeanDefinition.SCOPE_SINGLETON)
	public WalkingAcrossList walkingToEastward(){
		return new WalkingAcrossList();
	}
	
	@Bean(name="walkingWestward")
	@Scope(value=BeanDefinition.SCOPE_SINGLETON)
	public WalkingAcrossList walkingToWestward(){
		return new WalkingAcrossList();
	}
	
	@Bean
	@Scope(value=BeanDefinition.SCOPE_SINGLETON)
	public ExpectedMonkeys onExpectedFirstMonkeys(){
		return new ExpectedMonkeys();
	}
	
	@Bean(name = "semaphoreContext")
	@Scope(value=BeanDefinition.SCOPE_SINGLETON)
	public SemaphoreContext getSemaphoreContext(
			@Qualifier("waitingEastward") WaitingToJoinList watingToJoinEastward,
			@Qualifier("waitingWestward") WaitingToJoinList watingToJoinWestward,
			@Qualifier("walkingEastward") WalkingAcrossList walkingToEastward,
			@Qualifier("walkingWestward") WalkingAcrossList walkingToWestward,
			ExpectedMonkeys onExpectedFirstMonkeys) {
		
		return new SemaphoreContext(watingToJoinEastward, watingToJoinWestward, walkingToWestward, walkingToWestward, onExpectedFirstMonkeys);
		
	}
	
}
