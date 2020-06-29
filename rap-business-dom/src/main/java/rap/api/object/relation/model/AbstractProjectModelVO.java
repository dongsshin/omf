/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : AbstractProjectModelVO.java
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
public class AbstractProjectModelVO extends BusinessRelationObjectVO {
    private String        representativeModelFlag                           ;
    private String        masterSystem                                       = "GPDM";
    private String        modelStatus                                       ;
    private String        modelCategory                                      = "-";


    public void    setRepresentativeModelFlag(String representativeModelFlag){
        this.representativeModelFlag = representativeModelFlag;
    }
    public void    setMasterSystem(String masterSystem){
        this.masterSystem = masterSystem;
    }
    public void    setModelStatus(String modelStatus){
        this.modelStatus = modelStatus;
    }
    public void    setModelCategory(String modelCategory){
        this.modelCategory = modelCategory;
    }
    public String getRepresentativeModelFlag(){
        return representativeModelFlag;
    }
    public String getMasterSystem(){
        return masterSystem;
    }
    public String getModelStatus(){
        return modelStatus;
    }
    public String getModelCategory(){
        return modelCategory;
    }
}

