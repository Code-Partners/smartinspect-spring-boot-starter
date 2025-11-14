package com.codepartners.smartinspect.log4j;

import com.codepartners.smartinspect.logexecutionflow.LogExecutionFlowAspect;
import com.gurock.smartinspect.session.Session;
import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnProperty(prefix = "smartinspect", name = "enabled", havingValue = "true")
@ConditionalOnClass(org.apache.logging.log4j.core.LogEvent.class)
public class SmartInspectLog4jAppenderRegisterer {
    private final Session session;

    public SmartInspectLog4jAppenderRegisterer(Session session) {
        this.session = session;
    }

    @PostConstruct
    public void register() {
        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        org.apache.logging.log4j.core.config.Configuration cfg = ctx.getConfiguration();
        SmartInspectLog4jAppender app = new SmartInspectLog4jAppender(session);
        app.start();
        cfg.addAppender(app);
        cfg.getRootLogger().addAppender(app, null, null);
        ctx.updateLoggers();
    }

    @Bean
    public LogExecutionFlowAspect logExecutionFlowAspect() {
        return new LogExecutionFlowAspect(session);
    }
}
