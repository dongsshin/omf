/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : HttpUtil.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 8. 24.  jongjung.kwon   Initial
 * ===========================================
 */
package com.rap.omc.util.file;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.rap.omc.api.object.model.FilesVO;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.util.NullUtil;



/**
 * <pre>
 * Class : HttpUtil
 * Description : TODO
 * </pre>
 * 
 * @author jongjung.kwon
 */
public class FileCommonUtil {
    public static List<MultipartFile> generateMultipartFile(List<MultipartFile> files){
        List<MultipartFile> result = new ArrayList<MultipartFile>();
        for(MultipartFile file : files){
            if(!NullUtil.isNone(file.getOriginalFilename())){
                result.add(file);
            }            
        }        
        return result;
    }
    public static void copyFile(File sourceFile, File targetFile)throws IOException{
       // TODO Auto-generated method stub
       Path from = sourceFile.toPath(); //convert from File to Path
       Path to = targetFile.toPath(); //convert from String to Path
       
       java.nio.file.Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
    }
    /**
    *
    * @param sourceFile
    * @param targetFile
  * @throws IOException 
    */
    public static void moveFile(File sourceFile, File targetFile) throws IOException{
       Path from = sourceFile.toPath();
       Path to   = targetFile.toPath();
       java.nio.file.Files.move(from, to, StandardCopyOption.REPLACE_EXISTING);
    }
    public static boolean deleteFile(FilesVO uploadFile){
        try {
            File file = new File(FileManagementUtil.getPrivateDir() + File.separator + uploadFile.getUserFileName());
            file.delete();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static boolean deleteFile(String fileFullPath){
        try {
            File file = new File(fileFullPath);
            file.delete();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static String getAbsolutePath(String targetDir, String path){
          String rootPath;
          if(targetDir.endsWith("/")){
              rootPath = targetDir + path.substring(1);
          }else{
              rootPath = targetDir + path;    
          }
          return rootPath;
    }
    public static File makeTempDir(String root){
        int dirDepth = 6;
        int addedSize = dirDepth + 6;
        String random = RandomStringUtils.randomAlphabetic(addedSize);
        String prefix = random.substring(0, 3);
        String suffix = random.substring(3, 6);
        StringBuffer buf = new StringBuffer(root);
        for( int i = 6; i < addedSize; i++ ) {
            buf.append(File.separatorChar);
            buf.append( random.charAt(i));
        }
        String dirname = buf.toString();
        new File(dirname).mkdirs();
        java.io.File file = null;
        try {
            file = File.createTempFile(prefix, suffix, new File( dirname ));
        } catch (IOException e) {
            throw new FoundationException("frame.error.upload.creteTempFile", e);
        }
        return file;
    }
    public static File getFile(String fullPath){
        File file = new File(fullPath);
        String encodeFileName = null;
        String[] encodingList = {"euc-kr","ksc5601","utf-8","iso-8859-1","x-windows-949"};
        if(!file.exists()){
            return file;
        }else{
            boolean isFile = false;
            for(int encFromIdx = 0; encFromIdx < encodingList.length; encFromIdx++){
                for(int encToIdx = 0; encToIdx < encodingList.length; encToIdx++){
                    if(encFromIdx == encToIdx) continue;
                    try {
                        encodeFileName = new String(fullPath.getBytes(encodingList[encFromIdx]),encodingList[encToIdx]);
                    } catch (UnsupportedEncodingException e) {
                        continue;
                    }
                    try{
                        file = new File(encodeFileName);
                        if(file.exists()) {
                            isFile = true;
                            break;
                        }
                    }catch(Exception ex){
                        return file;
                    }
                }
                if(isFile) {
                    break;
                }
            }
        }
        return file;
    }
}
