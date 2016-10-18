package com.ranko.rent_a_car.config;

import com.ranko.rent_a_car.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;


@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = CustomUserDetailsService.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

//	@Autowired
//	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService);  //.passwordEncoder(passwordencoder());
//	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
				.inMemoryAuthentication()
				.withUser("ranko").password("ranko").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/resources/**"/*, "/home", "/"*/).permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin().loginPage("/login").permitAll()
				.and().csrf()
				.and()
				.logout().logoutSuccessUrl("/login?logout").permitAll();
	}

	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		*//*http.authorizeRequests()
				.antMatchers("/", "/resources*//**//**", "/login", "/home").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin().loginPage("/login").permitAll();*//*

				*//*
				antMatchers("/admin*//**//**")
				.access("hasRole('ROLE_ADMIN')").and().formLogin()
				.loginPage("/login").failureUrl("/login?error")
				.usernameParameter("username")
				.passwordParameter("password")
				.and().logout().logoutSuccessUrl("/login?logout")
				.and().csrf()
				.and().exceptionHandling().accessDeniedPage("/403");*//*
	}*/

	/*@Bean(name = "passwordEncoder")
	public PasswordEncoder passwordencoder() {
		return new BCryptPasswordEncoder();
	}*/
}