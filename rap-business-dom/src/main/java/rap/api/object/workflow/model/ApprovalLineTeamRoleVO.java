/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ApprovalLineTeamRoleVO.java
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
public class ApprovalLineTeamRoleVO extends BusinessObjectMasterVO {
    private String        fromObid                                          ;
    private String        userId                                            ;
    private String        roleCode                                          ;


    public void    setFromObid(String fromObid){
        this.fromObid = fromObid;
    }
    public void    setUserId(String userId){
        this.userId = userId;
    }
    public void    setRoleCode(String roleCode){
        this.roleCode = roleCode;
    }
    public String getFromObid(){
        return fromObid;
    }
    public String getUserId(){
        return userId;
    }
    public String getRoleCode(){
        return roleCode;
    }
}

