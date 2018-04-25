package com.android.ddmlib;

public class CalabashDevice {

    private Device device;
    public CalabashDevice(String serial) {
        device = new Device(null, serial, null);
    }

    public RawImage getScreenshot() throws Exception {
        return CalabashAdbHelper.getFrameBuffer(device);
    }
}
