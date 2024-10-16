package com.example.wavify_demo;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

import org.apache.commons.io.FileUtils;

public class Utils {

    private Context context;

    public Utils(Context context) {
        this.context = context;
    }

    public void copyAssetsToInternalStorage() throws IOException {
        String[] files = context.getAssets().list("");

        if (files != null) {
            for (String filename : files) {
                if (Objects.equals(filename, "images") || Objects.equals(filename, "webkit") || Objects.equals(filename, "geoid_height_map") ) {
                    continue;
                }
                InputStream in = context.getAssets().open(filename);
                File outFile = new File(context.getFilesDir(), filename);
                OutputStream out = new FileOutputStream(outFile);
                FileUtils.copyInputStreamToFile(in, outFile);
                in.close();
                out.close();
            }
        }
    }


}
