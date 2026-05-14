# TUI Mobile Automation Framework created by Akhil Reddy


This is an enterprise-level mobile automation framework using Java, Appium 2.x, Maven, TestNG, and Cucumber BDD for automating Android and iOS applications.

1.Tech Stack
- Java 17
- Appium 2.x with UiAutomator2 (Android) and XCUITest (iOS)
- Maven
- TestNG
- Cucumber BDD
- Extent Reports
- Log4j2

2.Prerequisites
- Java 17
- Maven
- Android Studio (for Android)
- Xcode (for iOS)
- Appium Server
- Node.js

Setup

Appium Setup
```bash
npm install -g appium
appium driver install uiautomator2
appium driver install xcuitest  # For iOS support
```

Android Studio Setup
- Install Android Studio and SDK
- Create emulator or connect real device

iOS Setup
- Install Xcode
- Create simulator or connect real device
- Ensure Xcode command line tools are installed

Real Device Setup
- Enable USB debugging (Android)
- Trust developer on device (iOS)
- Connect device via USB

BrowserStack Setup
- Create BrowserStack account
- Get user key and access key
- Upload app to BrowserStack

Configuration
To switch between platforms, update `src/test/resources/config/config.properties`:
- For Android: `platform=android`
- For iOS: `platform=ios`

Ensure the corresponding app file (.apk for Android, .app or .ipa for iOS) is placed in `src/test/resources/apps/`.

Execution Commands

 Local Emulator/Simulator
```bash
mvn clean test
```

### BrowserStack Cloud
```bash
mvn clean test -Dexecution=browserstack
```

### Smoke Tests
```bash
mvn test -Dcucumber.filter.tags="@smoke"
```

## Reporting
- Cucumber HTML Reports: `target/cucumber-reports`
- Extent Reports: `target/extent-reports`
- Screenshots on failure: `src/test/resources/reports/screenshots`

## Framework Structure
```
src/test/java
├── driver
│   └── DriverFactory.java
├── pages
│   ├── BasePage.java
│   ├── LoginPage.java
│   └── HomePage.java
├── stepdefinitions
│   ├── Hooks.java
│   ├── LoginSteps.java
│   └── HomeSteps.java
├── runners
│   └── TestRunner.java
└── utils
    ├── ConfigReader.java
    ├── JsonUtils.java
    ├── WaitUtils.java
    ├── GestureUtils.java
    ├── ScreenshotUtils.java
    └── RetryAnalyzer.java

src/test/resources
├── features
│   ├── login.feature
│   └── home.feature
├── testdata
│   └── testdata.json
├── config
│   └── config.properties
└── reports
```

## CI/CD
GitHub Actions configured for automated testing on push/PR to main branch.