/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectDefinedRoleVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import rap.api.object.project.model.ProjectMembersVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ProjectDefinedRoleVO extends ProjectMembersVO {
    private Integer       sequences                                          = 100;
    private String        roleType                                           = "From Template";
    private String        roleCode                                          ;


    public void    setSequences(Integer sequences){
        this.sequences = sequences;
    }
    public void    setRoleType(String roleType){
        this.roleType = roleType;
    }
    public void    setRoleCode(String roleCode){
        this.roleCode = roleCode;
    }
    public Integer getSequences(){
        return sequences;
    }
    public String getRoleType(){
        return roleType;
    }
    public String getRoleCode(){
        return roleCode;
    }
}

