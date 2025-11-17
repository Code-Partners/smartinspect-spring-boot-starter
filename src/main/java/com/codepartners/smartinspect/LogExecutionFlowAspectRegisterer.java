package com.codepartners.smartinspect;

import com.codepartners.smartinspect.logexecutionflow.LogExecutionFlowAspect;
import com.gurock.smartinspect.session.Session;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnProperty(prefix = "smartinspect", name = "enabled", havingValue = "true")
public class LogExecutionFlowAspectRegisterer {
    private final Session session;

    public LogExecutionFlowAspectRegisterer(Session session) {
        this.session = session;
    }

    @Bean
    public LogExecutionFlowAspect logExecutionFlowAspect() {
        return new LogExecutionFlowAspect(session);
    }
}
