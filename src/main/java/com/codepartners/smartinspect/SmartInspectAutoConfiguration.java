package com.codepartners.smartinspect;

import ch.qos.logback.classic.Logger;
import jakarta.annotation.PostConstruct;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;

@AutoConfiguration
//@EnableConfigurationProperties({CustomProperties.class})
public class SmartInspectAutoConfiguration {
    @PostConstruct
    public void register() {
        Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        SmartInspectAppender mirror = new SmartInspectAppender();
        mirror.setContext(root.getLoggerContext());
        mirror.start();
        root.addAppender(mirror);
    }
}
