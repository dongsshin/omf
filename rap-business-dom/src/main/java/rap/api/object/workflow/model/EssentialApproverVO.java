/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : EssentialApproverVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.workflow.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class EssentialApproverVO extends BusinessObjectMasterVO {
    private String        datatype                                          ;
    private String        checktype                                         ;
    private String        applieddivisionLcond                              ;
    private String        appliedtcsiteLcond                                ;
    private String        appliedplantLcond                                 ;
    private String        appliedaffiliateLcond                             ;
    private Integer       errorlevel                                         = 0;
    private Integer       isOptional                                         = 0;
    private String        requesteruserLcond                                ;
    private String        requesterdeptLcond                                ;
    private String        changeLcond                                       ;
    private String        drawingtypeLcond                                  ;
    private String        classLcond                                        ;
    private String        startwihclassLcond                                ;
    private String        drawingnoLcond                                    ;
    private String        exceptionlist                                     ;
    private String        returnMessage                                     ;
    private Integer       checkorder                                        ;
    private Integer       displayonsuccessflag                               = 1;
    private String        approveruserRcond                                 ;
    private String        approverdeptRcond                                 ;
    private String        chksql                                            ;
    private String        approvalordistributeelist                         ;
    private String        changeremarks                                     ;
    private String        addChksqlOld                                      ;
    private String        addApprovalordistributeOld                        ;
    private String        addRegtype                                        ;
    private String        addObjid                                          ;
    private String        ruleOwner                                         ;
    private String        requesterdeptLcondFlag                            ;
    private String        approverdeptRcondFlag                             ;


    public void    setDatatype(String datatype){
        this.datatype = datatype;
    }
    public void    setChecktype(String checktype){
        this.checktype = checktype;
    }
    public void    setApplieddivisionLcond(String applieddivisionLcond){
        this.applieddivisionLcond = applieddivisionLcond;
    }
    public void    setAppliedtcsiteLcond(String appliedtcsiteLcond){
        this.appliedtcsiteLcond = appliedtcsiteLcond;
    }
    public void    setAppliedplantLcond(String appliedplantLcond){
        this.appliedplantLcond = appliedplantLcond;
    }
    public void    setAppliedaffiliateLcond(String appliedaffiliateLcond){
        this.appliedaffiliateLcond = appliedaffiliateLcond;
    }
    public void    setErrorlevel(Integer errorlevel){
        this.errorlevel = errorlevel;
    }
    public void    setIsOptional(Integer isOptional){
        this.isOptional = isOptional;
    }
    public void    setRequesteruserLcond(String requesteruserLcond){
        this.requesteruserLcond = requesteruserLcond;
    }
    public void    setRequesterdeptLcond(String requesterdeptLcond){
        this.requesterdeptLcond = requesterdeptLcond;
    }
    public void    setChangeLcond(String changeLcond){
        this.changeLcond = changeLcond;
    }
    public void    setDrawingtypeLcond(String drawingtypeLcond){
        this.drawingtypeLcond = drawingtypeLcond;
    }
    public void    setClassLcond(String classLcond){
        this.classLcond = classLcond;
    }
    public void    setStartwihclassLcond(String startwihclassLcond){
        this.startwihclassLcond = startwihclassLcond;
    }
    public void    setDrawingnoLcond(String drawingnoLcond){
        this.drawingnoLcond = drawingnoLcond;
    }
    public void    setExceptionlist(String exceptionlist){
        this.exceptionlist = exceptionlist;
    }
    public void    setReturnMessage(String returnMessage){
        this.returnMessage = returnMessage;
    }
    public void    setCheckorder(Integer checkorder){
        this.checkorder = checkorder;
    }
    public void    setDisplayonsuccessflag(Integer displayonsuccessflag){
        this.displayonsuccessflag = displayonsuccessflag;
    }
    public void    setApproveruserRcond(String approveruserRcond){
        this.approveruserRcond = approveruserRcond;
    }
    public void    setApproverdeptRcond(String approverdeptRcond){
        this.approverdeptRcond = approverdeptRcond;
    }
    public void    setChksql(String chksql){
        this.chksql = chksql;
    }
    public void    setApprovalordistributeelist(String approvalordistributeelist){
        this.approvalordistributeelist = approvalordistributeelist;
    }
    public void    setChangeremarks(String changeremarks){
        this.changeremarks = changeremarks;
    }
    public void    setAddChksqlOld(String addChksqlOld){
        this.addChksqlOld = addChksqlOld;
    }
    public void    setAddApprovalordistributeOld(String addApprovalordistributeOld){
        this.addApprovalordistributeOld = addApprovalordistributeOld;
    }
    public void    setAddRegtype(String addRegtype){
        this.addRegtype = addRegtype;
    }
    public void    setAddObjid(String addObjid){
        this.addObjid = addObjid;
    }
    public void    setRuleOwner(String ruleOwner){
        this.ruleOwner = ruleOwner;
    }
    public void    setRequesterdeptLcondFlag(String requesterdeptLcondFlag){
        this.requesterdeptLcondFlag = requesterdeptLcondFlag;
    }
    public void    setApproverdeptRcondFlag(String approverdeptRcondFlag){
        this.approverdeptRcondFlag = approverdeptRcondFlag;
    }
    public String getDatatype(){
        return datatype;
    }
    public String getChecktype(){
        return checktype;
    }
    public String getApplieddivisionLcond(){
        return applieddivisionLcond;
    }
    public String getAppliedtcsiteLcond(){
        return appliedtcsiteLcond;
    }
    public String getAppliedplantLcond(){
        return appliedplantLcond;
    }
    public String getAppliedaffiliateLcond(){
        return appliedaffiliateLcond;
    }
    public Integer getErrorlevel(){
        return errorlevel;
    }
    public Integer getIsOptional(){
        return isOptional;
    }
    public String getRequesteruserLcond(){
        return requesteruserLcond;
    }
    public String getRequesterdeptLcond(){
        return requesterdeptLcond;
    }
    public String getChangeLcond(){
        return changeLcond;
    }
    public String getDrawingtypeLcond(){
        return drawingtypeLcond;
    }
    public String getClassLcond(){
        return classLcond;
    }
    public String getStartwihclassLcond(){
        return startwihclassLcond;
    }
    public String getDrawingnoLcond(){
        return drawingnoLcond;
    }
    public String getExceptionlist(){
        return exceptionlist;
    }
    public String getReturnMessage(){
        return returnMessage;
    }
    public Integer getCheckorder(){
        return checkorder;
    }
    public Integer getDisplayonsuccessflag(){
        return displayonsuccessflag;
    }
    public String getApproveruserRcond(){
        return approveruserRcond;
    }
    public String getApproverdeptRcond(){
        return approverdeptRcond;
    }
    public String getChksql(){
        return chksql;
    }
    public String getApprovalordistributeelist(){
        return approvalordistributeelist;
    }
    public String getChangeremarks(){
        return changeremarks;
    }
    public String getAddChksqlOld(){
        return addChksqlOld;
    }
    public String getAddApprovalordistributeOld(){
        return addApprovalordistributeOld;
    }
    public String getAddRegtype(){
        return addRegtype;
    }
    public String getAddObjid(){
        return addObjid;
    }
    public String getRuleOwner(){
        return ruleOwner;
    }
    public String getRequesterdeptLcondFlag(){
        return requesterdeptLcondFlag;
    }
    public String getApproverdeptRcondFlag(){
        return approverdeptRcondFlag;
    }
}

