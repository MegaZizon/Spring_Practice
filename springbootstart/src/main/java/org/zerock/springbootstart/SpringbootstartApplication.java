package org.zerock.springbootstart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class SpringbootstartApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootstartApplication.class, args);
    }

}
