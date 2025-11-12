package com.codepartners.smartinspect;

import ch.qos.logback.classic.Logger;
import com.codepartners.smartinspect.logexecutionflow.LogExecutionFlowAspect;
import com.gurock.smartinspect.session.Session;
import jakarta.annotation.PostConstruct;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnProperty(prefix = "smartinspect", name = "enabled", havingValue = "true")
public class SmartInspectAppenderRegisterer {
    private final Session session;

    public SmartInspectAppenderRegisterer(Session session) {
        this.session = session;
    }

    @PostConstruct
    public void register() {
        Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        SmartInspectAppender mirror = new SmartInspectAppender(session);
        mirror.setContext(root.getLoggerContext());
        mirror.start();
        root.addAppender(mirror);
    }

    @Bean
    public LogExecutionFlowAspect logExecutionFlowAspect() {
        return new LogExecutionFlowAspect(session);
    }
}
