package org.java2me.concurrency.monkeys;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author alejandro.contreras
 * 
 * Monkeys manager application.
 *
 */
@SpringBootApplication
@ComponentScan(basePackages={"org.java2me.concurrency.monkeys"})
public class MonkeyApp implements CommandLineRunner {
	
	
	/**
	 * Number of monkeys to play.
	 */
	@Value("${monkeys.number:10}")
	private int monkeys;
	
	
	/**
	 * Bean factory to make object graph.
	 */
	@Autowired
	private BeanFactory beanFactory;
	
	
	/* (non-Javadoc)
	 * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
	 */
	public void run(String... args) throws Exception {
		
		if(args != null && args.length > 0){
			monkeys = Integer.valueOf(args[0]);
		}	
        	
		ThreadFactory monkeyThreadFactory = (ThreadFactory) beanFactory.getBean("monkeyThreadFactory");
		ExecutorService threadPool = Executors.newFixedThreadPool(monkeys, monkeyThreadFactory);
		
		 	
		for (int x=0; x<monkeys ;x++) {
			SemaphoreContext semaphoreContext = (SemaphoreContext) beanFactory.getBean("semaphoreContext");
			MonkeyHandler wakeUp = (MonkeyHandler) beanFactory.getBean("wakeUp");
			final Monkey monkey= (Monkey) beanFactory.getBean("monkey", wakeUp, semaphoreContext);
			
			threadPool.submit(monkey);	
		}
		
		threadPool.shutdown();	
	}

	
	/**
	 * Main access point.
	 * 
	 */
	public static void main(String[] args) throws Exception {
		SpringApplication.run(MonkeyApp.class, args);
	}
	
}
