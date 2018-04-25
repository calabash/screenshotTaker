/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sh.calaba.screenshot;

import com.android.ddmlib.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;

public class ScreenshotTaker {

    public static void main(String[] args) throws Exception {
        if (args.length !=2 ) {
            System.out.println("Usage: java -jar screenshotTaker.jar <serial number> <screenshot path>");
            System.exit(1);
        }

        try {
            takeScreenshot(args[0], args[1]);
        } catch (Exception e) {
            System.err.println("Could not take screenshot");
            e.printStackTrace();
        }
    }

    public static void takeScreenshot(String serial, String fileName) throws Exception {
        CalabashDevice device = new CalabashDevice(serial);
        RawImage rawImage = device.getScreenshot();
        saveImage(rawImage, fileName);
    }

    private static void saveImage(RawImage rawImage, String fileName) {
        PaletteData palette = new PaletteData(rawImage.getRedMask(), rawImage.getGreenMask(), rawImage.getBlueMask());

        ImageData imageData = new ImageData(rawImage.width, rawImage.height, rawImage.bpp, palette, 1, rawImage.data);
        try {
            org.eclipse.swt.graphics.ImageLoader loader = new org.eclipse.swt.graphics.ImageLoader();
            loader.data = new ImageData[] { imageData };
            loader.save(fileName, SWT.IMAGE_PNG);
        } catch (Exception e) {
            throw new RuntimeException("Unable to save '" + fileName + "'", e);
        }
    }
}
