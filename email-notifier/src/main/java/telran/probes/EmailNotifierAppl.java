package telran.probes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor

public class EmailNotifierAppl {
	public static void main(String[] args) {
		SpringApplication.run(EmailNotifierAppl.class, args);

	}
}
