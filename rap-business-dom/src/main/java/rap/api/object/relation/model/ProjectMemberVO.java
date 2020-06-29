/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectMemberVO.java
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
public class ProjectMemberVO extends BusinessRelationObjectVO {
    private String        projectRoleCode                                   ;
    private String        projectRoleName                                   ;
    private String        category                                          ;
    private String        hasRole                                            = "Y";
    private String        mainFlag                                          ;


    public void    setProjectRoleCode(String projectRoleCode){
        this.projectRoleCode = projectRoleCode;
    }
    public void    setProjectRoleName(String projectRoleName){
        this.projectRoleName = projectRoleName;
    }
    public void    setCategory(String category){
        this.category = category;
    }
    public void    setHasRole(String hasRole){
        this.hasRole = hasRole;
    }
    public void    setMainFlag(String mainFlag){
        this.mainFlag = mainFlag;
    }
    public String getProjectRoleCode(){
        return projectRoleCode;
    }
    public String getProjectRoleName(){
        return projectRoleName;
    }
    public String getCategory(){
        return category;
    }
    public String getHasRole(){
        return hasRole;
    }
    public String getMainFlag(){
        return mainFlag;
    }
}

