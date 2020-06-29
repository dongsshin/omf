/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : WBSPhaseTemplateVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import rap.api.object.project.model.WBSItemTemplatesVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class WBSPhaseTemplateVO extends WBSItemTemplatesVO {
    private String        projectPhaseName                                   = "None";


    public void    setProjectPhaseName(String projectPhaseName){
        this.projectPhaseName = projectPhaseName;
    }
    public String getProjectPhaseName(){
        return projectPhaseName;
    }
}

