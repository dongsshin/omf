/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : UsingReviewTypeVO.java
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
public class UsingReviewTypeVO extends BusinessRelationObjectVO {
    private String        reviewName                                        ;
    private String        reviewNameEng                                     ;
    private Integer       sortOrderNo                                       ;
    private String        useFlag                                           ;
    private String        activityCode                                      ;


    public void    setReviewName(String reviewName){
        this.reviewName = reviewName;
    }
    public void    setReviewNameEng(String reviewNameEng){
        this.reviewNameEng = reviewNameEng;
    }
    public void    setSortOrderNo(Integer sortOrderNo){
        this.sortOrderNo = sortOrderNo;
    }
    public void    setUseFlag(String useFlag){
        this.useFlag = useFlag;
    }
    public void    setActivityCode(String activityCode){
        this.activityCode = activityCode;
    }
    public String getReviewName(){
        return reviewName;
    }
    public String getReviewNameEng(){
        return reviewNameEng;
    }
    public Integer getSortOrderNo(){
        return sortOrderNo;
    }
    public String getUseFlag(){
        return useFlag;
    }
    public String getActivityCode(){
        return activityCode;
    }
}

