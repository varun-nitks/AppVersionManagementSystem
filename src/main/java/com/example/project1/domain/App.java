package com.example.project1.domain;

import java.util.*;

public class App {
    private String appId;
    private String name;
    private Map<String, AppVersion> versions;

    public App(String appId, String name) {
        this.appId = appId;
        this.name = name;
        this.versions = new HashMap<>();
    }

    public String getAppId() {
        return appId;
    }

    public Map<String, AppVersion> getVersions() {
        return versions;
    }

    public void addVersion(AppVersion version) {
        versions.put(version.getVersionId(), version);
    }

    public AppVersion getLatestVersion() {
        return versions.values().stream()
                .max(Comparator.comparing(AppVersion::getVersionId))
                .orElse(null);
    }
}
