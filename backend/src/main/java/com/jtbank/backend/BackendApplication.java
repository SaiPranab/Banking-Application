package com.jtbank.backend;

import com.jtbank.backend.component.AuditingImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditingImpl")
public class BackendApplication {

	public static void main(String[] args) {
		System.out.println("hii");
		SpringApplication.run(BackendApplication.class, args);
	}
}
