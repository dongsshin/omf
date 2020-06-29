/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ApprovalLineDetailVO.java
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
public class ApprovalLineDetailVO extends BusinessObjectMasterVO {
    private Integer       sequences                                         ;
    private String        approvalType                                      ;
    private String        setType                                           ;
    private String        approver                                          ;
    private String        jobGrade                                          ;
    private String        role                                              ;
    private String        mailYn                                             = "N";
    private String        approverType                                      ;
    private String        evaluationYn                                       = "N";


    public void    setSequences(Integer sequences){
        this.sequences = sequences;
    }
    public void    setApprovalType(String approvalType){
        this.approvalType = approvalType;
    }
    public void    setSetType(String setType){
        this.setType = setType;
    }
    public void    setApprover(String approver){
        this.approver = approver;
    }
    public void    setJobGrade(String jobGrade){
        this.jobGrade = jobGrade;
    }
    public void    setRole(String role){
        this.role = role;
    }
    public void    setMailYn(String mailYn){
        this.mailYn = mailYn;
    }
    public void    setApproverType(String approverType){
        this.approverType = approverType;
    }
    public void    setEvaluationYn(String evaluationYn){
        this.evaluationYn = evaluationYn;
    }
    public Integer getSequences(){
        return sequences;
    }
    public String getApprovalType(){
        return approvalType;
    }
    public String getSetType(){
        return setType;
    }
    public String getApprover(){
        return approver;
    }
    public String getJobGrade(){
        return jobGrade;
    }
    public String getRole(){
        return role;
    }
    public String getMailYn(){
        return mailYn;
    }
    public String getApproverType(){
        return approverType;
    }
    public String getEvaluationYn(){
        return evaluationYn;
    }
}

