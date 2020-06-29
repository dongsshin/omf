/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectDevelopmentTypeVO.java
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
public class ProjectDevelopmentTypeVO extends BusinessObjectMasterVO {
    private String        appliedClassList                                  ;


    public void    setAppliedClassList(String appliedClassList){
        this.appliedClassList = appliedClassList;
    }
    public String getAppliedClassList(){
        return appliedClassList;
    }
}

