package com.codepartners.smartinspect.log4j;

import com.gurock.smartinspect.session.Session;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.Property;

public class SmartInspectLog4jAppender extends AbstractAppender {
    private final Session session;

    protected SmartInspectLog4jAppender(Session session) {
        super("mirror", null, null, true, Property.EMPTY_ARRAY);
        this.session = session;
    }

    @Override
    public void append(LogEvent event) {
        session.logMessage(event.getMessage().getFormattedMessage());
    }
}
