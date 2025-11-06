package com.codepartners.smartinspect;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

public class SmartInspectAppender extends AppenderBase<ILoggingEvent> {
    @Override
    protected void append(ILoggingEvent eventObject) {
        System.out.println(">> " + eventObject.getFormattedMessage());
    }
}