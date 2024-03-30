package cn.jackson.tools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ToolService {
    public static void main(String[] args) {
        SpringApplication.run(ToolService.class, args);
    }
}
