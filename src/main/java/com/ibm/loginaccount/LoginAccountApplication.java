package com.ibm.loginaccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.ibm.loginaccount"})
@SpringBootApplication
public class LoginAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginAccountApplication.class, args);
	}

}
