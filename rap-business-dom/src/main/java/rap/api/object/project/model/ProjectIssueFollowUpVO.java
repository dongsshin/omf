/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectIssueFollowUpVO.java
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
public class ProjectIssueFollowUpVO extends BusinessObjectMasterVO {
    private String        followUpComment                                   ;
    private String        issueStatusCode                                   ;
    private Integer       sortOrderNo                                       ;


    public void    setFollowUpComment(String followUpComment){
        this.followUpComment = followUpComment;
    }
    public void    setIssueStatusCode(String issueStatusCode){
        this.issueStatusCode = issueStatusCode;
    }
    public void    setSortOrderNo(Integer sortOrderNo){
        this.sortOrderNo = sortOrderNo;
    }
    public String getFollowUpComment(){
        return followUpComment;
    }
    public String getIssueStatusCode(){
        return issueStatusCode;
    }
    public Integer getSortOrderNo(){
        return sortOrderNo;
    }
}

