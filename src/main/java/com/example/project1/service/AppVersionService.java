package com.example.project1.service;

import com.example.project1.domain.*;
import com.example.project1.repository.AppRepository;
import com.example.project1.repository.DeviceRepository;
import java.util.*;

public class AppVersionService {
    private AppRepository appRepository;
    private DeviceRepository deviceRepository;

    public AppVersionService(AppRepository appRepository, DeviceRepository deviceRepository) {
        this.appRepository = appRepository;
        this.deviceRepository = deviceRepository;
    }

    public void uploadNewVersion(String appId, String versionId, byte[] fileContent, String minOsVersion) {
        App app = appRepository.findById(appId);
        if (app == null) {
            throw new IllegalArgumentException("App does not exist.");
        }
        app.addVersion(new AppVersion(versionId, fileContent, minOsVersion));
    }

    public byte[] createUpdatePatch(String appId, String fromVersionId, String toVersionId) {
        App app = appRepository.findById(appId);
        if (app == null) {
            throw new IllegalArgumentException("App does not exist.");
        }
        if (!app.getVersions().containsKey(fromVersionId) || !app.getVersions().containsKey(toVersionId)) {
            throw new IllegalArgumentException("Invalid version IDs.");
        }
        return "diff_pack".getBytes();
    }

    public void releaseVersion(String appId, String versionId, RolloutStrategy rolloutStrategy) {
        App app = appRepository.findById(appId);
        if (app == null) {
            throw new IllegalArgumentException("App does not exist.");
        }
        if (!app.getVersions().containsKey(versionId)) {
            throw new IllegalArgumentException("Version does not exist.");
        }
        List<Device> allDevices = deviceRepository.findAll();
        rolloutStrategy.rollout(app, versionId, allDevices);
    }

    public boolean isAppVersionSupported(String appId, String versionId, String deviceOsVersion) {
        App app = appRepository.findById(appId);
        if (app == null || !app.getVersions().containsKey(versionId)) {
            return false;
        }
        AppVersion version = app.getVersions().get(versionId);
        return deviceOsVersion.compareTo(version.getMinOsVersion()) >= 0;
    }

    public boolean checkForInstall(String appId, String deviceOsVersion) {
        App app = appRepository.findById(appId);
        if (app == null) {
            return false;
        }
        AppVersion latestVersion = app.getLatestVersion();
        return isAppVersionSupported(appId, latestVersion.getVersionId(), deviceOsVersion);
    }

    public String checkForUpdates(String appId, String deviceId) {
        Device device = deviceRepository.findById(deviceId);
        if (device == null) {
            return null;
        }
        String installedVersionId = device.getInstalledApps().get(appId);
        App app = appRepository.findById(appId);
        if (app == null) {
            return null;
        }
        AppVersion latestVersion = app.getLatestVersion();
        if (!installedVersionId.equals(latestVersion.getVersionId())) {
            return latestVersion.getVersionId();
        }
        return null;
    }

    public void executeTask(String appId, String deviceId, String taskType) {
        Device device = deviceRepository.findById(deviceId);
        App app = appRepository.findById(appId);
        if (device == null || app == null) {
            throw new IllegalArgumentException("Invalid app or device.");
        }
        AppVersion latestVersion = app.getLatestVersion();
        if (taskType.equals("install") || taskType.equals("update")) {
            device.installApp(appId, latestVersion.getVersionId());
        } else {
            throw new IllegalArgumentException("Invalid task type.");
        }
    }
}
