/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectIssueVO.java
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
public class ProjectIssueVO extends BusinessObjectMasterVO {
    private String        issueTitle                                        ;
    private String        commander                                         ;
    private Date          requestedCompletedDate                            ;
    private String        urgent                                            ;
    private String        issueCategory                                     ;
    private String        issueContents                                     ;
    private String        issueResults                                      ;
    private String        issueStatusCode                                   ;
    private String        reviewId                                          ;
    private String        checkPointId                                      ;
    private String        jiraNo                                            ;


    public void    setIssueTitle(String issueTitle){
        this.issueTitle = issueTitle;
    }
    public void    setCommander(String commander){
        this.commander = commander;
    }
    public void    setRequestedCompletedDate(Date requestedCompletedDate){
        this.requestedCompletedDate = requestedCompletedDate;
    }
    public void    setRequestedCompletedDate(String    requestedCompletedDate){
        this.requestedCompletedDate = this.omcConvertStr2Date(requestedCompletedDate);
    }
    public void    setUrgent(String urgent){
        this.urgent = urgent;
    }
    public void    setIssueCategory(String issueCategory){
        this.issueCategory = issueCategory;
    }
    public void    setIssueContents(String issueContents){
        this.issueContents = issueContents;
    }
    public void    setIssueResults(String issueResults){
        this.issueResults = issueResults;
    }
    public void    setIssueStatusCode(String issueStatusCode){
        this.issueStatusCode = issueStatusCode;
    }
    public void    setReviewId(String reviewId){
        this.reviewId = reviewId;
    }
    public void    setCheckPointId(String checkPointId){
        this.checkPointId = checkPointId;
    }
    public void    setJiraNo(String jiraNo){
        this.jiraNo = jiraNo;
    }
    public String getIssueTitle(){
        return issueTitle;
    }
    public String getCommander(){
        return commander;
    }
    public Date getRequestedCompletedDate(){
        return requestedCompletedDate;
    }
    public String getUrgent(){
        return urgent;
    }
    public String getIssueCategory(){
        return issueCategory;
    }
    public String getIssueContents(){
        return issueContents;
    }
    public String getIssueResults(){
        return issueResults;
    }
    public String getIssueStatusCode(){
        return issueStatusCode;
    }
    public String getReviewId(){
        return reviewId;
    }
    public String getCheckPointId(){
        return checkPointId;
    }
    public String getJiraNo(){
        return jiraNo;
    }
}

