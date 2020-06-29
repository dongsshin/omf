/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PartProjectWBSVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.partproject.model;


import com.rap.omc.api.object.model.BusinessObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class PartProjectWBSVO extends BusinessObjectVO {
    private String        prodPhaseCode                                     ;
    private String        prodActivityCode                                  ;
    private String        partActivityNameKor                               ;
    private String        partActivityNameEng                               ;
    private Integer       partActivitySorting                               ;
    private String        partOwnerRole                                     ;
    private String        partActivityType                                  ;
    private String        completeCondition                                 ;
    private String        documentMandatory                                  = "N";
    private Integer       st                                                ;
    private String        partActivityStatus                                 = "N";
    private Date          planStartDate                                     ;
    private Date          planEndDate                                       ;
    private Date          actualStartDate                                   ;
    private Date          actualEndDate                                     ;
    private String        ownerUserId                                       ;
    private String        reassignedUserId                                  ;
    private String        resultContents                                    ;
    private String        skipYn                                             = "N";


    public void    setProdPhaseCode(String prodPhaseCode){
        this.prodPhaseCode = prodPhaseCode;
    }
    public void    setProdActivityCode(String prodActivityCode){
        this.prodActivityCode = prodActivityCode;
    }
    public void    setPartActivityNameKor(String partActivityNameKor){
        this.partActivityNameKor = partActivityNameKor;
    }
    public void    setPartActivityNameEng(String partActivityNameEng){
        this.partActivityNameEng = partActivityNameEng;
    }
    public void    setPartActivitySorting(Integer partActivitySorting){
        this.partActivitySorting = partActivitySorting;
    }
    public void    setPartOwnerRole(String partOwnerRole){
        this.partOwnerRole = partOwnerRole;
    }
    public void    setPartActivityType(String partActivityType){
        this.partActivityType = partActivityType;
    }
    public void    setCompleteCondition(String completeCondition){
        this.completeCondition = completeCondition;
    }
    public void    setDocumentMandatory(String documentMandatory){
        this.documentMandatory = documentMandatory;
    }
    public void    setSt(Integer st){
        this.st = st;
    }
    public void    setPartActivityStatus(String partActivityStatus){
        this.partActivityStatus = partActivityStatus;
    }
    public void    setPlanStartDate(Date planStartDate){
        this.planStartDate = planStartDate;
    }
    public void    setPlanStartDate(String    planStartDate){
        this.planStartDate = this.omcConvertStr2Date(planStartDate);
    }
    public void    setPlanEndDate(Date planEndDate){
        this.planEndDate = planEndDate;
    }
    public void    setPlanEndDate(String    planEndDate){
        this.planEndDate = this.omcConvertStr2Date(planEndDate);
    }
    public void    setActualStartDate(Date actualStartDate){
        this.actualStartDate = actualStartDate;
    }
    public void    setActualStartDate(String    actualStartDate){
        this.actualStartDate = this.omcConvertStr2Date(actualStartDate);
    }
    public void    setActualEndDate(Date actualEndDate){
        this.actualEndDate = actualEndDate;
    }
    public void    setActualEndDate(String    actualEndDate){
        this.actualEndDate = this.omcConvertStr2Date(actualEndDate);
    }
    public void    setOwnerUserId(String ownerUserId){
        this.ownerUserId = ownerUserId;
    }
    public void    setReassignedUserId(String reassignedUserId){
        this.reassignedUserId = reassignedUserId;
    }
    public void    setResultContents(String resultContents){
        this.resultContents = resultContents;
    }
    public void    setSkipYn(String skipYn){
        this.skipYn = skipYn;
    }
    public String getProdPhaseCode(){
        return prodPhaseCode;
    }
    public String getProdActivityCode(){
        return prodActivityCode;
    }
    public String getPartActivityNameKor(){
        return partActivityNameKor;
    }
    public String getPartActivityNameEng(){
        return partActivityNameEng;
    }
    public Integer getPartActivitySorting(){
        return partActivitySorting;
    }
    public String getPartOwnerRole(){
        return partOwnerRole;
    }
    public String getPartActivityType(){
        return partActivityType;
    }
    public String getCompleteCondition(){
        return completeCondition;
    }
    public String getDocumentMandatory(){
        return documentMandatory;
    }
    public Integer getSt(){
        return st;
    }
    public String getPartActivityStatus(){
        return partActivityStatus;
    }
    public Date getPlanStartDate(){
        return planStartDate;
    }
    public Date getPlanEndDate(){
        return planEndDate;
    }
    public Date getActualStartDate(){
        return actualStartDate;
    }
    public Date getActualEndDate(){
        return actualEndDate;
    }
    public String getOwnerUserId(){
        return ownerUserId;
    }
    public String getReassignedUserId(){
        return reassignedUserId;
    }
    public String getResultContents(){
        return resultContents;
    }
    public String getSkipYn(){
        return skipYn;
    }
}

