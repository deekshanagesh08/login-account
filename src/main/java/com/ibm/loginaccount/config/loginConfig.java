package com.ibm.loginaccount.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.loginaccount.service.MyUserDetailsService;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

	@Configuration
	public class loginConfig extends WebSecurityConfigurerAdapter {
		@Autowired
		private MyUserDetailsService myUserDetailsService;
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService( myUserDetailsService);
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception{
			http.csrf().disable().authorizeRequests().antMatchers("/authenticate").permitAll().anyRequest().authenticated();
		}
		
		@Override
		@Bean
		public AuthenticationManager authenticationManagerBean() throws Exception{
			return super.authenticationManagerBean();
		}
				
		@Bean
		public PasswordEncoder passwordEncoder(){
			return NoOpPasswordEncoder.getInstance();
		}
		
}

