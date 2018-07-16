Build
=====

| master  |
|---------|
|[![Build Status](http://calabash-ci.xyz:8081/job/screenshotTaker/job/master/badge/icon)](http://calabash-ci.xyz:8081/job/screenshotTaker/job/master/)|
|[![Build Status](https://travis-ci.org/calabash/screenshotTaker.svg?branch=master)](https://travis-ci.org/calabash/screenshotTaker)|


To build the jar run:

    ./gradlew build

Running
=======
To take a screen shot on the device with serial number `<serial number>` and save it to `<screenshot path>`

    java -jar screenshotTaker.jar <serial number> <screenshot path>
    
Testing
=======
To run integration tests

    ./gradlew integrationTest -PdeviceSerial=<serial number>
    
Make sure that environment variable `ANDROID_HOME` is set to proper Android SDK location before running integration tests.
