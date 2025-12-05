package grupo2.com.ecoPoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EcoPointApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcoPointApplication.class, args);
	}

    @Bean
    public PasswordEncoder passwordEncoder() {    /* Meu codificador de senha */
        return new BCryptPasswordEncoder();
    }
}

