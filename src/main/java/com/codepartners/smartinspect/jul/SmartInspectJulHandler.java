package com.codepartners.smartinspect.jul;

import com.gurock.smartinspect.ViewerId;
import com.gurock.smartinspect.packets.logentry.LogEntryType;
import com.gurock.smartinspect.session.Session;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class SmartInspectJulHandler extends Handler {
    private final Session session;

    public SmartInspectJulHandler(Session session) {
        this.session = session;
    }

    @Override
    public void publish(LogRecord record) {
        Level level = record.getLevel();
        String text = record.getMessage();

        com.gurock.smartinspect.Level siLevel;
        LogEntryType logEntryType;

        if (level.equals(Level.SEVERE)) {
            siLevel = com.gurock.smartinspect.Level.Error;
            logEntryType = LogEntryType.Error;
        } else if (level.equals(Level.WARNING)) {
            siLevel = com.gurock.smartinspect.Level.Warning;
            logEntryType = LogEntryType.Warning;
        } else if (level.equals(Level.INFO)) {
            siLevel = com.gurock.smartinspect.Level.Message;
            logEntryType = LogEntryType.Message;
        } else if (level.equals(Level.FINE) || level.equals(Level.FINER)) {
            siLevel = com.gurock.smartinspect.Level.Debug;
            logEntryType = LogEntryType.Debug;
        } else if (level.equals(Level.FINEST)) {
            siLevel = com.gurock.smartinspect.Level.Verbose;
            logEntryType = LogEntryType.Verbose;
        } else {
            siLevel = com.gurock.smartinspect.Level.Message;
            logEntryType = LogEntryType.Verbose;
        }

        session.sendCustomLogEntry(
                siLevel, text, logEntryType, ViewerId.Title, null
        );
    }

    @Override
    public void flush() {

    }

    @Override
    public void close() throws SecurityException {

    }
}
