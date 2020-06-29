/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ConsultAttachFileVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.consult.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ConsultAttachFileVO extends BusinessObjectMasterVO {
    private Integer       sno                                               ;
    private String        fileNm                                            ;
    private String        fileOrigiNm                                       ;
    private String        filePath                                          ;
    private String        fileKind                                          ;
    private String        policy                                            ;
    private String        comments                                          ;
    private Integer       esseSno                                            = 0;
    private String        fileRootPath                                      ;
    private Integer       fileSize                                          ;


    public void    setSno(Integer sno){
        this.sno = sno;
    }
    public void    setFileNm(String fileNm){
        this.fileNm = fileNm;
    }
    public void    setFileOrigiNm(String fileOrigiNm){
        this.fileOrigiNm = fileOrigiNm;
    }
    public void    setFilePath(String filePath){
        this.filePath = filePath;
    }
    public void    setFileKind(String fileKind){
        this.fileKind = fileKind;
    }
    public void    setPolicy(String policy){
        this.policy = policy;
    }
    public void    setComments(String comments){
        this.comments = comments;
    }
    public void    setEsseSno(Integer esseSno){
        this.esseSno = esseSno;
    }
    public void    setFileRootPath(String fileRootPath){
        this.fileRootPath = fileRootPath;
    }
    public void    setFileSize(Integer fileSize){
        this.fileSize = fileSize;
    }
    public Integer getSno(){
        return sno;
    }
    public String getFileNm(){
        return fileNm;
    }
    public String getFileOrigiNm(){
        return fileOrigiNm;
    }
    public String getFilePath(){
        return filePath;
    }
    public String getFileKind(){
        return fileKind;
    }
    public String getPolicy(){
        return policy;
    }
    public String getComments(){
        return comments;
    }
    public Integer getEsseSno(){
        return esseSno;
    }
    public String getFileRootPath(){
        return fileRootPath;
    }
    public Integer getFileSize(){
        return fileSize;
    }
}

