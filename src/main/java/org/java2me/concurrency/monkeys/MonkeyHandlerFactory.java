package org.java2me.concurrency.monkeys;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author alejandro.contreras
 * 
 * Factory to make monkey handlers.
 *
 */
@Configuration
public class MonkeyHandlerFactory {

	@Bean(name="getInQueueEastward")
    public MonkeyHandler createGetInQueueEastward(@Qualifier("takeTimeEastward") MonkeyHandler handler) {
        return new GetInQueueEastward(handler);
    }
	
	@Bean(name="takeTimeEastward")
    public MonkeyHandler createTakeTimeEastward(@Qualifier("leaveQueueEastward") MonkeyHandler handler) {
        return new TakeTimeEastward(handler);
    }
	
	@Bean(name="leaveQueueEastward")
    public MonkeyHandler createLeaveQueueEastward(@Qualifier("crossCanyonEastward") MonkeyHandler handler) {
        return new LeaveQueueEastward(handler);
    }
	
	@Bean(name="crossCanyonEastward")
    public MonkeyHandler createCrossCanyonEastward(@Qualifier("leaveCanyonEastward") MonkeyHandler handler) {
        return new CrossCanyonEastward(handler);
    }
	
	@Bean(name="leaveCanyonEastward")
    public MonkeyHandler createLeaveCanyonEastward() {
        return new LeaveCanyonEastward();
    }
	
	//
	
	@Bean(name="getInQueueWestward")
    public MonkeyHandler createGetInQueueWestward(@Qualifier("takeTimeWestward") MonkeyHandler handler) {
        return new GetInQueueWestward(handler);
    }
	
	@Bean(name="takeTimeWestward")
    public MonkeyHandler createTakeTimeWestward(@Qualifier("leaveQueueWestward") MonkeyHandler handler) {
        return new TakeTimeWestward(handler);
    }
	
	@Bean(name="leaveQueueWestward")
    public MonkeyHandler createLeaveQueueWestward(@Qualifier("crossCanyonWestward") MonkeyHandler handler) {
        return new LeaveQueueWestward(handler);
    }
	
	@Bean(name="crossCanyonWestward")
    public MonkeyHandler createCrossCanyonWestward(@Qualifier("leaveCanyonWestward") MonkeyHandler handler) {
        return new CrossCanyonWestward(handler);
    }
	
	@Bean(name="leaveCanyonWestward")
    public MonkeyHandler createLeaveCanyonWestward() {
        return new LeaveCanyonWestward();
    }
	
	@Bean(name="wakeUp")
	@Scope(BeanDefinition.SCOPE_PROTOTYPE)
	public MonkeyHandler createWakeUpEastward(@Qualifier("getInQueueEastward") MonkeyHandler getInQueueEastward,
			@Qualifier("getInQueueWestward") MonkeyHandler getInQueueWestward){
		
		Map<Direction,MonkeyHandler> monkeyHandlerMap = new HashMap<Direction, MonkeyHandler>();
		monkeyHandlerMap.put(Direction.EASTWARD, getInQueueEastward);
		monkeyHandlerMap.put(Direction.WESTWARD, getInQueueWestward);
		
		
		RandomUtil util = new RandomUtil();
		Direction direction = Direction.get(util.random(0, 1));
		return new WakeUp(monkeyHandlerMap.get(direction), util.random(1,8), direction);
	}
	
	@Bean
	public RandomUtil getRandomUtil() {
		return new RandomUtil();
	}
}
