/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : AwardB2BProjectCodeVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.budget.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class AwardB2BProjectCodeVO extends BusinessObjectMasterVO {
    private String        b2bProjectCode                                    ;
    private String        b2bProjectNameKor                                 ;
    private String        b2bProjectNameEng                                 ;
    private String        awardYyyymmdd                                     ;
    private String        projectStatus                                     ;
    private String        approveComment                                    ;


    public void    setB2bProjectCode(String b2bProjectCode){
        this.b2bProjectCode = b2bProjectCode;
    }
    public void    setB2bProjectNameKor(String b2bProjectNameKor){
        this.b2bProjectNameKor = b2bProjectNameKor;
    }
    public void    setB2bProjectNameEng(String b2bProjectNameEng){
        this.b2bProjectNameEng = b2bProjectNameEng;
    }
    public void    setAwardYyyymmdd(String awardYyyymmdd){
        this.awardYyyymmdd = awardYyyymmdd;
    }
    public void    setProjectStatus(String projectStatus){
        this.projectStatus = projectStatus;
    }
    public void    setApproveComment(String approveComment){
        this.approveComment = approveComment;
    }
    public String getB2bProjectCode(){
        return b2bProjectCode;
    }
    public String getB2bProjectNameKor(){
        return b2bProjectNameKor;
    }
    public String getB2bProjectNameEng(){
        return b2bProjectNameEng;
    }
    public String getAwardYyyymmdd(){
        return awardYyyymmdd;
    }
    public String getProjectStatus(){
        return projectStatus;
    }
    public String getApproveComment(){
        return approveComment;
    }
}

