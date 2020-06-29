/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : AllocatedMyRoleVO.java
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
public class AllocatedMyRoleVO extends BusinessRelationObjectVO {
    private String        isMainMember                                       = "N";
    private String        workingType                                       ;


    public void    setIsMainMember(String isMainMember){
        this.isMainMember = isMainMember;
    }
    public void    setWorkingType(String workingType){
        this.workingType = workingType;
    }
    public String getIsMainMember(){
        return isMainMember;
    }
    public String getWorkingType(){
        return workingType;
    }
}

