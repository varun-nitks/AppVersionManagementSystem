package com.example.project1.service;

import com.example.project1.domain.App;
import com.example.project1.domain.Device;
import java.util.List;
import java.util.Set;

public class BetaRolloutStrategy implements RolloutStrategy {
    private Set<String> betaDevices;

    public BetaRolloutStrategy(Set<String> betaDevices) {
        this.betaDevices = betaDevices;
    }

    @Override
    public void rollout(App app, String versionId, List<Device> devices) {
        for (Device device : devices) {
            if (betaDevices.contains(device.getDeviceId())) {
                device.installApp(app.getAppId(), versionId);
            }
        }
    }
}
