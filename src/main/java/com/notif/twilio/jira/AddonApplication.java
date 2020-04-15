package com.notif.twilio.jira;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.notif.twilio.jira.io.repositories")
@SpringBootApplication
public class AddonApplication {

    public static void main(String[] args) throws Exception {
        new SpringApplication(AddonApplication.class).run(args);
    }
}
