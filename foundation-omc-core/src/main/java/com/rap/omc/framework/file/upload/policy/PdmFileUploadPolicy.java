/*** Eclipse Class Decompiler plugin, copyright (c) 2012 Chao Chen (cnfree2000@hotmail.com) ***/
package com.rap.omc.framework.file.upload.policy;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;



import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.rap.omc.framework.exception.FoundationBaseException;
import com.rap.omc.framework.file.upload.exception.AllowOrDenyFileUploadException;
import com.rap.omc.framework.file.upload.exception.FileSizeLimitException;
import com.rap.omc.framework.file.upload.exception.TotalFileSizeLimitException;
import com.rap.omc.framework.file.upload.exception.ZeroSizeFileUploadException;
import com.rap.omc.util.core.FileUtil;



public class PdmFileUploadPolicy implements FileUploadPolicy {

    private static final Logger LOGGER = LoggerFactory.getLogger(PdmFileUploadPolicy.class);

    protected static final Pattern FILE_PATTERN = Pattern.compile("\\{(.*?)\\}");

    protected String uploadTargetDir;

    protected String allowPattern;

    protected String denyPattern;


    protected int dirDepth;
    protected int addedSize;

    protected long maxTotalFileSize;

    protected long maxFileSize;

    protected boolean zeroSizeAllow;



    public PdmFileUploadPolicy() {
        this.uploadTargetDir = System.getProperty("user.home");

        this.allowPattern = "*";

        this.denyPattern = "";

        this.dirDepth = 6;

        this.addedSize = dirDepth + 6;

        this.maxTotalFileSize = -1L;

        this.maxFileSize = -1L;

        this.zeroSizeAllow = true;
    }

    public void setUploadTargetDir(Resource uploadTargetDir) throws IOException{
        this.uploadTargetDir = uploadTargetDir.getFile().getAbsolutePath();
    }

    public void setAllowPattern(String allowPattern){
        this.allowPattern = allowPattern;
    }

    public void setDenyPattern(String denyPattern){
        this.denyPattern = denyPattern;
    }
    public void setDirDepth(String dirDepth){
        this.dirDepth = Integer.parseInt( dirDepth );
    }
    public void setMaxTotalFileSize(String maxTotalFileSize){
        this.maxTotalFileSize = FileUtil.parseStringFormatFileSize(maxTotalFileSize);
    }
    public void setMaxFileSize(String maxFileSize){
        this.maxFileSize = FileUtil.parseStringFormatFileSize(maxFileSize);
    }
    public void setZeroSizeAllow(boolean zeroSizeAllow){
        this.zeroSizeAllow = zeroSizeAllow;
    }

    public void validate(MultipartFile file, String policyName, long filesTotalSize){
        LOGGER.debug("Start fileupload policy validation (policyName : {})", policyName);
        LOGGER.debug(" + Original Filename        : [{}])", file.getOriginalFilename());
        LOGGER.debug(" + policy.uploadTargetDir   : [{}]", this.uploadTargetDir);
        LOGGER.debug(" + policy.dirDepth          : [{}]", Integer.valueOf(this.dirDepth));
        LOGGER.debug(" + policy.zeroSizeAllow     : [{}]", Boolean.valueOf(this.zeroSizeAllow));
        LOGGER.debug(" + policy.maxFileSize       : [{}]", Long.valueOf(this.maxFileSize));
        LOGGER.debug(" + policy.maxTotalFileSize  : [{}]", Long.valueOf(this.maxTotalFileSize));
        LOGGER.debug(" + policy.allowPattern      : [{}]", this.allowPattern);
        LOGGER.debug(" + policy.denyPattern       : [{}]", this.denyPattern);

        checkUnitFileSizeExceeded(file.getSize(), policyName);
        checkFilesTotalSizeExceeded(file.getSize() + filesTotalSize, policyName);
        checkAllowOrDenyFileName(file.getOriginalFilename(), policyName);
    }

    protected void checkUnitFileSizeExceeded(long fileSize, String policyName){
        if ((!(this.zeroSizeAllow)) && (fileSize <= 0L)) {
            String errorMessage = "Uploaded file size is 0KB. Zero file size not allowed.";
            throw new ZeroSizeFileUploadException("DSOFUP003", "[policy:" + policyName + "] " + errorMessage);
        }

        if ((this.maxFileSize != -1L) && (fileSize > this.maxFileSize)) {
            String errorMessage = "Uploaded file size is larger than maxFileSize[" + this.maxFileSize + "]";
            throw new FileSizeLimitException("DSOFUP003", "[policy:" + policyName + "] " + errorMessage);
        }
    }

    public void checkFilesTotalSizeExceeded(long filesTotalSize, String policyName){
        if ((this.maxTotalFileSize != -1L) && (filesTotalSize > this.maxTotalFileSize)) {
            String errorMessage = "Uploaded files total size is larger than maxTotalFileSize[" + this.maxTotalFileSize
                    + "]";
            throw new TotalFileSizeLimitException("DSOFUP003", "[policy:" + policyName + "] " + errorMessage);
        }
    }

    protected void checkAllowOrDenyFileName(String filename, String policyName){
        String lowerFileName = filename.toLowerCase();
        try {
            if (!(FileUtil.acceptFileName(lowerFileName, this.allowPattern, this.denyPattern))) {
                String errorMessage = "Uploaded File '" + filename
                        + "' is violated denyPattern or doesn't match allowPattern.[allow : " + this.allowPattern
                        + "][deny : " + this.denyPattern + "]";
                throw new AllowOrDenyFileUploadException("DSOFUP003", "[policy:" + policyName + "] " + errorMessage);
            }
        } catch (IllegalArgumentException e) {
            throw new AllowOrDenyFileUploadException("DSOFUP003", "[policy:" + policyName + "] " + e.getMessage(),e);
        }
    }

    public File getFileNameToSave(String filename, String subDir){
      if ( filename.trim().length() < 1 ) {
          throw new FoundationBaseException("frame.error.upload.nameSizeZero");
      }
      String fullDir =  this.uploadTargetDir  +"/" + subDir;
      File newFile = createDownPath(fullDir);
      return newFile;
  }
    
    public File getFileNameToSaveRealDir(String filename, String subDir){
      
      if ( filename.trim().length() < 1 ) {
          throw new FoundationBaseException("frame.error.upload.nameSizeZero");
      }
      
      File newFile = new File(subDir);

      return newFile;
  }
  protected File createDownPath(String root){
      String random = RandomStringUtils.randomAlphabetic(this.addedSize);
      String prefix = random.substring(0, 3);
      String suffix = random.substring(3, 6);
      StringBuffer buf = new StringBuffer(root);
      for( int i = 6; i < this.addedSize; i++ ) {
          buf.append(File.separatorChar);
          buf.append(String.valueOf(random.charAt(i)).toLowerCase());
      }
      String dirname = buf.toString();
      new File(dirname).mkdirs();
      File file = null;
      try {
          file = File.createTempFile(prefix, suffix, new File( dirname ));
      } catch (IOException e) {
          throw new FoundationBaseException("frame.error.upload.creteTempFile", e);
      }
      return file;
    }
    public String getUploadTargetDir(){
        return uploadTargetDir;
    }
}