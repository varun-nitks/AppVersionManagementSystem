package com.example.project1.service;

import com.example.project1.domain.App;
import com.example.project1.domain.Device;
import java.util.Collections;
import java.util.List;

public class PercentageRolloutStrategy implements RolloutStrategy {
    private int percentage;

    public PercentageRolloutStrategy(int percentage) {
        this.percentage = percentage;
    }

    @Override
    public void rollout(App app, String versionId, List<Device> devices) {
        int numDevices = (int) (devices.size() * (percentage / 100.0));
        Collections.shuffle(devices);
        for (int i = 0; i < numDevices; i++) {
            devices.get(i).installApp(app.getAppId(), versionId);
        }
    }
}
