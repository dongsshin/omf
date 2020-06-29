/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : GpdmFileDownloadView.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 3. 11.  jongjung.kwon   Initial
 * ===========================================
 */
package com.rap.omc.framework.file.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

import com.rap.omc.util.core.FileUtil;




/**
 * <pre>
 * Class : GpdmFileDownloadView
 * Description : TODO
 * </pre>
 * 
 * @author jongjung.kwon
 */
public class PdmFileDownloadView extends FileDownloadView {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PdmFileDownloadView.class);
  
    private HashMap<String, String> fileReplaceMapper;

    public PdmFileDownloadView(String path)
    {
      this(new File(path));
    }

    public PdmFileDownloadView(File file)
    {
      this(file, file.getName());
    }

    public PdmFileDownloadView(String path, String fileAlias)
    {
      this(new File(path), fileAlias);
    }

    public PdmFileDownloadView(File file, String fileAlias)
    {
      this(new File[] { file }, fileAlias);
    }

    public PdmFileDownloadView(String[] filepaths, String fileAlias)
    {
      this(FileUtil.convertToFileArray(filepaths), fileAlias);
    }

    public PdmFileDownloadView(String[] filepaths, String fileAlias, String zipFileEncoding)
    {
      this(FileUtil.convertToFileArray(filepaths), fileAlias);
      super.zipFileEncoding = zipFileEncoding;
    }

    public PdmFileDownloadView(File[] files, String fileAlias, String zipFileEncoding)
    {
      super(files, fileAlias);
      super.zipFileEncoding = zipFileEncoding;
    }

    public PdmFileDownloadView(File[] files, String fileAlias)
    {
      
        super(files, fileAlias);
        
//      this.fileAlias = "";
//
//      this.encoding = "utf-8";
//
//      this.zipFileEncoding = "EUC-KR";
//
//      this.contentType = "application/octet-stream";
//
//      this.directOpen = false;
//
//      this.files = files;
//      for (int i = 0; i < this.files.length; ++i) {
//        if (this.files[i].exists()) continue; throw new FileDownloadException("DSOWEB002", "Download file not found : " + this.files[i].getAbsolutePath());
//      }
//      this.fileAlias = fileAlias;
      

    }
    public PdmFileDownloadView(File[] files, String fileAlias, HashMap<String,String> fileReplaceMapper )
    {
      
        super(files, fileAlias);
        this.fileReplaceMapper = fileReplaceMapper;

    } 
    
    public void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
            throws Exception
          {
            super.setContentType(this.contentType);
            String rootPath = "/";
            if ((((this.files.length > 1) || ((this.files.length == 1) && (this.files[0].isDirectory()) && (!(this.files[0].equals(new File(rootPath))))))) && 
              (!(this.fileAlias.toLowerCase().endsWith("zip"))) && (!(this.fileAlias.toLowerCase().endsWith("jar")))) {
              this.fileAlias += ".zip";
            }

            setResponseHeaders(response);
            setResponseHeadersForMulitBrowser(request, response);

            renderBodyContentsToResponse(model, request, response);

            if (response.getOutputStream() != null){
              response.getOutputStream().flush();
            }
          }

          protected void renderBodyContentsToResponse(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
          {
            if (this.files.length == 1) {
              if (this.files[0].isFile()){
                writeSingleFileToOutputStream(this.files[0], response);
              }else {
                writeToZipOutputStream(new File[] { this.files[0] }, response);
              }
            }
            else{
              writeToZipOutputStream(this.files, response);
            }
          }

          private void writeSingleFileToOutputStream(File file, HttpServletResponse response)
          {
            FileInputStream fileInputStream = null;
            try {
              fileInputStream = new FileInputStream(file);
              FileCopyUtils.copy(fileInputStream, response.getOutputStream());
            }
            catch (Exception e) {
            }
            finally {
              FileUtil.close(fileInputStream);
            }
          }

          private void writeToZipOutputStream(File[] files, HttpServletResponse response)
          {
            ArchiveStreamFactory archiveStreamFactory = null;
            ArchiveOutputStream archiveOutputStream = null;
            try {
              archiveStreamFactory = new ArchiveStreamFactory();
              archiveStreamFactory.setEntryEncoding(this.zipFileEncoding);
              archiveOutputStream = archiveStreamFactory.createArchiveOutputStream("zip", response.getOutputStream());
              for (int i = 0; i < files.length; ++i) {
                if (files[i].isFile()) {
                  addFile(archiveOutputStream, files[i], null);
                }
                else
                {
                  File[] children = files[i].listFiles();
                  for (int j = 0; j < children.length; ++j) {
                    if (children[j].isFile()){
                      addFile(archiveOutputStream, children[j], files[i].getName());
                    }
                  }
                }
              }
              archiveOutputStream.flush();
            }
            catch (Exception e) {
            }
            finally {
              if (archiveOutputStream != null) {
                try { archiveOutputStream.finish(); } catch (IOException e) { LOGGER.error("ArchiveOutputStream finish fail!", e); }
                try { archiveOutputStream.close(); } catch (IOException e) { LOGGER.error("ArchiveOutputStream close fail!", e);
                }
              }
            }
          }

          private void addFile(ArchiveOutputStream archiveOutputStream, File file, String parentDir)
            throws Exception
          {
            FileInputStream fInputStream = null;
            try {
              String realFileName = (String)fileReplaceMapper.get(file.getAbsolutePath());
              if (parentDir != null) {
                  ZipArchiveEntry entry = new ZipArchiveEntry(file, parentDir + "/" + realFileName);
//                ZipArchiveEntry entry = new ZipArchiveEntry(file, parentDir + "/" + file.getName());
                archiveOutputStream.putArchiveEntry(entry);
              } else {
                  ZipArchiveEntry entry = new ZipArchiveEntry(file, realFileName);
//                ZipArchiveEntry entry = new ZipArchiveEntry(file, file.getName());
                archiveOutputStream.putArchiveEntry(entry);
              }
              fInputStream = new FileInputStream(file);
              IOUtils.copy(fInputStream, archiveOutputStream);
            }
            finally {
              if (fInputStream != null) try { fInputStream.close(); } catch (Exception ex) { LOGGER.error("FileInputStream close fail!", ex); }
              if (archiveOutputStream != null) try { archiveOutputStream.closeArchiveEntry(); } catch (Exception ex) { LOGGER.error("ArchiveOutputStream closeArchiveEntry fail!", ex);
                }
            }
          }
    
    
}
