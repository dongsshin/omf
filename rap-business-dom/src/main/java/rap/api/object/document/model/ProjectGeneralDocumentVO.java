/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectGeneralDocumentVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.document.model;


import rap.api.object.document.model.ProjectDocumentsVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ProjectGeneralDocumentVO extends ProjectDocumentsVO {
    private String        groupCode                                         ;
    private Integer       seqNo                                             ;
    private String        categoryCode                                      ;
    private String        projectCode                                       ;
    private String        secGradeFlag                                      ;
    private String        secLevel                                          ;
    private String        contents                                          ;


    public void    setGroupCode(String groupCode){
        this.groupCode = groupCode;
    }
    public void    setSeqNo(Integer seqNo){
        this.seqNo = seqNo;
    }
    public void    setCategoryCode(String categoryCode){
        this.categoryCode = categoryCode;
    }
    public void    setProjectCode(String projectCode){
        this.projectCode = projectCode;
    }
    public void    setSecGradeFlag(String secGradeFlag){
        this.secGradeFlag = secGradeFlag;
    }
    public void    setSecLevel(String secLevel){
        this.secLevel = secLevel;
    }
    public void    setContents(String contents){
        this.contents = contents;
    }
    public String getGroupCode(){
        return groupCode;
    }
    public Integer getSeqNo(){
        return seqNo;
    }
    public String getCategoryCode(){
        return categoryCode;
    }
    public String getProjectCode(){
        return projectCode;
    }
    public String getSecGradeFlag(){
        return secGradeFlag;
    }
    public String getSecLevel(){
        return secLevel;
    }
    public String getContents(){
        return contents;
    }
}

