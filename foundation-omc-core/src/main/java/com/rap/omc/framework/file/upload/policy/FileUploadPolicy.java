package com.rap.omc.framework.file.upload.policy;

import java.io.File;
import org.springframework.web.multipart.MultipartFile;

public abstract interface FileUploadPolicy {

    public abstract void validate(MultipartFile paramMultipartFile, String paramString, long paramLong);

    public abstract File getFileNameToSave(String paramString1, String paramString2);
}