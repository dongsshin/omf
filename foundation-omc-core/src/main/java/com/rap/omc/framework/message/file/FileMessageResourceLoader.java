package com.rap.omc.framework.message.file;

import java.io.IOException;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.rap.omc.framework.message.exception.MessageException;
public class FileMessageResourceLoader extends DefaultResourceLoader
{
    public Resource getResource(String location)
    {
        Resource resource = super.getResource(location);
        
        if (resource.exists())
        {
            String absolutePath = null;
            try
            {
                absolutePath = resource.getFile().getAbsolutePath();
                } catch (IOException ex) {
                throw new MessageException(absolutePath + " is not existed.");
            }
            return new FileSystemResource(absolutePath);
        }
        return resource;
    }
}