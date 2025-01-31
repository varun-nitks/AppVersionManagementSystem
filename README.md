App Version Management System

Overview

Every smartphone user has multiple apps installed on their devices. These apps go through multiple iterations in their lifecycle, ranging from fresh installs to updates of an existing app. This system provides a way for app owners to manage app versions and directly roll out updates to user devices without relying on a marketplace like the Play Store or App Store.

Real-World Example

Install: A user buys a new smartphone and wants to install the PhonePe app for the first time. The latest supported version is installed.

Update: A user has the PhonePe app installed. A new feature, such as dark mode, is rolled out. The app is updated directly on the user's phone.

Features

Install a fresh app if it is not already installed.

Update an existing app by comparing the installed version with the latest version.

Store metadata for each app version, including minimum supported OS version.

Roll out new versions with different strategies:

Beta Rollout: Deploys the new version to specific devices.

Percentage Rollout: Deploys the new version to a percentage of devices.

Project Structure

The project follows a layered architecture:
com
└── example
    ├── domain
    │   ├── AppVersion.java       # Represents an app version
    │   ├── App.java              # Represents an app with multiple versions
    │   └── Device.java           # Represents a device with installed apps
    ├── repository
    │   ├── AppRepository.java    # Manages app data
    │   └── DeviceRepository.java # Manages device data
    ├── service
    │   ├── AppVersionService.java # Core business logic
    │   ├── RolloutStrategy.java   # Interface for rollout strategies
    │   ├── BetaRolloutStrategy.java # Beta rollout implementation
    │   └── PercentageRolloutStrategy.java # Percentage rollout implementation
    ├── controller
    │   └── AppVersionController.java # Handles user interaction
    └── Main.java                 # Entry point of the application

