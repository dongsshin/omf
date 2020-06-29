/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PartProjectExcelPersonVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.partproject.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class PartProjectExcelPersonVO extends BusinessObjectMasterVO {
    private String        xlsxRowNo                                         ;
    private String        partOwnerRole                                     ;
    private String        partOwnerRoleName                                 ;
    private String        roleUserName                                      ;
    private String        roleUserSso                                       ;
    private String        findUserId                                        ;


    public void    setXlsxRowNo(String xlsxRowNo){
        this.xlsxRowNo = xlsxRowNo;
    }
    public void    setPartOwnerRole(String partOwnerRole){
        this.partOwnerRole = partOwnerRole;
    }
    public void    setPartOwnerRoleName(String partOwnerRoleName){
        this.partOwnerRoleName = partOwnerRoleName;
    }
    public void    setRoleUserName(String roleUserName){
        this.roleUserName = roleUserName;
    }
    public void    setRoleUserSso(String roleUserSso){
        this.roleUserSso = roleUserSso;
    }
    public void    setFindUserId(String findUserId){
        this.findUserId = findUserId;
    }
    public String getXlsxRowNo(){
        return xlsxRowNo;
    }
    public String getPartOwnerRole(){
        return partOwnerRole;
    }
    public String getPartOwnerRoleName(){
        return partOwnerRoleName;
    }
    public String getRoleUserName(){
        return roleUserName;
    }
    public String getRoleUserSso(){
        return roleUserSso;
    }
    public String getFindUserId(){
        return findUserId;
    }
}

