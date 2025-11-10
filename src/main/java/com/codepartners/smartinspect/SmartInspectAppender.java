package com.codepartners.smartinspect;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import com.gurock.smartinspect.session.Session;

public class SmartInspectAppender extends AppenderBase<ILoggingEvent> {
    private final Session session;

    public SmartInspectAppender(Session session) {
        this.session = session;
    }

    @Override
    protected void append(ILoggingEvent eventObject) {
        session.logMessage(eventObject.getFormattedMessage());
    }
}