package com.ultimate.testing;

import com.ultimate.testing.entity.MathGrade;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	@Scope(value = "prototype")
	@Qualifier("mathGrades")
	MathGrade getGrade() {
		return new MathGrade();
	}

}
