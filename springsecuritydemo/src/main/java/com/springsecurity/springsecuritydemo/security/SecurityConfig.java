package com.springsecurity.springsecuritydemo.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springsecurity.springsecuritydemo.security.jwt.AuthEntryPointJwt;
import com.springsecurity.springsecuritydemo.security.jwt.AuthTokenFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	@Autowired
	DataSource dataSource;
	
	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;
	
	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}
	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/h2-console/**").permitAll()
				.requestMatchers("/signin").permitAll()
				.anyRequest().authenticated());
		http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.exceptionHandling(exception->exception.authenticationEntryPoint(unauthorizedHandler));
		//http.httpBasic(withDefaults());
		http.headers(headers-> headers.frameOptions(frameOptions->frameOptions.sameOrigin()));
		http.csrf(csrf->csrf.disable());
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails user1= User.withUsername("User1").password(passwordEncoder().encode("password1")).roles("USER").build();
//		UserDetails admin= User.withUsername("admin").password(passwordEncoder().encode("adminPassword")).roles("ADMIN").build();
//		//UserDetails user1= User.withUsername("User1").password("{noop}password1").roles("USER").build();
//		//UserDetails admin= User.withUsername("admin").password("{noop}adminPassword").roles("ADMIN").build();
//		//when using Database we have to use JdbcUserDetailsManager it takes DataSource as parameter
//		JdbcUserDetailsManager userDetailsManager= new JdbcUserDetailsManager(dataSource);
//		userDetailsManager.createUser(admin);
//		userDetailsManager.createUser(user1);
//		return userDetailsManager;
//		//return new InMemoryUserDetailsManager(user1,admin);
//	}
	
	@Bean
	public UserDetailsService userDetailsService(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}
	
	@Bean
	public CommandLineRunner initData(UserDetailsService userDetailsService) {
		return args ->{
			JdbcUserDetailsManager manager=(JdbcUserDetailsManager) userDetailsService;
			UserDetails user1= User.withUsername("User1").password(passwordEncoder().encode("password1")).roles("USER").build();
			UserDetails admin= User.withUsername("admin").password(passwordEncoder().encode("adminPassword")).roles("ADMIN").build();
			//UserDetails user1= User.withUsername("User1").password("{noop}password1").roles("USER").build();
			//UserDetails admin= User.withUsername("admin").password("{noop}adminPassword").roles("ADMIN").build();
			//when using Database we have to use JdbcUserDetailsManager it takes DataSource as parameter
			JdbcUserDetailsManager userDetailsManager= new JdbcUserDetailsManager(dataSource);
			userDetailsManager.createUser(admin);
			userDetailsManager.createUser(user1);
		};
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
	    return configuration.getAuthenticationManager();
	}
}
