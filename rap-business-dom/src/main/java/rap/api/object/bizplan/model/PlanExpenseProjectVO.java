/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PlanExpenseProjectVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.bizplan.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class PlanExpenseProjectVO extends BusinessObjectMasterVO {
    private Integer       ifSeq                                             ;
    private String        projectCode                                       ;
    private String        projectName                                       ;
    private String        projectNickName                                   ;
    private String        legalEntityName                                   ;
    private String        ifYn                                              ;
    private String        errorYn                                           ;
    private String        errorMsg                                          ;


    public void    setIfSeq(Integer ifSeq){
        this.ifSeq = ifSeq;
    }
    public void    setProjectCode(String projectCode){
        this.projectCode = projectCode;
    }
    public void    setProjectName(String projectName){
        this.projectName = projectName;
    }
    public void    setProjectNickName(String projectNickName){
        this.projectNickName = projectNickName;
    }
    public void    setLegalEntityName(String legalEntityName){
        this.legalEntityName = legalEntityName;
    }
    public void    setIfYn(String ifYn){
        this.ifYn = ifYn;
    }
    public void    setErrorYn(String errorYn){
        this.errorYn = errorYn;
    }
    public void    setErrorMsg(String errorMsg){
        this.errorMsg = errorMsg;
    }
    public Integer getIfSeq(){
        return ifSeq;
    }
    public String getProjectCode(){
        return projectCode;
    }
    public String getProjectName(){
        return projectName;
    }
    public String getProjectNickName(){
        return projectNickName;
    }
    public String getLegalEntityName(){
        return legalEntityName;
    }
    public String getIfYn(){
        return ifYn;
    }
    public String getErrorYn(){
        return errorYn;
    }
    public String getErrorMsg(){
        return errorMsg;
    }
}

