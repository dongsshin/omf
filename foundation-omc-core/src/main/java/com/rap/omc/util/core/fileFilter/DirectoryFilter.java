package com.rap.omc.util.core.fileFilter;

import java.io.File;
import java.io.FileFilter;

public class DirectoryFilter implements FileFilter
{
    private final boolean hidden;
    public DirectoryFilter(boolean includeHidden)
    {
        this.hidden = includeHidden;
    }
    public DirectoryFilter()
    {
        this.hidden = true;
    }
    public boolean accept(File file)
    {
        return (((file.isDirectory()) && (!(file.isHidden()))) ? true
                : (this.hidden) ? file.isDirectory() : false);
    }
}
