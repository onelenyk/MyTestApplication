fastlane documentation
================
# Installation

Make sure you have the latest version of the Xcode command line tools installed:

```
xcode-select --install
```

Install _fastlane_ using
```
[sudo] gem install fastlane -NV
```
or alternatively using `brew install fastlane`

# Available Actions
## Android
### android buildAll
```
fastlane android buildAll
```
Build release and debug
### android buildR
```
fastlane android buildR
```
Build release: flavor - production, built_type - Release
### android buildD
```
fastlane android buildD
```
Build debug: flavor - development, built_type - Debug
### android copy
```
fastlane android copy
```
Copy build to

----

This README.md is auto-generated and will be re-generated every time [fastlane](https://fastlane.tools) is run.
More information about fastlane can be found on [fastlane.tools](https://fastlane.tools).
The documentation of fastlane can be found on [docs.fastlane.tools](https://docs.fastlane.tools).
