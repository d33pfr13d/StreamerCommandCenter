package streaming.scc.boot;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "streaming.scc.services.kofi")
public class Application {

	public static void main(String[] args) {
		 launch(args);
	}

	public static void launch(String[] args) {
//		SpringApplication.run(Application.class, args);
		
		// Launch with custom port
		SpringApplication app = new SpringApplication(Application.class);
	        app.setDefaultProperties(Collections
	          .singletonMap("server.port", "8081"));
	        app.run(args);
	}

}
