package com.example.project1.repository;

import com.example.project1.domain.Device;
import java.util.*;

public class DeviceRepository {
    private Map<String, Device> devices = new HashMap<>();

    public void save(Device device) {
        devices.put(device.getDeviceId(), device);
    }

    public Device findById(String deviceId) {
        return devices.get(deviceId);
    }

    public List<Device> findAll() {
        return new ArrayList<>(devices.values());
    }
}