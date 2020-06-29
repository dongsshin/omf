/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PartProjectMembersVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.partproject.model;


import com.rap.omc.api.object.model.BusinessObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class PartProjectMembersVO extends BusinessObjectVO {
    private String        partOwnerRole                                     ;
    private String        partOwnerRoleName                                 ;
    private String        mainFlag                                           = "N";
    private String        roleUserId                                        ;
    private String        reassignedUserId                                  ;
    private Integer       partActivitySorting                               ;


    public void    setPartOwnerRole(String partOwnerRole){
        this.partOwnerRole = partOwnerRole;
    }
    public void    setPartOwnerRoleName(String partOwnerRoleName){
        this.partOwnerRoleName = partOwnerRoleName;
    }
    public void    setMainFlag(String mainFlag){
        this.mainFlag = mainFlag;
    }
    public void    setRoleUserId(String roleUserId){
        this.roleUserId = roleUserId;
    }
    public void    setReassignedUserId(String reassignedUserId){
        this.reassignedUserId = reassignedUserId;
    }
    public void    setPartActivitySorting(Integer partActivitySorting){
        this.partActivitySorting = partActivitySorting;
    }
    public String getPartOwnerRole(){
        return partOwnerRole;
    }
    public String getPartOwnerRoleName(){
        return partOwnerRoleName;
    }
    public String getMainFlag(){
        return mainFlag;
    }
    public String getRoleUserId(){
        return roleUserId;
    }
    public String getReassignedUserId(){
        return reassignedUserId;
    }
    public Integer getPartActivitySorting(){
        return partActivitySorting;
    }
}

