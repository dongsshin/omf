/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectRoleVO.java
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
public class ProjectRoleVO extends BusinessObjectMasterVO {
    private Integer       sequences                                          = 100;
    private String        roleType                                           = "Group";
    private String        roleNameKor                                       ;
    private String        roleNameEng                                       ;
    private String        roleNameChi                                       ;
    private String        roleModuleType                                     = "Project";
    private String        roleCategory                                       = "H/W";


    public void    setSequences(Integer sequences){
        this.sequences = sequences;
    }
    public void    setRoleType(String roleType){
        this.roleType = roleType;
    }
    public void    setRoleNameKor(String roleNameKor){
        this.roleNameKor = roleNameKor;
    }
    public void    setRoleNameEng(String roleNameEng){
        this.roleNameEng = roleNameEng;
    }
    public void    setRoleNameChi(String roleNameChi){
        this.roleNameChi = roleNameChi;
    }
    public void    setRoleModuleType(String roleModuleType){
        this.roleModuleType = roleModuleType;
    }
    public void    setRoleCategory(String roleCategory){
        this.roleCategory = roleCategory;
    }
    public Integer getSequences(){
        return sequences;
    }
    public String getRoleType(){
        return roleType;
    }
    public String getRoleNameKor(){
        return roleNameKor;
    }
    public String getRoleNameEng(){
        return roleNameEng;
    }
    public String getRoleNameChi(){
        return roleNameChi;
    }
    public String getRoleModuleType(){
        return roleModuleType;
    }
    public String getRoleCategory(){
        return roleCategory;
    }
}

