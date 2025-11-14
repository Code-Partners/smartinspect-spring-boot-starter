package com.codepartners.smartinspect.jul;

import com.codepartners.smartinspect.logexecutionflow.LogExecutionFlowAspect;
import com.gurock.smartinspect.session.Session;
import jakarta.annotation.PostConstruct;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import java.util.logging.LogManager;

@AutoConfiguration
@ConditionalOnProperty(prefix = "smartinspect", name = "enabled", havingValue = "true")
@ConditionalOnMissingClass({"org.apache.logging.log4j.core.LogEvent", "ch.qos.logback.core.Context"})
public class SmartInspectJulHandlerRegisterer {
    private final Session session;

    public SmartInspectJulHandlerRegisterer(Session session) {
        this.session = session;
    }

    @PostConstruct
    public void register() {
        java.util.logging.Logger root = LogManager.getLogManager().getLogger("");
        root.addHandler(new SmartInspectJulHandler(session));
    }

    @Bean
    public LogExecutionFlowAspect logExecutionFlowAspect() {
        return new LogExecutionFlowAspect(session);
    }
}
