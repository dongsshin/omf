package com.rap.omc.framework.file.upload.model;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public class UploadFileInfo{
    private MultipartFile file;
    private File serverFile;
    public UploadFileInfo(MultipartFile file, File serverFile)
    {
        this.file = file;
        this.serverFile = serverFile;
    }


    public MultipartFile getFile()
    {
        return this.file;
    }

    public File getServerFile()
    {
        return this.serverFile;
    }

    public String getClientFileName()
    {
        return this.file.getOriginalFilename();
    }

    public String getServerFileName()
    {
        return this.serverFile.getName();
    }


    public String getServerPath()
    {
        return this.serverFile.getAbsolutePath();
    }


    public long getSize()
    {
        return this.serverFile.length();
    }
}