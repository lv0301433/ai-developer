package com.aideveloper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@ComponentScan(basePackages = {"com.aideveloper"})
public class AIDeveloperApp {
    private static final Logger logger = LoggerFactory.getLogger(AIDeveloperApp.class);

    public static void main(String[] args) {
        logger.info("===========================================");
        logger.info("  AI Developer Platform - Starting Up");
        logger.info("  Multi-language Auto Code Generation");
        logger.info("  Version: 1.0.0");
        logger.info("===========================================");
        SpringApplication.run(AIDeveloperApp.class, args);
        logger.info("AI Developer Platform Ready!");
    }
}