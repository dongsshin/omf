/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PartProjectIssueVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.partproject.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class PartProjectIssueVO extends BusinessObjectMasterVO {
    private String        issueCreationType                                 ;
    private String        issueTitle                                        ;
    private String        commander                                         ;
    private Date          requestedCompletedDate                            ;
    private Date          completedDate                                     ;
    private String        urgent                                            ;
    private String        issueCategory                                     ;
    private String        issueContents                                     ;
    private String        issueResults                                      ;
    private String        issueStatusCode                                   ;


    public void    setIssueCreationType(String issueCreationType){
        this.issueCreationType = issueCreationType;
    }
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
    public void    setCompletedDate(Date completedDate){
        this.completedDate = completedDate;
    }
    public void    setCompletedDate(String    completedDate){
        this.completedDate = this.omcConvertStr2Date(completedDate);
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
    public String getIssueCreationType(){
        return issueCreationType;
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
    public Date getCompletedDate(){
        return completedDate;
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
}

