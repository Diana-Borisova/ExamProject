package com.example.foodplanner.event;



import com.example.foodplanner.service.LogService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;



@Component
public class LogEvent {

    private final LogService logService;
    private final Logger LOGGER = LoggerFactory.getLogger(LogEvent.class);

    public LogEvent(LogService logService) {
        this.logService = logService;
    }


    @Async
    @Transactional
    @Scheduled(cron = "0 10 20 */1 * 1")
    public void cleanLog() {
        logService.cleanLog();
        LOGGER.info("Log history cleaned");
    }

    @Async
    @Transactional
    @Scheduled(cron = "0 0 20 */1 * *")
    public void cleanExceptionLog() {
        logService.cleanExceptions();
        LOGGER.info("Exception history cleaned");
    }
}