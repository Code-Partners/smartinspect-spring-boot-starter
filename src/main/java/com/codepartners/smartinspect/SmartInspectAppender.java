package com.codepartners.smartinspect;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import com.gurock.smartinspect.ViewerId;
import com.gurock.smartinspect.packets.logentry.LogEntryType;
import com.gurock.smartinspect.session.Session;

import static ch.qos.logback.classic.Level.*;

public class SmartInspectAppender extends AppenderBase<ILoggingEvent> {
    private final Session session;

    public SmartInspectAppender(Session session) {
        this.session = session;
    }

    @Override
    protected void append(ILoggingEvent eventObject) {
        Level level = eventObject.getLevel();
        com.gurock.smartinspect.Level siLevel;

        String text = eventObject.getFormattedMessage();

        if (level.equals(ERROR)) {
            siLevel = com.gurock.smartinspect.Level.Error;
        } else if (level.equals(WARN)) {
            siLevel = com.gurock.smartinspect.Level.Warning;
        } else if (level.equals(INFO)) {
            siLevel = com.gurock.smartinspect.Level.Message;
        } else if (level.equals(DEBUG)) {
            siLevel = com.gurock.smartinspect.Level.Debug;
        } else if (level.equals(TRACE)) {
            siLevel = com.gurock.smartinspect.Level.Verbose;
        } else {
            siLevel = com.gurock.smartinspect.Level.Message;
        }

        session.sendCustomLogEntry(
                siLevel, text, LogEntryType.Message, ViewerId.Title, null
        );
    }
}