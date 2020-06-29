/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ExpenseProjectVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.bizplan.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ExpenseProjectVO extends BusinessObjectMasterVO {
    private String        projectCode                                       ;
    private String        projectLocalName                                  ;
    private String        projectEngName                                    ;
    private String        legalEntityName                                   ;
    private String        enabledFlag                                       ;


    public void    setProjectCode(String projectCode){
        this.projectCode = projectCode;
    }
    public void    setProjectLocalName(String projectLocalName){
        this.projectLocalName = projectLocalName;
    }
    public void    setProjectEngName(String projectEngName){
        this.projectEngName = projectEngName;
    }
    public void    setLegalEntityName(String legalEntityName){
        this.legalEntityName = legalEntityName;
    }
    public void    setEnabledFlag(String enabledFlag){
        this.enabledFlag = enabledFlag;
    }
    public String getProjectCode(){
        return projectCode;
    }
    public String getProjectLocalName(){
        return projectLocalName;
    }
    public String getProjectEngName(){
        return projectEngName;
    }
    public String getLegalEntityName(){
        return legalEntityName;
    }
    public String getEnabledFlag(){
        return enabledFlag;
    }
}

