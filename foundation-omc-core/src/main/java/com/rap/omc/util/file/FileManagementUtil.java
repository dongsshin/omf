package com.rap.omc.util.file;

import java.io.File;

import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.framework.exception.FoundationException;



public class FileManagementUtil {

    public static String getPrivateDir() throws FoundationException{   
        String privateDir = getBasePrivateDir() + "/" + ThreadLocalUtil.getUserId();
        makePrivateDir(privateDir);
        return  privateDir;
    }
    private static void makePrivateDir(String privateDir){
        try {
            File targetDir = new File(privateDir);
            if(!targetDir.exists()) { 
                targetDir.mkdirs();
            }
        }catch(Exception e){
            throw new FoundationException("omc.error.dir.notMakePrivateDir",e);
        }
    }
    public static String getWorkingTemporaryDir() throws FoundationException{
        File directory = FileCommonUtil.makeTempDir(getPrivateDir());
        return directory.getAbsolutePath();
    }
    public static String getBasePrivateDir(){
        return FCSServerLocationUtil.getBasePrivateDir();
    }
}
