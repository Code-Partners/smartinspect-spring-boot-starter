package com.codepartners.smartinspect;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "smartinspect")
public class SmartInspectProperties {
    private boolean enabled;
    private String appId;
    private String region;
    private String writeKey;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getWriteKey() {
        return writeKey;
    }

    public void setWriteKey(String writeKey) {
        this.writeKey = writeKey;
    }

    @Override
    public String toString() {
        return "SmartInspectProperties{" +
                "enabled=" + enabled +
                ", appId='" + appId + '\'' +
                ", region='" + region + '\'' +
                ", writeKey='" + writeKey + '\'' +
                '}';
    }
}

