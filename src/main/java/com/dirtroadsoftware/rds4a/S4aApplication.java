package com.dirtroadsoftware.rds4a;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class S4aApplication {

	public static void main(String[] args) {
        new SpringApplicationBuilder().sources(S4aApplication.class).run(args);
    }
}