package com.codepartners.smartinspect;

import com.gurock.smartinspect.connections.builder.CloudProtocolConnectionStringBuilder;

@FunctionalInterface
public interface SmartInspectCloudProtocolConnectionStringBuilderCustomizer {
    void customize(CloudProtocolConnectionStringBuilder builder);
}