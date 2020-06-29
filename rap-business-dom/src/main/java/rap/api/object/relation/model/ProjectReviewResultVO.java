/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectReviewResultVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.relation.model;


import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ProjectReviewResultVO extends BusinessRelationObjectVO {
    private String        selfValue                                         ;
    private String        finalValue                                        ;
    private String        checkComment                                      ;
    private String        checkDocumentObid                                 ;
    private String        inputComment                                      ;
    private String        activityObid                                      ;
    private String        checkApplicationNames                             ;
    private String        checkApplicationTitle                             ;
    private Boolean       isDoc                                              = (Boolean)true;
    private String        finalPoint                                        ;
    private String        attribute01                                       ;
    private String        comment                                           ;
    private String        percentagePoint                                   ;
    private String        attribute02                                       ;
    private String        attribute06                                       ;
    private String        attribute03                                       ;
    private String        attribute04                                       ;
    private String        attribute05                                       ;


    public void    setSelfValue(String selfValue){
        this.selfValue = selfValue;
    }
    public void    setFinalValue(String finalValue){
        this.finalValue = finalValue;
    }
    public void    setCheckComment(String checkComment){
        this.checkComment = checkComment;
    }
    public void    setCheckDocumentObid(String checkDocumentObid){
        this.checkDocumentObid = checkDocumentObid;
    }
    public void    setInputComment(String inputComment){
        this.inputComment = inputComment;
    }
    public void    setActivityObid(String activityObid){
        this.activityObid = activityObid;
    }
    public void    setCheckApplicationNames(String checkApplicationNames){
        this.checkApplicationNames = checkApplicationNames;
    }
    public void    setCheckApplicationTitle(String checkApplicationTitle){
        this.checkApplicationTitle = checkApplicationTitle;
    }
    public void    setIsDoc(Boolean isDoc){
        this.isDoc = isDoc;
    }
    public void    setFinalPoint(String finalPoint){
        this.finalPoint = finalPoint;
    }
    public void    setAttribute01(String attribute01){
        this.attribute01 = attribute01;
    }
    public void    setComment(String comment){
        this.comment = comment;
    }
    public void    setPercentagePoint(String percentagePoint){
        this.percentagePoint = percentagePoint;
    }
    public void    setAttribute02(String attribute02){
        this.attribute02 = attribute02;
    }
    public void    setAttribute06(String attribute06){
        this.attribute06 = attribute06;
    }
    public void    setAttribute03(String attribute03){
        this.attribute03 = attribute03;
    }
    public void    setAttribute04(String attribute04){
        this.attribute04 = attribute04;
    }
    public void    setAttribute05(String attribute05){
        this.attribute05 = attribute05;
    }
    public String getSelfValue(){
        return selfValue;
    }
    public String getFinalValue(){
        return finalValue;
    }
    public String getCheckComment(){
        return checkComment;
    }
    public String getCheckDocumentObid(){
        return checkDocumentObid;
    }
    public String getInputComment(){
        return inputComment;
    }
    public String getActivityObid(){
        return activityObid;
    }
    public String getCheckApplicationNames(){
        return checkApplicationNames;
    }
    public String getCheckApplicationTitle(){
        return checkApplicationTitle;
    }
    public Boolean getIsDoc(){
        return isDoc;
    }
    public String getFinalPoint(){
        return finalPoint;
    }
    public String getAttribute01(){
        return attribute01;
    }
    public String getComment(){
        return comment;
    }
    public String getPercentagePoint(){
        return percentagePoint;
    }
    public String getAttribute02(){
        return attribute02;
    }
    public String getAttribute06(){
        return attribute06;
    }
    public String getAttribute03(){
        return attribute03;
    }
    public String getAttribute04(){
        return attribute04;
    }
    public String getAttribute05(){
        return attribute05;
    }
}

