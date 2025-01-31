package com.example.project1.domain;

import java.util.*;

public class Device {
    private String deviceId;
    private String osVersion;
    private Map<String, String> installedApps; // appId -> versionId

    public Device(String deviceId, String osVersion) {
        this.deviceId = deviceId;
        this.osVersion = osVersion;
        this.installedApps = new HashMap<>();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public Map<String, String> getInstalledApps() {
        return installedApps;
    }

    public void installApp(String appId, String versionId) {
        installedApps.put(appId, versionId);
    }
}
