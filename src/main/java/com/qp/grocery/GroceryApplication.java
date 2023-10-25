package com.qp.grocery;

import com.qp.grocery.model.Role;
import com.qp.grocery.model.User;
import com.qp.grocery.repository.UserRepository;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class GroceryApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GroceryApplication.class, args);
	}

	@Autowired
	UserRepository repository;

	@Autowired
	PasswordEncoder passwordEncoder;


	@Override
	public void run(String... args) throws Exception {
		User adminUser = new User("admin", passwordEncoder.encode("admin@123"), Role.ROLE_ADMIN.name());
		User user = new User("user",  passwordEncoder.encode("user@123"), Role.ROLE_USER.name());

		repository.save(adminUser);
		repository.save(user);
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@PreDestroy
	public void preDestroy() {
		repository.deleteAll();
	}
}
