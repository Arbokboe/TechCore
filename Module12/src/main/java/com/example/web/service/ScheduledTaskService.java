package com.example.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledTaskService {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTaskService.class);

    @Scheduled(cron = "0 * * * * ?")
    public void runScheduledTask() {
        logger.info("Running scheduled task...");
    }
}
