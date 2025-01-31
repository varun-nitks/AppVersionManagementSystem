package com.example.project1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.project1.controller.*;
import com.example.project1.domain.*;
import com.example.project1.repository.*;
import com.example.project1.service.*;
import java.util.Set;

@SpringBootApplication
public class Project1Application {

    public static void main(String[] args) {
        AppRepository appRepository = new AppRepository();
        DeviceRepository deviceRepository = new DeviceRepository();

        // Initialization
        AppVersionService appVersionService = new AppVersionService(appRepository, deviceRepository);
        AppVersionController controller = new AppVersionController(appVersionService);

        // Create an app
        App phonePeApp = new App("phonepe", "PhonePe");
        appRepository.save(phonePeApp);

        // Upload new versions
        controller.uploadNewVersion("phonepe", "1.0.0", "file_content_1".getBytes(), "10.0");
        controller.uploadNewVersion("phonepe", "1.1.0", "file_content_2".getBytes(), "10.0");

        // Register a device
        Device device1 = new Device("device1", "10.0");
        deviceRepository.save(device1);
        Device device2 = new Device("device2", "10.0");
        deviceRepository.save(device2);

        // Check for install
        System.out.println(controller.checkForInstall("phonepe", "10.0"));

        // Execute install task
        controller.executeTask("phonepe", "device1", "install");

        // Check for updates
        System.out.println(controller.checkForUpdates("phonepe", "device1")); // null (already latest)

        // Create a beta rollout strategy for specific devices
        Set<String> betaDevices = Set.of("device1", "device2");

        // Upload a new version and release with percentage rollout
        controller.uploadNewVersion("phonepe", "1.2.0", "file_content_3".getBytes(), "10.0");
        RolloutStrategy percentageRollout = new PercentageRolloutStrategy(50);
        RolloutStrategy betaRollout = new BetaRolloutStrategy(betaDevices);
        controller.releaseVersion("phonepe", "1.2.0", betaRollout);
        controller.releaseVersion("phonepe", "1.2.0", percentageRollout);

        // Check for updates again
        System.out.println(controller.checkForUpdates("phonepe", "device1"));

        controller.uploadNewVersion("phonepe", "1.3.0", "file_content_3".getBytes(), "10.0");
        System.out.println(controller.checkForUpdates("phonepe", "device1")); // 1.2.0 (if selected in rollout)
    }
}
