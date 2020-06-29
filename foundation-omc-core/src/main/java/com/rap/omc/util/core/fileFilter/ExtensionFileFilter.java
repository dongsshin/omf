package com.rap.omc.util.core.fileFilter;

import java.io.File;
import java.io.FileFilter;


public class ExtensionFileFilter implements FileFilter{
    private String[] extensions = null;
    
    public ExtensionFileFilter(String extension)
    {
        this.extensions = extension.split(";");
    }
    public boolean accept(File file)
    {
        if ((file == null) || (!(file.isFile()))) return false;
        for (int idx = 0; idx < this.extensions.length; ++idx) {
            if (file.getName().toLowerCase().endsWith("." + this.extensions[idx].toLowerCase())) return true;
        }
        return false;
    }
}
