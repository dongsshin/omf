package com.rap.omc.util.core.compress.compress;

import java.io.File;

public abstract interface Compress {

    public abstract File compress(File paramFile1, File paramFile2);

    public abstract File decompress(File paramFile1, File paramFile2);
}
