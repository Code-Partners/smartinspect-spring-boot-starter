package com.codepartners.smartinspect.log4j;

import com.gurock.smartinspect.ViewerId;
import com.gurock.smartinspect.packets.logentry.LogEntryType;
import com.gurock.smartinspect.session.Session;
import org.apache.logging.log4j.Level;
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
        Level level = event.getLevel();
        String text = event.getMessage().getFormattedMessage();

        com.gurock.smartinspect.Level siLevel;
        LogEntryType logEntryType;

        if (level.equals(Level.FATAL)) {
            siLevel = com.gurock.smartinspect.Level.Fatal;
            logEntryType = LogEntryType.Fatal;
        } else if (level.equals(Level.ERROR)) {
            siLevel = com.gurock.smartinspect.Level.Error;
            logEntryType = LogEntryType.Error;
        } else if (level.equals(Level.WARN)) {
            siLevel = com.gurock.smartinspect.Level.Warning;
            logEntryType = LogEntryType.Warning;
        } else if (level.equals(Level.INFO)) {
            siLevel = com.gurock.smartinspect.Level.Message;
            logEntryType = LogEntryType.Message;
        } else if (level.equals(Level.DEBUG)) {
            siLevel = com.gurock.smartinspect.Level.Debug;
            logEntryType = LogEntryType.Debug;
        } else if (level.equals(Level.TRACE)) {
            siLevel = com.gurock.smartinspect.Level.Verbose;
            logEntryType = LogEntryType.Verbose;
        } else {
            siLevel = com.gurock.smartinspect.Level.Verbose;
            logEntryType = LogEntryType.Verbose;
        }

        session.sendCustomLogEntry(
                siLevel, text, logEntryType, ViewerId.Title, null
        );
    }
}
