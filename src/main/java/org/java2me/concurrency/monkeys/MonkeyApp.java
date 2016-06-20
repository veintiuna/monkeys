package org.java2me.concurrency.monkeys;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"org.java2me.concurrency.monkeys"})
public class MonkeyApp implements CommandLineRunner {
	
	
	@Value("${monkeys.number:10}")
	private int monkeys;
	
	@Autowired
	private BeanFactory beanFactory;
	
	/* (non-Javadoc)
	 * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
	 */
	public void run(String... args) throws Exception {
		
		Map<Direction, Semaphore> semaphores = (Map<Direction, Semaphore>) beanFactory.getBean("semaphores");
		
		if(args != null && args.length > 0){
			monkeys = Integer.valueOf(args[0]);
		}
		
		
		for (int x=0; x<monkeys ;x++) {
			
			int dir = random(0,1);
			int timeToReady = random(1,8);
			
			Direction direction = Direction.random(dir);
			final Monkey monkey= (Monkey) beanFactory.getBean("monkey", direction, timeToReady, semaphores.get(direction));
			
			Thread tMonkey = new Thread(monkey){
				
				@Override
				public String toString(){
					return this.getName();
				}
			};
			
			tMonkey.start();
			
		}
		
	}

	

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MonkeyApp.class, args);
	}
	
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
