package init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = {"service", "controller"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public RestTemplate template() {
		// Para proporcionar los credenciales de forma básica en Authorization utilizamos un interceptor
		BasicAuthenticationInterceptor intercep = new BasicAuthenticationInterceptor("admin", "admin");
		RestTemplate template = new RestTemplate();
		template.getInterceptors().add(intercep);
		return template;
	}
}
