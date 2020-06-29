package com.rap.omc.util.core.compress.archive;

import java.io.File;
import org.apache.commons.compress.archivers.ArchiveOutputStream;

public abstract interface Archive {

    public abstract File archive(File paramFile1, File paramFile2, String paramString);

    public abstract void addFileToArchive(ArchiveOutputStream paramArchiveOutputStream, File paramFile,
            String paramString);

    public abstract File archive(File[] paramArrayOfFile, File paramFile, String paramString);

    public abstract File[] unarchive(File paramFile1, File paramFile2, String paramString);
}
