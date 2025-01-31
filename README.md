App Version Management System

Every smartphone user these days has lots of apps installed in their smartphones. These apps follow multiple iterations in its lifecycle which can range from fresh installs to updates of an existing app.

Install is done for cases where the phone doesn't have the app previously installed.
Updates are triggered when the app is preinstalled but a new feature is rolled out.

Design an app version management system for a mobile application, say PhonePe app.

We will assume that there is no marketplace like Playstore / AppStore exists and every App Owner can directly interact with the target device irrespective of operating system ( android / iOS )

To install any app, consumer can go to the website and directly install the app through online installer ( we’ll assume, this is something which is implemented )

Real world example -

Install - Consumer just bought his / her first Mobile device and wants to use PhonePe. In such case a fresh Install will happen - always latest version supported will be installed
Consumer goes to phonepe website and selects install option given on website ( how it happens is outside the scope of problem statement )

Update- Above customer has installed the app and a few days later a new feature ( say dark mode ) is rolled out by PhonePe. In such cases, PhonePe will directly update the app on the phone.

System Components -

App and App versions -
App will have a list of versions, each version denoting a new file and metadata.
Version will have some meta data associated with it, like the minimum supported operating system ( android / iOS ) version etc.
Roll out :: App admin can roll-out a new version from the backend. A roll-out can be either installing or updating the app -
install - App is to be installed fresh in the device. We will install the app in the smartphone
Update - takes a diff of installed version vs latest version and install the diff ( details in requirements part )
Rollout strategy - New version rollout can be done with different strategies.
Beta rollout - roll out the app version only on specific devices
Percentage rollout - roll out the app version on some percent on devices

**Project Structure**
The project follows a layered architecture:
└── com
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