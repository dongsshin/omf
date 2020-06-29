package com.rap.omc.framework.file.upload.policy;

/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;

/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.core.io.Resource;
/*     */ import org.springframework.web.multipart.MultipartFile;

import com.rap.omc.framework.file.upload.exception.AllowOrDenyFileUploadException;
import com.rap.omc.framework.file.upload.exception.FileSizeLimitException;
import com.rap.omc.framework.file.upload.exception.TotalFileSizeLimitException;
import com.rap.omc.framework.file.upload.exception.ZeroSizeFileUploadException;
import com.rap.omc.util.StringUtil;
import com.rap.omc.util.core.DateUtil;
import com.rap.omc.util.core.FileUtil;
public class DefaultFileUploadPolicy implements FileUploadPolicy{

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultFileUploadPolicy.class);

    
    protected static final Pattern FILE_PATTERN = Pattern.compile("\\{(.*?)\\}");

    
    protected String uploadTargetDir = System.getProperty("user.home");

    
    protected String allowPattern = "*";

    
    protected String denyPattern = "";

    
    protected String renamePattern = "{prefix}.{ext}";

    
    protected long maxTotalFileSize = -1L;

    
    protected long maxFileSize = -1L;

    
    protected boolean sequenceAllow = true;

    
    protected boolean zeroSizeAllow = true;

    
    public void setUploadTargetDir(Resource uploadTargetDir)/*     */ throws IOException
    {
        String path = uploadTargetDir.getURL().getPath();
        
        if (path.contains("date")) this.uploadTargetDir = applyUploadTargetDatePattern(path);
        else this.uploadTargetDir = uploadTargetDir.getFile().getAbsolutePath();
        }

    
    private String applyUploadTargetDatePattern(String pattern)
    {
        StringBuffer stringBuffer = new StringBuffer();
        Matcher matcher = FILE_PATTERN.matcher(pattern);
        
        String part = null;
        
        while (matcher.find()) {
            part = matcher.group(1);
            if ("date:".equals(part.substring(0, 5))) {
                part = DateUtil.getDate(part.substring(5));
                }
            matcher.appendReplacement(stringBuffer, StringUtil.escapeBackslashAndDollarSign(part));
            }
        
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString().trim();
        }

    
    public void setAllowPattern(String allowPattern)
    {
        this.allowPattern = allowPattern;
        }

    
    public void setDenyPattern(String denyPattern)
    {
        this.denyPattern = denyPattern;
        }

    
    public void setRenamePattern(String renamePattern)
    {
        this.renamePattern = renamePattern;
        }

    
    public void setMaxTotalFileSize(String maxTotalFileSize)
    {
        this.maxTotalFileSize = FileUtil.parseStringFormatFileSize(maxTotalFileSize);
        }

    
    public void setMaxFileSize(String maxFileSize)
    {
        this.maxFileSize = FileUtil.parseStringFormatFileSize(maxFileSize);
        }

    
    public void setSequenceAllow(boolean sequenceAllow)
    {
        this.sequenceAllow = sequenceAllow;
        }

    
    public void setZeroSizeAllow(boolean zeroSizeAllow)
    {
        this.zeroSizeAllow = zeroSizeAllow;
        }

    
    public void validate(MultipartFile file, String policyName, long filesTotalSize)
    {
        LOGGER.debug("Start fileupload policy validation (policyName : {})", policyName);
        LOGGER.debug(" + Original Filename        : [{}])", file.getOriginalFilename());
        LOGGER.debug(" + policy.uploadTargetDir   : [{}]", this.uploadTargetDir);
        LOGGER.debug(" + policy.renamePattern     : [{}]", this.renamePattern);
        LOGGER.debug(" + policy.sequenceAllow     : [{}]", Boolean.valueOf(this.sequenceAllow));
        LOGGER.debug(" + policy.zeroSizeAllow     : [{}]", Boolean.valueOf(this.zeroSizeAllow));
        LOGGER.debug(" + policy.maxFileSize       : [{}]", Long.valueOf(this.maxFileSize));
        LOGGER.debug(" + policy.maxTotalFileSize  : [{}]", Long.valueOf(this.maxTotalFileSize));
        LOGGER.debug(" + policy.allowPattern      : [{}]", this.allowPattern);
        LOGGER.debug(" + policy.denyPattern       : [{}]", this.denyPattern);
        
        checkUnitFileSizeExceeded(file.getSize(), policyName);
        checkFilesTotalSizeExceeded(file.getSize() + filesTotalSize, policyName);
        checkAllowOrDenyFileName(file.getOriginalFilename(), policyName);
        }

    
    protected void checkUnitFileSizeExceeded(long fileSize, String policyName)
    {
        if ((!(this.zeroSizeAllow)) && (fileSize <= 0L)) {
             String errorMessage = "Uploaded file size is 0KB. Zero file size not allowed.";
             throw new ZeroSizeFileUploadException("[policy:" + policyName + "] " + errorMessage);
             }
        
        if ((this.maxFileSize != -1L) && (fileSize > this.maxFileSize)) {
            String errorMessage = "Uploaded file size is larger than maxFileSize[" + this.maxFileSize + "]";
            throw new FileSizeLimitException("[policy:" + policyName + "] " + errorMessage);
            }
        }

    
    public void checkFilesTotalSizeExceeded(long filesTotalSize, String policyName)
    {
        if ((this.maxTotalFileSize != -1L) && (filesTotalSize > this.maxTotalFileSize)) {
            String errorMessage = "Uploaded files total size is larger than maxTotalFileSize["
                    + this.maxTotalFileSize + "]";
            throw new TotalFileSizeLimitException("[policy:" + policyName + "] " + errorMessage);
            }
    }

    
    protected void checkAllowOrDenyFileName(String filename, String policyName)
    {
        try
        {
            if (!(FileUtil.acceptFileName(filename, this.allowPattern, this.denyPattern))) {
                String errorMessage = "Uploaded File '" + filename
                        + "' is violated denyPattern or doesn't match allowPattern.[allow : " + this.allowPattern
                        + "][deny : " + this.denyPattern + "]";
                throw new AllowOrDenyFileUploadException("[policy:" + policyName + "] " + errorMessage);
                }
            } catch (IllegalArgumentException e) {
            throw new AllowOrDenyFileUploadException("[policy:" + policyName + "] " + e.getMessage());
            }
        }
    public File getFileNameToSave(String filename, String subDir)
    {
        String newFilename = filename;
        
        newFilename = applyRenamePattern(filename, this.renamePattern);
        File newFile = applySequencePattern(newFilename, subDir);
        
        return newFile;
        }
    protected String applyRenamePattern(String name, String pattern)
    {
        StringBuffer stringBuffer = new StringBuffer();
        Matcher matcher = FILE_PATTERN.matcher(pattern);
        
        int lastDot = name.lastIndexOf(46);
        String prefix = (lastDot == -1) ? name : name.substring(0, lastDot);
        String extension = (lastDot == -1) ? "" : name.substring(lastDot + 1);
        String part = null;
        
        while (matcher.find()) {
            part = matcher.group(1);
            if ("prefix".equals(part))/* 316 */ part = prefix;
            else if ("ext".equals(part))/* 318 */ part = extension;
            else if ("date:".equals(part.substring(0, 5))) {
                part = DateUtil.getDate(part.substring(5));
                }
            matcher.appendReplacement(stringBuffer, StringUtil.escapeBackslashAndDollarSign(part));
        }
        
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString().trim();
    }
    protected File applySequencePattern(String newFileName, String subDir)
    {
        File file = null;
        
        if (subDir == null) {
        	file = new File(this.uploadTargetDir + "/" + newFileName);
        }
        else {
            file = new File(this.uploadTargetDir + "/" + subDir + "/" + newFileName);
        }
        
        if ((this.sequenceAllow) && (file.exists())) {
            String filePath = file.getAbsolutePath();
            int lastDot = filePath.lastIndexOf(46);
            String prefix = (lastDot == -1) ? filePath : filePath.substring(0, lastDot);
            String ext = (lastDot == -1) ? "" : filePath.substring(lastDot);
            
            int count = 0;
            do {
                file = new File(prefix + "[" + count + "]" + ext);
                ++count;
            }
            while (file.exists());
        }
        return file;
    }
}