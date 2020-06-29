package com.rap.omc.schema.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class OmcFileUtil {
    public static void copyFile(File source, File dest) throws IOException { 
        Files.copy(source.toPath(), dest.toPath()); 
    } 
    public static void copyFile(String sourceStr, String destStr) throws IOException { 
        File source = new File(sourceStr);
        File dest = new File(destStr);
        copyFile(source, dest); 
    } 
    public static void copyFile(String sourceStr, String destStr, boolean overWrite) throws IOException { 
        File source = new File(sourceStr);
        File dest = new File(destStr);
        if(overWrite && dest.exists()) {
            dest.delete();
        }
        copyFile(source, dest); 
    } 
}