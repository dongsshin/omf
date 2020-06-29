/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : DQMSExamIssueVO.java
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
public class DQMSExamIssueVO extends BusinessObjectMasterVO {
    private Integer       reqId                                             ;
    private Integer       examMstId                                         ;
    private Integer       orgId                                             ;
    private String        issueType                                         ;
    private String        issueGrade                                        ;
    private String        defect                                            ;
    private String        total                                             ;
    private String        issueRegUser                                      ;
    private Date          issueRegDate                                      ;
    private Date          issueCloseDate                                    ;
    private String        issueDesc                                         ;
    private String        issueConsensus                                    ;
    private String        improveUser                                       ;
    private String        issueStatus                                       ;
    private String        issueCause                                        ;
    private String        issueImprove                                      ;
    private String        closeUser                                         ;
    private String        attribute01                                       ;
    private String        attribute02                                       ;
    private String        attribute03                                       ;
    private String        attribute04                                       ;
    private String        attribute05                                       ;
    private String        attribute06                                       ;
    private String        attribute07                                       ;
    private String        attribute08                                       ;
    private String        attribute09                                       ;
    private String        attribute10                                       ;
    private String        attribute11                                       ;
    private String        attribute12                                       ;
    private String        attribute13                                       ;
    private String        attribute14                                       ;
    private String        attribute15                                       ;
    private String        attribute16                                       ;
    private String        attribute17                                       ;
    private String        attribute18                                       ;
    private String        attribute19                                       ;
    private String        attribute20                                       ;


    public void    setReqId(Integer reqId){
        this.reqId = reqId;
    }
    public void    setExamMstId(Integer examMstId){
        this.examMstId = examMstId;
    }
    public void    setOrgId(Integer orgId){
        this.orgId = orgId;
    }
    public void    setIssueType(String issueType){
        this.issueType = issueType;
    }
    public void    setIssueGrade(String issueGrade){
        this.issueGrade = issueGrade;
    }
    public void    setDefect(String defect){
        this.defect = defect;
    }
    public void    setTotal(String total){
        this.total = total;
    }
    public void    setIssueRegUser(String issueRegUser){
        this.issueRegUser = issueRegUser;
    }
    public void    setIssueRegDate(Date issueRegDate){
        this.issueRegDate = issueRegDate;
    }
    public void    setIssueRegDate(String    issueRegDate){
        this.issueRegDate = this.omcConvertStr2Date(issueRegDate);
    }
    public void    setIssueCloseDate(Date issueCloseDate){
        this.issueCloseDate = issueCloseDate;
    }
    public void    setIssueCloseDate(String    issueCloseDate){
        this.issueCloseDate = this.omcConvertStr2Date(issueCloseDate);
    }
    public void    setIssueDesc(String issueDesc){
        this.issueDesc = issueDesc;
    }
    public void    setIssueConsensus(String issueConsensus){
        this.issueConsensus = issueConsensus;
    }
    public void    setImproveUser(String improveUser){
        this.improveUser = improveUser;
    }
    public void    setIssueStatus(String issueStatus){
        this.issueStatus = issueStatus;
    }
    public void    setIssueCause(String issueCause){
        this.issueCause = issueCause;
    }
    public void    setIssueImprove(String issueImprove){
        this.issueImprove = issueImprove;
    }
    public void    setCloseUser(String closeUser){
        this.closeUser = closeUser;
    }
    public void    setAttribute01(String attribute01){
        this.attribute01 = attribute01;
    }
    public void    setAttribute02(String attribute02){
        this.attribute02 = attribute02;
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
    public void    setAttribute06(String attribute06){
        this.attribute06 = attribute06;
    }
    public void    setAttribute07(String attribute07){
        this.attribute07 = attribute07;
    }
    public void    setAttribute08(String attribute08){
        this.attribute08 = attribute08;
    }
    public void    setAttribute09(String attribute09){
        this.attribute09 = attribute09;
    }
    public void    setAttribute10(String attribute10){
        this.attribute10 = attribute10;
    }
    public void    setAttribute11(String attribute11){
        this.attribute11 = attribute11;
    }
    public void    setAttribute12(String attribute12){
        this.attribute12 = attribute12;
    }
    public void    setAttribute13(String attribute13){
        this.attribute13 = attribute13;
    }
    public void    setAttribute14(String attribute14){
        this.attribute14 = attribute14;
    }
    public void    setAttribute15(String attribute15){
        this.attribute15 = attribute15;
    }
    public void    setAttribute16(String attribute16){
        this.attribute16 = attribute16;
    }
    public void    setAttribute17(String attribute17){
        this.attribute17 = attribute17;
    }
    public void    setAttribute18(String attribute18){
        this.attribute18 = attribute18;
    }
    public void    setAttribute19(String attribute19){
        this.attribute19 = attribute19;
    }
    public void    setAttribute20(String attribute20){
        this.attribute20 = attribute20;
    }
    public Integer getReqId(){
        return reqId;
    }
    public Integer getExamMstId(){
        return examMstId;
    }
    public Integer getOrgId(){
        return orgId;
    }
    public String getIssueType(){
        return issueType;
    }
    public String getIssueGrade(){
        return issueGrade;
    }
    public String getDefect(){
        return defect;
    }
    public String getTotal(){
        return total;
    }
    public String getIssueRegUser(){
        return issueRegUser;
    }
    public Date getIssueRegDate(){
        return issueRegDate;
    }
    public Date getIssueCloseDate(){
        return issueCloseDate;
    }
    public String getIssueDesc(){
        return issueDesc;
    }
    public String getIssueConsensus(){
        return issueConsensus;
    }
    public String getImproveUser(){
        return improveUser;
    }
    public String getIssueStatus(){
        return issueStatus;
    }
    public String getIssueCause(){
        return issueCause;
    }
    public String getIssueImprove(){
        return issueImprove;
    }
    public String getCloseUser(){
        return closeUser;
    }
    public String getAttribute01(){
        return attribute01;
    }
    public String getAttribute02(){
        return attribute02;
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
    public String getAttribute06(){
        return attribute06;
    }
    public String getAttribute07(){
        return attribute07;
    }
    public String getAttribute08(){
        return attribute08;
    }
    public String getAttribute09(){
        return attribute09;
    }
    public String getAttribute10(){
        return attribute10;
    }
    public String getAttribute11(){
        return attribute11;
    }
    public String getAttribute12(){
        return attribute12;
    }
    public String getAttribute13(){
        return attribute13;
    }
    public String getAttribute14(){
        return attribute14;
    }
    public String getAttribute15(){
        return attribute15;
    }
    public String getAttribute16(){
        return attribute16;
    }
    public String getAttribute17(){
        return attribute17;
    }
    public String getAttribute18(){
        return attribute18;
    }
    public String getAttribute19(){
        return attribute19;
    }
    public String getAttribute20(){
        return attribute20;
    }
}

