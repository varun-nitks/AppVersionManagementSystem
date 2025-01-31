package com.example.project1.controller;

import com.example.project1.service.*;
import com.example.project1.domain.*;

public class AppVersionController {
    private AppVersionService appVersionService;

    public AppVersionController(AppVersionService appVersionService) {
        this.appVersionService = appVersionService;
    }

    public void uploadNewVersion(String appId, String versionId, byte[] fileContent, String minOsVersion) {
        appVersionService.uploadNewVersion(appId, versionId, fileContent, minOsVersion);
    }

    public byte[] createUpdatePatch(String appId, String fromVersionId, String toVersionId) {
        return appVersionService.createUpdatePatch(appId, fromVersionId, toVersionId);
    }

    public void releaseVersion(String appId, String versionId, RolloutStrategy rolloutStrategy) {
        appVersionService.releaseVersion(appId, versionId, rolloutStrategy);
    }

    public boolean checkForInstall(String appId, String deviceOsVersion) {
        return appVersionService.checkForInstall(appId, deviceOsVersion);
    }

    public String checkForUpdates(String appId, String deviceId) {
        return appVersionService.checkForUpdates(appId, deviceId);
    }

    public void executeTask(String appId, String deviceId, String taskType) {
        appVersionService.executeTask(appId, deviceId, taskType);
    }
}
