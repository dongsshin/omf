/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectMembersVO.java
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
public class ProjectMembersVO extends BusinessObjectMasterVO {
    private String        projectCode                                       ;


    public void    setProjectCode(String projectCode){
        this.projectCode = projectCode;
    }
    public String getProjectCode(){
        return projectCode;
    }
}

