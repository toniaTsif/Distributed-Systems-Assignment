package gr.hua.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)

public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder())
				.usersByUsernameQuery("select id, password, enabled from person where id=?")
				.authoritiesByUsernameQuery("select u.id, r.role from person u, person r where u.id=r.id and u.id=?");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/login").permitAll()
				.antMatchers("/api/**").permitAll()
				.antMatchers("/student/*").hasRole("STUDENT")
				.antMatchers("/employee/*").hasAnyRole("EMPLOYEE", "MANAGER")
				.antMatchers("/manager/*").hasRole("MANAGER")
				.anyRequest().authenticated().and().formLogin().loginPage("/login")
				.loginProcessingUrl("/authUser").defaultSuccessUrl("/").and().logout().permitAll().and().exceptionHandling()
				.accessDeniedPage("/403");
	}
	
	@Override
	public void configure(WebSecurity web){
		web.ignoring().antMatchers("/resources/**");
		web.ignoring().antMatchers("/api/**");

	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
	    return new PasswordEncoderTest();
	}
}
