package hpe.tfm;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScan("hpe.tfm")
@Configuration
public class Application {
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
	public static void main (String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext context) {
		return args -> {
			String[] beanNames = context.getBeanDefinitionNames();
			Arrays.parallelSort(beanNames);
			for (String beanName : beanNames) 
				System.out.println(beanName);
		};
	}

}
