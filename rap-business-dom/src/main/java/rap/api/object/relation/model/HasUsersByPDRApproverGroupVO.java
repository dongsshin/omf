/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasUsersByPDRApproverGroupVO.java
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
public class HasUsersByPDRApproverGroupVO extends BusinessRelationObjectVO {
    private String        roleType                                          ;
    private Integer       apprSeq                                           ;


    public void    setRoleType(String roleType){
        this.roleType = roleType;
    }
    public void    setApprSeq(Integer apprSeq){
        this.apprSeq = apprSeq;
    }
    public String getRoleType(){
        return roleType;
    }
    public Integer getApprSeq(){
        return apprSeq;
    }
}

