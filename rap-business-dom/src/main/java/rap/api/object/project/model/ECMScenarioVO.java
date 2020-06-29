/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ECMScenarioVO.java
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
public class ECMScenarioVO extends BusinessObjectMasterVO {
    private String        scenarioCode                                      ;
    private String        scenarioDesc                                      ;
    private String        scenarioStatus                                    ;
    private String        scenarioConfirmYn                                 ;


    public void    setScenarioCode(String scenarioCode){
        this.scenarioCode = scenarioCode;
    }
    public void    setScenarioDesc(String scenarioDesc){
        this.scenarioDesc = scenarioDesc;
    }
    public void    setScenarioStatus(String scenarioStatus){
        this.scenarioStatus = scenarioStatus;
    }
    public void    setScenarioConfirmYn(String scenarioConfirmYn){
        this.scenarioConfirmYn = scenarioConfirmYn;
    }
    public String getScenarioCode(){
        return scenarioCode;
    }
    public String getScenarioDesc(){
        return scenarioDesc;
    }
    public String getScenarioStatus(){
        return scenarioStatus;
    }
    public String getScenarioConfirmYn(){
        return scenarioConfirmYn;
    }
}

