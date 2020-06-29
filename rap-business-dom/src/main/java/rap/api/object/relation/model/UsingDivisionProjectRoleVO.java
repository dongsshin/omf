/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : UsingDivisionProjectRoleVO.java
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
public class UsingDivisionProjectRoleVO extends BusinessRelationObjectVO {
    private String        roleModuleType                                     = "Project";


    public void    setRoleModuleType(String roleModuleType){
        this.roleModuleType = roleModuleType;
    }
    public String getRoleModuleType(){
        return roleModuleType;
    }
}

