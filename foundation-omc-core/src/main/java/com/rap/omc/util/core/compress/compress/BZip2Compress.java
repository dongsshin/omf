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
/*     */ public class BZip2Compress/*     */ implements Compress
/*     */ {

    /*  41 */ private static final Logger LOGGER = LoggerFactory.getLogger(BZip2Compress.class);

    /*     */
    /*     */ public File compress(File archivedFile, File compressedFile)
    /*     */ {
        /*  52 */ OutputStream outputStream = null;
        /*  53 */ CompressorOutputStream compressorOutputStream = null;
        /*     */ try
        /*     */ {
            /*  56 */ outputStream = new FileOutputStream(compressedFile);
            /*  57 */ compressorOutputStream = new CompressorStreamFactory().createCompressorOutputStream("bzip2",
                    outputStream);
            /*  58 */ IOUtils.copy(new FileInputStream(archivedFile), compressorOutputStream);
            /*     */ }
        /*     */ catch (CompressorException e) {
            /*  61 */ throw new IllegalStateException("Fail to compress a file.", e);
            /*     */ }
        /*     */ catch (IOException e) {
            /*  64 */ LOGGER.error("An I/O error has occurred : " + e);
            /*     */ }
        /*     */ finally {
            /*     */ try {
                /*  68 */ if (compressorOutputStream != null) {
                    /*  69 */ compressorOutputStream.close();
                    /*     */ }
                /*     */
                /*  72 */ if (outputStream != null)/*  73 */ outputStream.close();
                /*     */ }
            /*     */ catch (IOException e)
            /*     */ {
                /*  77 */ LOGGER.error("An I/O error has occurred : " + e);
                /*     */ }
            /*     */ }
        /*  80 */ return compressedFile;
        /*     */ }

    /*     */
    /*     */ public File decompress(File compressedFile, File decompressedFile)
    /*     */ {
        /*  92 */ InputStream inputStream = null;
        /*  93 */ CompressorInputStream compressorInputStream = null;
        /*     */ try
        /*     */ {
            /*  96 */ inputStream = new FileInputStream(compressedFile);
            /*  97 */ compressorInputStream = new CompressorStreamFactory().createCompressorInputStream("bzip2",
                    inputStream);
            /*  98 */ IOUtils.copy(compressorInputStream, new FileOutputStream(decompressedFile));
            /*     */ }
        /*     */ catch (CompressorException e) {
            /* 101 */ throw new IllegalStateException("Fail to compress a file.", e);
            /*     */ }
        /*     */ catch (IOException e) {
            /* 104 */ LOGGER.error("An I/O error has occurred : " + e);
            /*     */ }
        /*     */ finally {
            /*     */ try {
                /* 108 */ compressorInputStream.close();
                /*     */ } catch (IOException e) {
                /* 110 */ LOGGER.error("An I/O error has occurred : " + e);
                /*     */ }
            /*     */ try {
                /* 113 */ inputStream.close();
                /*     */ } catch (IOException e) {
                /* 115 */ LOGGER.error("An I/O error has occurred : " + e);
                /*     */ }
            /*     */ }
        /* 118 */ return decompressedFile;
        /*     */ }
    /*     */ }
