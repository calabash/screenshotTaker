#! /usr/bin/env bash

set -e

if adb devices | grep -q "emulator"; then
    echo "Emulator is running"
else
    echo "Starting emulator..."
    emulator -avd calabash_test22 | echo -ne '\n' &
    adb wait-for-device
fi