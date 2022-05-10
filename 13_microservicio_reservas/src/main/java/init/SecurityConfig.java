package init;

import javax.sql.DataSource;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//        .inMemoryAuthentication()
//        .withUser("user1")
//          .password("{noop}user1")
//          .roles("GET")
//          .and()
//        .withUser("user2")
//          .password("{noop}user2")
//          .roles("BASIC");
		auth.jdbcAuthentication()
		  .passwordEncoder(new BCryptPasswordEncoder())
		  .dataSource(dataSource())
      	.usersByUsernameQuery("select user, pwd, enabled"
          	+ " from users where user=?")
      	.authoritiesByUsernameQuery("select user, rol "
          	+ "from roles where user=?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		// Para acceder a la lista de reservas, tendrán que estar autenticados y pertenercer al rol "GET"
//		.antMatchers(HttpMethod.GET,"/Reservas").authenticated()	
		.antMatchers(HttpMethod.GET,"/Reservas").hasRole("GET")
		.and()
		.httpBasic();		// forma de solicitar los credenciales
	}
	
	private DataSource dataSource() {
		DriverManagerDataSource ds=new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/springsecurity?serverTimezone=UTC");
		ds.setUsername("root");
		ds.setPassword("");
		return ds;
	}
}

