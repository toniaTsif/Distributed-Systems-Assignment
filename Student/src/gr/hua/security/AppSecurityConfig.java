/*package gr.hua.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import gr.hua.entity.Person;
import gr.hua.request.ServerCommunication;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    		List<Person> persons = ServerCommunication.getStudents();
    	
    		for (Person person : persons) {
    			auth.inMemoryAuthentication()
    			.withUser(person.getName()).password("{noop}" + person.getPassword())
    			.roles("STUDENT");
    		}

    }
    
    @Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/login").permitAll()
				.antMatchers("/api/**").hasRole("STUDENT")
				.antMatchers("/").hasRole("STUDENT")
				.anyRequest().authenticated().and().formLogin().loginPage("/login")
				.loginProcessingUrl("/authUser").defaultSuccessUrl("/").and().logout().permitAll().and().exceptionHandling()
				.accessDeniedPage("/403");
	}*/
    
    /*@Override
	public void configure(WebSecurity web){
		web.ignoring().antMatchers("/resources/**");
		web.ignoring().antMatchers("/api/**");

	}*/
	
	/*@Bean
	public PasswordEncoder passwordEncoder(){
	    return new PasswordEncoderTest();
	}
}*/
