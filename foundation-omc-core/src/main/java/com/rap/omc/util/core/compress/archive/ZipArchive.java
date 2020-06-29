package com.rap.omc.util.core.compress.archive;

import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import org.apache.commons.compress.archivers.ArchiveException;
/*     */ import org.apache.commons.compress.archivers.ArchiveInputStream;
/*     */ import org.apache.commons.compress.archivers.ArchiveOutputStream;
/*     */ import org.apache.commons.compress.archivers.ArchiveStreamFactory;
/*     */ import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
/*     */ import org.apache.commons.compress.utils.IOUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;

/*     */
/*     */ public class ZipArchive/*     */ implements Archive
/*     */ {

    /*  43 */ private static final Logger LOGGER = LoggerFactory.getLogger(ZipArchive.class);

    /*     */
    /*     */ public File archive(File inputFile, File archivedFile, String encodingType)
    /*     */ {
        /*  56 */ File resultFile = null;
        /*     */
        /*  58 */ OutputStream outputStream = null;
        /*  59 */ ArchiveStreamFactory archiveStreamFactory = null;
        /*  60 */ ArchiveOutputStream archiveOutputStream = null;
        /*     */ try
        /*     */ {
            /*  63 */ archiveStreamFactory = new ArchiveStreamFactory();
            archiveStreamFactory.setEntryEncoding(encodingType);
            /*     */
            /*  65 */ outputStream = new FileOutputStream(archivedFile);
            /*  66 */ archiveOutputStream = archiveStreamFactory.createArchiveOutputStream("zip", outputStream);
            /*     */
            /*  68 */ addFileToArchive(archiveOutputStream, inputFile, "");
            /*     */
            /*  70 */ resultFile = archivedFile;
            /*     */ }
        /*     */ catch (FileNotFoundException e) {
            /*  73 */ throw new IllegalStateException("The file cannot be found. : " + archivedFile.getAbsolutePath(),
                    e);
            /*     */ } catch (ArchiveException e) {
            /*  75 */ throw new IllegalStateException("Fail to archive the file.", e);
            /*     */ } finally {
            /*     */ try {
                /*  78 */ archiveOutputStream.finish();
                /*  79 */ archiveOutputStream.close();
                /*     */ } catch (IOException e) {
                /*  81 */ LOGGER.error("An I/O error has occurred : " + e);
                /*     */ }
            /*     */ try {
                /*  84 */ outputStream.close();
                /*     */ } catch (IOException e) {
                /*  86 */ LOGGER.error("An I/O error has occurred : " + e);
                /*     */ }
            /*     */ }
        /*  89 */ return resultFile;
        /*     */ }

    /*     */
    /*     */ public void addFileToArchive(ArchiveOutputStream archiveOutputStream, File inputFile, String base)
    /*     */ {
        /*     */ try
        /*     */ {
            /* 102 */ String entryName = base + inputFile.getName();
            /* 103 */ ZipArchiveEntry entry = new ZipArchiveEntry(inputFile, entryName);
            /* 104 */ archiveOutputStream.putArchiveEntry(entry);
            /*     */
            /* 106 */ if (inputFile.isFile()) {
                /* 107 */ FileInputStream fInputStream = null;
                /* 108 */ fInputStream = new FileInputStream(inputFile);
                /* 109 */ IOUtils.copy(fInputStream, archiveOutputStream);
                /* 110 */ archiveOutputStream.closeArchiveEntry();
                /*     */
                /* 112 */ return;
            }
            /* 113 */ archiveOutputStream.closeArchiveEntry();
            /* 114 */ File[] children = inputFile.listFiles();
            /*     */
            /* 116 */ if (children != null) {
                /* 117 */ for (File child : children)
                    /* 118 */ addFileToArchive(archiveOutputStream, child, entryName + "/");
                /*     */ }
            /*     */ }
        /*     */ catch (IOException e)
        /*     */ {
            /* 123 */ LOGGER.error("An I/O error has occurred : " + e);
            /*     */ }
        /*     */ }

    /*     */
    /*     */ public File archive(File[] inputFiles, File compressedFile, String encodingType)
    /*     */ {
        /* 138 */ File resultFile = null;
        /* 139 */ OutputStream outputStream = null;
        /* 140 */ ArchiveOutputStream archiveOutputStream = null;
        /*     */ try
        /*     */ {
            /* 143 */ outputStream = new FileOutputStream(compressedFile);
            /*     */
            /* 145 */ ArchiveStreamFactory archiveStreamFactory = new ArchiveStreamFactory();
            archiveStreamFactory.setEntryEncoding(encodingType);
            /*     */
            /* 147 */ archiveOutputStream = archiveStreamFactory.createArchiveOutputStream("zip", outputStream);
            /*     */
            /* 149 */ for (int inx = 0; inx < inputFiles.length; ++inx) {
                /* 150 */ ZipArchiveEntry entry = new ZipArchiveEntry(inputFiles[inx].getName());
                /* 151 */ entry.setSize(inputFiles[inx].length());
                /* 152 */ archiveOutputStream.putArchiveEntry(entry);
                /* 153 */ IOUtils.copy(new FileInputStream(inputFiles[inx]), archiveOutputStream);
                /* 154 */ archiveOutputStream.closeArchiveEntry();
                /*     */ }
            /* 156 */ resultFile = compressedFile;
            /*     */ } catch (IOException e) {
            /* 158 */ LOGGER.error("An I/O error has occurred : " + e);
            /*     */ }
        /*     */ catch (ArchiveException e) {
            /* 161 */ throw new IllegalStateException("Fail to archive the files.", e);
            /*     */ }
        /*     */ finally {
            /*     */ try {
                /* 165 */ if (archiveOutputStream != null) {
                    /* 166 */ archiveOutputStream.finish();
                    /* 167 */ archiveOutputStream.close();
                    /*     */ }
                /*     */
                /* 170 */ if (outputStream != null)/* 171 */ outputStream.close();
                /*     */ }
            /*     */ catch (IOException e)
            /*     */ {
                /* 175 */ LOGGER.error("An I/O error has occurred : " + e);
                /*     */ }
            /*     */ }
        /* 178 */ return resultFile;
        /*     */ }

    /*     */
    /*     */ public File[] unarchive(File compressedFile, File destinationDirectory, String encodingType)
    /*     */ {
        /* 191 */ File[] resultList = null;
        /* 192 */ InputStream inputStream = null;
        /* 193 */ ArchiveInputStream archiveInputStream = null;
        /* 194 */ OutputStream outputStream = null;
        /*     */ try
        /*     */ {
            /* 197 */ inputStream = new FileInputStream(compressedFile);
            /* 198 */ ArchiveStreamFactory archiveStreamFactory = new ArchiveStreamFactory();
            archiveStreamFactory.setEntryEncoding(encodingType);
            /* 199 */ int bufferSize = 65536;
            /*     */
            /* 201 */ archiveInputStream = archiveStreamFactory.createArchiveInputStream("zip", inputStream);
            /* 202 */ ZipArchiveEntry entry = null;
            /* 203 */ while ((entry = (ZipArchiveEntry)archiveInputStream.getNextEntry()) != null) {
                /* 204 */ String name = entry.getName();
                /* 205 */ name = name.replace('\\', '/');
                /*     */
                /* 207 */ File destinationFile = new File(destinationDirectory, name);
                /* 208 */ if (!(name.endsWith("/"))) {
                    /* 209 */ File parentFolder = destinationFile.getParentFile();
                    /* 210 */ if (!(parentFolder.exists())) {
                        /* 211 */ parentFolder.mkdirs();
                        /*     */ }
                    /* 213 */ outputStream = new FileOutputStream(destinationFile);
                    /*     */ }
                /* 215 */ IOUtils.copy(archiveInputStream, outputStream, bufferSize);
                /*     */ }
            /* 217 */ resultList = destinationDirectory.listFiles();
            /*     */ }
        /*     */ catch (Exception ex) {
            /* 220 */ throw new IllegalStateException("Fail to unarchive the zip file.", ex);
            /*     */ } finally {
            /*     */ try {
                /* 223 */ if (outputStream != null) {
                    /* 224 */ outputStream.close();
                    /*     */ }
                /* 226 */ if (archiveInputStream != null) {
                    /* 227 */ archiveInputStream.close();
                    /*     */ }
                /* 229 */ if (inputStream != null)/* 230 */ inputStream.close();
                /*     */ }
            /*     */ catch (IOException e) {
                /* 233 */ LOGGER.error("An I/O error has occurred : " + e);
                /*     */ }
            /*     */ }
        /*     */
        /* 237 */ return resultList;
        /*     */ }
    /*     */ }