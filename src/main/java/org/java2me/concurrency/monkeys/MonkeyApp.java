package org.java2me.concurrency.monkeys;

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
		
		for (int x=0; x<monkeys ;x++) {
			SemaphoreContext semaphoreContext = (SemaphoreContext) beanFactory.getBean("semaphoreContext");
			MonkeyHandler wakeUp = (MonkeyHandler) beanFactory.getBean("wakeUp");
			final Monkey monkey= (Monkey) beanFactory.getBean("monkey", wakeUp, semaphoreContext);
			
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
	
}
