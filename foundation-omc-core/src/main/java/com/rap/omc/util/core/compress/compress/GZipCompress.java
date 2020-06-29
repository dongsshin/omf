package com.rap.omc.util.core.compress.compress;

import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import org.apache.commons.compress.compressors.CompressorException;
/*     */ import org.apache.commons.compress.compressors.CompressorInputStream;
/*     */ import org.apache.commons.compress.compressors.CompressorOutputStream;
/*     */ import org.apache.commons.compress.compressors.CompressorStreamFactory;
/*     */ import org.apache.commons.compress.utils.IOUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;

/*     */
/*     */ public class GZipCompress/*     */ implements Compress
/*     */ {

    /*  41 */ private static final Logger LOGGER = LoggerFactory.getLogger(GZipCompress.class);

    /*     */
    /*     */ public File compress(File archivedFile, File compressedFile)
    /*     */ {
        /*  53 */ OutputStream outputStream = null;
        /*  54 */ CompressorOutputStream compressorOutputStream = null;
        /*     */ try
        /*     */ {
            /*  57 */ outputStream = new FileOutputStream(compressedFile);
            /*  58 */ compressorOutputStream = new CompressorStreamFactory().createCompressorOutputStream("gz",
                    outputStream);
            /*  59 */ IOUtils.copy(new FileInputStream(archivedFile), compressorOutputStream);
            /*     */ }
        /*     */ catch (CompressorException e) {
            /*  62 */ throw new IllegalStateException("Fail to compress a file.", e);
            /*     */ }
        /*     */ catch (IOException e) {
            /*  65 */ LOGGER.error("An I/O error has occurred : " + e);
            /*     */ }
        /*     */ finally {
            /*     */ try {
                /*  69 */ if (compressorOutputStream != null) {
                    /*  70 */ compressorOutputStream.close();
                    /*     */ }
                /*     */
                /*  73 */ if (outputStream != null)/*  74 */ outputStream.close();
                /*     */ }
            /*     */ catch (IOException e)
            /*     */ {
                /*  78 */ LOGGER.error("An I/O error has occurred : " + e);
                /*     */ }
            /*     */ }
        /*  81 */ return compressedFile;
        /*     */ }

    /*     */
    /*     */ public File decompress(File compressedFile, File decompressedFile)
    /*     */ {
        /*  93 */ InputStream inputStream = null;
        /*  94 */ CompressorInputStream compressorInputStream = null;
        /*     */ try
        /*     */ {
            /*  97 */ inputStream = new FileInputStream(compressedFile);
            /*  98 */ compressorInputStream = new CompressorStreamFactory().createCompressorInputStream("gz",
                    inputStream);
            /*  99 */ IOUtils.copy(compressorInputStream, new FileOutputStream(decompressedFile));
            /*     */ }
        /*     */ catch (CompressorException e) {
            /* 102 */ throw new IllegalStateException("Fail to compress a file.", e);
            /*     */ } catch (IOException e) {
            /* 104 */ LOGGER.error("An I/O error has occurred : " + e);
            /*     */ } finally {
            /*     */ try {
                /* 107 */ if (compressorInputStream != null) {
                    /* 108 */ compressorInputStream.close();
                    /*     */ }
                /*     */
                /* 111 */ if (inputStream != null)/* 112 */ inputStream.close();
                /*     */ }
            /*     */ catch (IOException e)
            /*     */ {
                /* 116 */ LOGGER.error("An I/O error has occurred : " + e);
                /*     */ }
            /*     */ }
        /* 119 */ return decompressedFile;
        /*     */ }
    /*     */ }
