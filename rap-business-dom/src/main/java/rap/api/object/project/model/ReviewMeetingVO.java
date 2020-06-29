/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ReviewMeetingVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ReviewMeetingVO extends BusinessObjectMasterVO {
    private String        objectId                                          ;
    private String        divisionCode                                      ;
    private String        areaCode                                          ;
    private String        fromMeetingTime                                   ;
    private String        toMeetingTime                                     ;
    private String        meetingPlace                                      ;
    private String        ownerId                                           ;
    private String        meetingContents                                   ;
    private String        meetingTypeCode                                   ;
    private String        meetingComments                                   ;
    private String        reviewStatus                                       = "OK";
    private String        npiStatus                                         ;
    private String        npiNewYn                                          ;
    private String        activityObid                                      ;
    private String        inboxtaskObid                                     ;


    public void    setObjectId(String objectId){
        this.objectId = objectId;
    }
    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setAreaCode(String areaCode){
        this.areaCode = areaCode;
    }
    public void    setFromMeetingTime(String fromMeetingTime){
        this.fromMeetingTime = fromMeetingTime;
    }
    public void    setToMeetingTime(String toMeetingTime){
        this.toMeetingTime = toMeetingTime;
    }
    public void    setMeetingPlace(String meetingPlace){
        this.meetingPlace = meetingPlace;
    }
    public void    setOwnerId(String ownerId){
        this.ownerId = ownerId;
    }
    public void    setMeetingContents(String meetingContents){
        this.meetingContents = meetingContents;
    }
    public void    setMeetingTypeCode(String meetingTypeCode){
        this.meetingTypeCode = meetingTypeCode;
    }
    public void    setMeetingComments(String meetingComments){
        this.meetingComments = meetingComments;
    }
    public void    setReviewStatus(String reviewStatus){
        this.reviewStatus = reviewStatus;
    }
    public void    setNpiStatus(String npiStatus){
        this.npiStatus = npiStatus;
    }
    public void    setNpiNewYn(String npiNewYn){
        this.npiNewYn = npiNewYn;
    }
    public void    setActivityObid(String activityObid){
        this.activityObid = activityObid;
    }
    public void    setInboxtaskObid(String inboxtaskObid){
        this.inboxtaskObid = inboxtaskObid;
    }
    public String getObjectId(){
        return objectId;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getAreaCode(){
        return areaCode;
    }
    public String getFromMeetingTime(){
        return fromMeetingTime;
    }
    public String getToMeetingTime(){
        return toMeetingTime;
    }
    public String getMeetingPlace(){
        return meetingPlace;
    }
    public String getOwnerId(){
        return ownerId;
    }
    public String getMeetingContents(){
        return meetingContents;
    }
    public String getMeetingTypeCode(){
        return meetingTypeCode;
    }
    public String getMeetingComments(){
        return meetingComments;
    }
    public String getReviewStatus(){
        return reviewStatus;
    }
    public String getNpiStatus(){
        return npiStatus;
    }
    public String getNpiNewYn(){
        return npiNewYn;
    }
    public String getActivityObid(){
        return activityObid;
    }
    public String getInboxtaskObid(){
        return inboxtaskObid;
    }
}

