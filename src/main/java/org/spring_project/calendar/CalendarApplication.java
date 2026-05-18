package org.spring_project.calendar;

import org.spring_project.calendar.Security.RsaKeyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyConfig.class)
@SpringBootApplication
public class CalendarApplication {

    static void main(String[] args) {
        SpringApplication.run(CalendarApplication.class, args);
    }

}
