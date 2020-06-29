/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectMemberSummaryVO.java
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
public class ProjectMemberSummaryVO extends BusinessObjectMasterVO {
    private String        fromObid                                          ;
    private Float         memberCount                                       ;
    private Float         undermannedCount                                  ;
    private String        category                                          ;


    public void    setFromObid(String fromObid){
        this.fromObid = fromObid;
    }
    public void    setMemberCount(Float memberCount){
        this.memberCount = memberCount;
    }
    public void    setUndermannedCount(Float undermannedCount){
        this.undermannedCount = undermannedCount;
    }
    public void    setCategory(String category){
        this.category = category;
    }
    public String getFromObid(){
        return fromObid;
    }
    public Float getMemberCount(){
        return memberCount;
    }
    public Float getUndermannedCount(){
        return undermannedCount;
    }
    public String getCategory(){
        return category;
    }
}

