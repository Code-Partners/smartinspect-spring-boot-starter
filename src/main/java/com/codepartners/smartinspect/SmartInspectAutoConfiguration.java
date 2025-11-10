package com.codepartners.smartinspect;

import com.gurock.smartinspect.*;
import com.gurock.smartinspect.connections.builder.cloud.CloudConnectionStringBuilder;
import com.gurock.smartinspect.packets.controlcommand.ControlCommandEvent;
import com.gurock.smartinspect.packets.logentry.LogEntryEvent;
import com.gurock.smartinspect.packets.processflow.ProcessFlowEvent;
import com.gurock.smartinspect.packets.watch.WatchEvent;
import com.gurock.smartinspect.session.Session;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnProperty(prefix = "smartinspect", name = "enabled", havingValue = "true")
@EnableConfigurationProperties(SmartInspectProperties.class)
public class SmartInspectAutoConfiguration {
    java.util.logging.Logger logger = java.util.logging.Logger.getLogger(SmartInspectAutoConfiguration.class.getName());

    private final SmartInspectProperties properties;

    public SmartInspectAutoConfiguration(SmartInspectProperties properties) {
        this.properties = properties;
    }

    @Bean
    public SmartInspect smartInspect() throws InvalidConnectionsException {
        SmartInspect smartInspect = new SmartInspect(properties.getAppId());
        smartInspect.addListener(
                new SmartInspectListener() {
                    @Override
                    public void onError(ErrorEvent e) {
                        logger.severe(e.getException().getMessage());
                    }

                    @Override
                    public void onControlCommand(ControlCommandEvent e) {

                    }

                    @Override
                    public void onLogEntry(LogEntryEvent e) {

                    }

                    @Override
                    public void onProcessFlow(ProcessFlowEvent e) {

                    }

                    @Override
                    public void onWatch(WatchEvent e) {

                    }

                    @Override
                    public void onFilter(FilterEvent e) {

                    }
                }
        );

        String connectionString = (new CloudConnectionStringBuilder().addCloudProtocol()
                .setRegion(properties.getRegion())
                .setWriteKey(properties.getWriteKey())).and().build();

        smartInspect.setConnections(connectionString);

        smartInspect.setEnabled(true);

        return smartInspect;
    }

    @Bean
    public Session session(SmartInspect smartInspect) throws InvalidConnectionsException {
        return smartInspect.addSession("cloud", true);
    }
}
