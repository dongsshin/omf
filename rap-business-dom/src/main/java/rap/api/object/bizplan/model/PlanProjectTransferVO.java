/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PlanProjectTransferVO.java
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
public class PlanProjectTransferVO extends BusinessObjectMasterVO {
    private String        planYear                                          ;
    private String        projectCode                                       ;
    private String        planProjectCode                                   ;
    private String        transferSection                                   ;
    private String        transferType                                      ;
    private String        transferScope                                     ;
    private String        transferBizType                                   ;
    private String        revisionYn                                        ;
    private Integer       projectRevisionNo                                 ;
    private String        versionObid                                       ;
    private String        fromObid                                          ;


    public void    setPlanYear(String planYear){
        this.planYear = planYear;
    }
    public void    setProjectCode(String projectCode){
        this.projectCode = projectCode;
    }
    public void    setPlanProjectCode(String planProjectCode){
        this.planProjectCode = planProjectCode;
    }
    public void    setTransferSection(String transferSection){
        this.transferSection = transferSection;
    }
    public void    setTransferType(String transferType){
        this.transferType = transferType;
    }
    public void    setTransferScope(String transferScope){
        this.transferScope = transferScope;
    }
    public void    setTransferBizType(String transferBizType){
        this.transferBizType = transferBizType;
    }
    public void    setRevisionYn(String revisionYn){
        this.revisionYn = revisionYn;
    }
    public void    setProjectRevisionNo(Integer projectRevisionNo){
        this.projectRevisionNo = projectRevisionNo;
    }
    public void    setVersionObid(String versionObid){
        this.versionObid = versionObid;
    }
    public void    setFromObid(String fromObid){
        this.fromObid = fromObid;
    }
    public String getPlanYear(){
        return planYear;
    }
    public String getProjectCode(){
        return projectCode;
    }
    public String getPlanProjectCode(){
        return planProjectCode;
    }
    public String getTransferSection(){
        return transferSection;
    }
    public String getTransferType(){
        return transferType;
    }
    public String getTransferScope(){
        return transferScope;
    }
    public String getTransferBizType(){
        return transferBizType;
    }
    public String getRevisionYn(){
        return revisionYn;
    }
    public Integer getProjectRevisionNo(){
        return projectRevisionNo;
    }
    public String getVersionObid(){
        return versionObid;
    }
    public String getFromObid(){
        return fromObid;
    }
}

