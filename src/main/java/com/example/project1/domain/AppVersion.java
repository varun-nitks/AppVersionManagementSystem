package com.example.project1.domain;

public class AppVersion {
    private String versionId;
    private byte[] fileContent;
    private String minOsVersion;

    public AppVersion(String versionId, byte[] fileContent, String minOsVersion) {
        this.versionId = versionId;
        this.fileContent = fileContent;
        this.minOsVersion = minOsVersion;
    }

    public String getVersionId() {
        return versionId;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public String getMinOsVersion() {
        return minOsVersion;
    }
}
