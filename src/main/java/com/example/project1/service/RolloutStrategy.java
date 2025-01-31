package com.example.project1.service;

import com.example.project1.domain.App;
import com.example.project1.domain.Device;
import java.util.List;

public interface RolloutStrategy {
    void rollout(App app, String versionId, List<Device> devices);
}
