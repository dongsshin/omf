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
/*     */ import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
/*     */ import org.apache.commons.compress.utils.IOUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;


public class TarArchive implements Archive
{
    private static final Logger LOGGER = LoggerFactory.getLogger(TarArchive.class);
    
    
    public File archive(File inputFile, File archivedFile, String encodingType)
    {
        File resultFile = null;
        
        OutputStream outputStream = null;
        ArchiveStreamFactory archiveStreamFactory = null;
        ArchiveOutputStream archiveOutputStream = null;
        try
        {
            archiveStreamFactory = new ArchiveStreamFactory();
            archiveStreamFactory.setEntryEncoding(encodingType);
            
            outputStream = new FileOutputStream(archivedFile);
            archiveOutputStream = archiveStreamFactory.createArchiveOutputStream("tar", outputStream);
            
            addFileToArchive(archiveOutputStream, inputFile, "");
            
            resultFile = archivedFile;
            }
        catch (FileNotFoundException e) {
            throw new IllegalStateException("The file cannot be found. : " + archivedFile.getAbsolutePath(),
                    e);
            } catch (ArchiveException e) {
            throw new IllegalStateException("Fail to archive the file.", e);
            }
        finally {
            try {
                if (archiveOutputStream != null) {
                    archiveOutputStream.finish();
                    archiveOutputStream.close();
                    }
                
                if (outputStream != null)/*  84 */ outputStream.close();
                }
            catch (IOException e)
            {
                LOGGER.error("An I/O error has occurred : " + e);
                }
            }
        
        return resultFile;
        }
    
    public void addFileToArchive(ArchiveOutputStream archiveOutputStream, File inputFile, String base)
  {
    FileInputStream fInputStream = null;
    try {
      String entryName = base + inputFile.getName();
      TarArchiveEntry entry = new TarArchiveEntry(inputFile, entryName);
      archiveOutputStream.putArchiveEntry(entry);

      if (inputFile.isFile()) {
        fInputStream = new FileInputStream(inputFile);
        IOUtils.copy(fInputStream, archiveOutputStream);
        archiveOutputStream.closeArchiveEntry();
	      return;
        }
      archiveOutputStream.closeArchiveEntry();
      File[] children = inputFile.listFiles();

      if (children == null) {
	                 for (File child : children) {
		         addFileToArchive(archiveOutputStream, child, entryName + "/");
		       }
                }
    }
    catch (IOException e)
    {
      LOGGER.error("An I/O error has occurred : " + e);
    } finally {
      try {
        if (fInputStream != null)
          fInputStream.close();
      }
      catch (IOException e) {
        LOGGER.error("An I/O error has occurred : " + e);
      }
    }
  }

    
    public File archive(File[] inputFiles, File compressedFile, String encodingType)
    {
        File resultFile = null;
        OutputStream outputStream = null;
        ArchiveOutputStream archiveOutputStream = null;
        try
        {
            outputStream = new FileOutputStream(compressedFile);
            
            ArchiveStreamFactory archiveStreamFactory = new ArchiveStreamFactory();
            archiveStreamFactory.setEntryEncoding(encodingType);
            
            archiveOutputStream = archiveStreamFactory.createArchiveOutputStream("tar", outputStream);
            
            for (int inx = 0; inx < inputFiles.length; ++inx) {
                TarArchiveEntry entry = new TarArchiveEntry(inputFiles[inx].getName());
                entry.setSize(inputFiles[inx].length());
                archiveOutputStream.putArchiveEntry(entry);
                IOUtils.copy(new FileInputStream(inputFiles[inx]), archiveOutputStream);
                archiveOutputStream.closeArchiveEntry();
                }
            resultFile = compressedFile;
            }
        catch (IOException e) {
            LOGGER.error("An I/O error has occurred : " + e);
        }
        catch (ArchiveException e) {
            throw new IllegalStateException("Fail to archive the file.", e);
            }
        finally {
            try {
                if (archiveOutputStream != null) {
                    archiveOutputStream.finish();
                    archiveOutputStream.close();
                    }
                
                if (outputStream != null) outputStream.close();
                }
            catch (IOException e)
            {
                LOGGER.error("An I/O error has occurred : " + e);
                }
            }
        return resultFile;
        }

    
    public File[] unarchive(File compressedFile, File destinationDirectory, String encodingType)
    {
        File[] resultList = null;
        InputStream inputStream = null;
        ArchiveInputStream archiveInputStream = null;
        FileOutputStream outputStream = null;
        try
        {
            inputStream = new FileInputStream(compressedFile);
            ArchiveStreamFactory archiveStreamFactory = new ArchiveStreamFactory();
            archiveStreamFactory.setEntryEncoding(encodingType);
            
            archiveInputStream = archiveStreamFactory.createArchiveInputStream("tar", inputStream);
            
            int bufferSize = 65536;
            TarArchiveEntry entry = null;
            while ((entry = (TarArchiveEntry)archiveInputStream.getNextEntry()) != null) {
                String name = entry.getName();
                name = name.replace('\\', '/');
                File destinationFile = new File(destinationDirectory, name);
                
                if (!(name.endsWith("/"))) {
                    File parentFolder = destinationFile.getParentFile();
                    if (!(parentFolder.exists())) {
                        parentFolder.mkdirs();
                        }
                    outputStream = new FileOutputStream(destinationFile);
                    IOUtils.copy(inputStream, outputStream, bufferSize);
                    }
                }
            resultList = destinationDirectory.listFiles();
            } catch (ArchiveException e) {
            throw new IllegalStateException("Fail to unarchive the files.", e);
            } catch (IOException e) {
            LOGGER.error("An I/O error has occurred : " + e);
            } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                    }
                if (archiveInputStream != null) {
                    archiveInputStream.close();
                    }
                if (inputStream != null)/* 244 */ inputStream.close();
                }
            catch (IOException e) {
                LOGGER.error("An I/O error has occurred : " + e);
                }
            }
        return resultList;
        }
    }
