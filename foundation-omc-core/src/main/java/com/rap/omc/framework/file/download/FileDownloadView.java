package com.rap.omc.framework.file.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import org.springframework.web.servlet.view.AbstractView;

import com.rap.omc.framework.file.upload.exception.FileDownloadException;
import com.rap.omc.util.core.FileUtil;

public class FileDownloadView extends AbstractView
{

    private static final Logger LOGGER = LoggerFactory.getLogger(FileDownloadView.class);

    public static final String DEFAULT_ENCODING = "utf-8";

    public static final String DEFAULT_ZIP_ENCODING = "EUC-KR";

    public static final String DEFAULT_CONTENT_TYPE = "application/octet-stream";

    public static final String CONTENT_DISPOSITION = "Content-Disposition";

    protected File[] files;

    protected String fileAlias;

    protected String encoding;

    protected String zipFileEncoding;

    protected String contentType;

    protected boolean directOpen;

    
    public FileDownloadView(String path)
    {
        this(new File(path));
    }


    public FileDownloadView(File file)
 	{
        this(file, file.getName());
    }


    public FileDownloadView(String path, String fileAlias)
    {
        this(new File(path), fileAlias);
    }


    public FileDownloadView(File file, String fileAlias)
    {
        this(new File[] { file }, fileAlias);
    }


    public FileDownloadView(String[] filepaths, String fileAlias)
    {
        this(FileUtil.convertToFileArray(filepaths), fileAlias);
    }


    public FileDownloadView(String[] filepaths, String fileAlias, String zipFileEncoding)
    {
        this(FileUtil.convertToFileArray(filepaths), fileAlias);
        this.zipFileEncoding = zipFileEncoding;
    }


    public FileDownloadView(File[] files, String fileAlias, String zipFileEncoding)
    {
        this(files, fileAlias);
        this.zipFileEncoding = zipFileEncoding;
    }


    public FileDownloadView(File[] files, String fileAlias)
    {
        this.fileAlias = "";
    
        this.encoding = "utf-8";
    
        this.zipFileEncoding = "EUC-KR";
    
        this.contentType = "application/octet-stream";
    
        this.directOpen = false;
    
        this.files = files;
        for (int i = 0; i < this.files.length; ++i) {
         try {
                if (!(checkFilePathValidation(this.files[i])))
                    throw new FileDownloadException("DSOWEB004","Configured file path is not permitted to access");
            }
         catch (IOException ex) {
                throw new FileDownloadException("DSOWEB004","Configured file path is not permitted to access", ex);
            }
        
            if (this.files[i].exists()) continue;
            throw new FileDownloadException("DSOWEB002", "Download file not found : " + this.files[i].getAbsolutePath());
        }
        this.fileAlias = fileAlias;
    }


    public void setContentType(String contentType)
    {
        this.contentType = contentType;
    }


    public void setEncoding(String encoding)
    {
        this.encoding = encoding;
    }


    public void setDirectOpen(boolean directOpen)
 	{
        /* 188 */ this.directOpen = directOpen;
    }


    public void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception{
        super.setContentType(this.contentType);
    
        if ((((this.files.length > 1) || ((this.files.length == 1) && (this.files[0].isDirectory())
                && (!(this.files[0].equals(new File("/"))))))) &&
        (!(this.fileAlias.toLowerCase().endsWith("zip")))
                && (!(this.fileAlias.toLowerCase().endsWith("jar")))) {
            this.fileAlias += ".zip";
        }
    
        setResponseHeaders(response);
        setResponseHeadersForMulitBrowser(request, response);
    
        renderBodyContentsToResponse(model, request, response);
    
        if (response.getOutputStream() != null)/* 215 */ response.getOutputStream().flush();
    }


    protected void renderBodyContentsToResponse(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response){
    	if (this.files.length == 1) {
	    	if (this.files[0].isFile()) {
	    		 writeSingleFileToOutputStream(this.files[0], response);
	    	}else {
	                writeToZipOutputStream(new File[] { this.files[0] }, response);
	        }
        }else{
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
            throw new FileDownloadException("DSOWEB003", "File download failed.", e);
        } finally {
            FileUtil.close(fileInputStream);
        }
    }


	 private void writeToZipOutputStream(File[] files, HttpServletResponse response)
	 {
        ArchiveStreamFactory archiveStreamFactory = null;
        ArchiveOutputStream archiveOutputStream = null;
     try {
            archiveStreamFactory = new ArchiveStreamFactory(this.zipFileEncoding);
        
            archiveOutputStream = archiveStreamFactory.createArchiveOutputStream("zip",
                    response.getOutputStream());
            for (int i = 0; i < files.length; ++i) {
                if (files[i].isFile()) {
                    addFile(archiveOutputStream, files[i], null);
                }
             else
             {
                    File[] children = files[i].listFiles();
                    for (int j = 0; j < children.length; ++j) {
                        if (children[j].isFile())
                            addFile(archiveOutputStream, children[j], files[i].getName());
                    }
                }
            }
            archiveOutputStream.flush();
        }
     catch (Exception e) {
            throw new FileDownloadException("DSOWEB003", "File download failed.", e);
        } finally {
            if (archiveOutputStream != null) {
             try {
                    archiveOutputStream.finish();
            } catch (IOException e) {
                    LOGGER.error("ArchiveOutputStream finish fail!", e);
            }
             try {
                    archiveOutputStream.close();
            } catch (IOException e) {
                    LOGGER.error("ArchiveOutputStream close fail!", e);
                }
            }
        }
    }


	 private void addFile(ArchiveOutputStream archiveOutputStream, File file, String parentDir) throws Exception
	 {
		 FileInputStream fInputStream = null;
		 try {
            if (parentDir != null) {
                ZipArchiveEntry entry = new ZipArchiveEntry(file, parentDir + "/" + file.getName());
                archiveOutputStream.putArchiveEntry(entry);
            } else {
                ZipArchiveEntry entry = new ZipArchiveEntry(file, file.getName());
                archiveOutputStream.putArchiveEntry(entry);
            }
            fInputStream = new FileInputStream(file);
            IOUtils.copy(fInputStream, archiveOutputStream);
        }
     finally {
           if (fInputStream != null) try {
                fInputStream.close();
        } catch (Exception ex) {
                LOGGER.error("FileInputStream close fail!", ex);
        }
            if (archiveOutputStream != null) try {
                archiveOutputStream.closeArchiveEntry();
        } catch (Exception ex) {
                LOGGER.error("ArchiveOutputStream closeArchiveEntry fail!", ex);
            }
        }
    }


 	protected void setResponseHeaders(HttpServletResponse response) throws UnsupportedEncodingException{
        response.setContentType(getContentType());
    
        response.setHeader("Content-Transfer-Encoding", "binary;");
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");
    }


 	protected void setResponseHeadersForMulitBrowser(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
        String sUserAgent = request.getHeader("USER-AGENT");
    
        if (sUserAgent.indexOf("MSIE 5.5") != -1) {
            response.setHeader("Content-Disposition",
                    "attachment; filename=\"" + FileUtil.encodeURLEncoding(this.fileAlias, this.encoding) + "\";");
        }
     else {
            boolean isChromeOrFirefox = false;
            if (sUserAgent.toLowerCase().indexOf("chrome") != -1) isChromeOrFirefox = true;
            else if (sUserAgent.toLowerCase().indexOf("firefox") != -1) {
                 isChromeOrFirefox = true;
            }
            String openStyle = (this.directOpen) ? "inline" : "attachment";
        
            if (isChromeOrFirefox)
                response.setHeader("Content-Disposition", openStyle + "; filename=" + "\"=?" + this.encoding
                        + "?Q?" + FileUtil.encodeQuotedPrintable(this.fileAlias, this.encoding) + "?=\";");
         else response.setHeader("Content-Disposition", openStyle + "; filename=\""
                    + FileUtil.encodeURLEncoding(this.fileAlias, this.encoding).replaceAll("\\+", " ") + "\"");
        }
    }


	 private boolean checkFilePathValidation(File file) throws IOException
	 {
		 try {
            if (!(file.getAbsolutePath().equals(file.getCanonicalFile().getPath())))/* 370 */ return false;
        }
     catch (IOException ex) {
            throw ex;
        }
        return true;
    }
 }