package org.java2me.concurrency.monkeys;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import sun.management.Agent;

/**
 * @author alejandro.contreras
 * 
 * Factory for make {@link Agent} instances.
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
	
	/**
	 * Make and returns an map with semaphores.
	 * 
	 * @return {@link Map} with semaphores.
	 */
	@Bean(name="semaphores")
	@Scope(value=BeanDefinition.SCOPE_SINGLETON)
	public Map<Direction, Semaphore> makeSemaphores(@Qualifier("semaphoreEastward")
			Semaphore semaphoreEastward, @Qualifier("semaphoreWestward")
			Semaphore semaphoreWestward)
	{
				
		Map<Direction, Semaphore> semaphores= new HashMap<Direction, Semaphore>();
		semaphores.put(Direction.EASTWARD, semaphoreEastward);
		semaphores.put(Direction.WESTWARD, semaphoreWestward);
		
		return semaphores;
	}
	
}
