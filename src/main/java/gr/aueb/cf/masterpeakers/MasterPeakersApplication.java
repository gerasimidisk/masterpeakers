package gr.aueb.cf.masterpeakers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MasterPeakersApplication {

    public static void main(String[] args) {
        SpringApplication.run(MasterPeakersApplication.class, args);
    }
}
