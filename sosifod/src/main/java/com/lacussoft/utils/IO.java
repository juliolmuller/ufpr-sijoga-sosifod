package com.lacussoft.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IO {

    private IO() {}

    public static boolean copyFile(String source, String dest) throws IOException {
        File sourceFile = new File(source);
        File destFile = new File(dest);

        return copyFile(sourceFile, destFile);
    }

    public static boolean copyFile(File source, File dest) throws IOException {
        if (!source.exists()) {
            return false;
        }

        try (InputStream in = new FileInputStream(source); OutputStream out = new FileOutputStream(dest)) {
            return copyFile(in, out);
        }
    }

    public static boolean copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int bytes;

        while ((bytes = in.read(buffer)) > 0) {
            out.write(buffer, 0, bytes);
        }

        return true;
    }

    public static boolean renameFile(String source, String dest) {
        File sourceFile = new File(source);
        File destFile = new File(dest);

        return renameFile(sourceFile, destFile);
    }

    private static boolean renameFile(File source, File dest) {
        if (!source.exists()) {
            return false;
        }

        return source.renameTo(dest);
    }

    public static boolean moveFile(String source, String dest) {
        return renameFile(source, dest);
    }

    public static boolean moveFile(File source, File dest) {
        return renameFile(source, dest);
    }
}
