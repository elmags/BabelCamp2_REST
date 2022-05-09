package init;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
        .inMemoryAuthentication()
        .withUser("user1")
          .password("{noop}user1")
          .roles("GET")
          .and()
        .withUser("user2")
          .password("{noop}user2")
          .roles("BASIC");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		// Para acceder a la lista de reservas, tendrán que estar autenticados y pertenercer al rol "GET"
		.antMatchers(HttpMethod.GET,"/Reservas").authenticated()	
		.antMatchers(HttpMethod.GET,"/Reservas").hasRole("GET")
		.and()
		.httpBasic();		// forma de solicitar los credenciales
	}

}

