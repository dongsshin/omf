/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectActivityDocumentTemplateVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.document.model;


import rap.api.object.document.model.ProjectDocumentTemplateVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ProjectActivityDocumentTemplateVO extends ProjectDocumentTemplateVO {
    private String        documentNameKor                                   ;
    private String        documentNameEng                                   ;
    private String        documentNameChi                                   ;
    private String        defaultActivityCode                               ;
    private String        defaultIsMandantory                               ;
    private String        defaultSkipGradeList                              ;
    private String        isCommon                                          ;


    public void    setDocumentNameKor(String documentNameKor){
        this.documentNameKor = documentNameKor;
    }
    public void    setDocumentNameEng(String documentNameEng){
        this.documentNameEng = documentNameEng;
    }
    public void    setDocumentNameChi(String documentNameChi){
        this.documentNameChi = documentNameChi;
    }
    public void    setDefaultActivityCode(String defaultActivityCode){
        this.defaultActivityCode = defaultActivityCode;
    }
    public void    setDefaultIsMandantory(String defaultIsMandantory){
        this.defaultIsMandantory = defaultIsMandantory;
    }
    public void    setDefaultSkipGradeList(String defaultSkipGradeList){
        this.defaultSkipGradeList = defaultSkipGradeList;
    }
    public void    setIsCommon(String isCommon){
        this.isCommon = isCommon;
    }
    public String getDocumentNameKor(){
        return documentNameKor;
    }
    public String getDocumentNameEng(){
        return documentNameEng;
    }
    public String getDocumentNameChi(){
        return documentNameChi;
    }
    public String getDefaultActivityCode(){
        return defaultActivityCode;
    }
    public String getDefaultIsMandantory(){
        return defaultIsMandantory;
    }
    public String getDefaultSkipGradeList(){
        return defaultSkipGradeList;
    }
    public String getIsCommon(){
        return isCommon;
    }
}

