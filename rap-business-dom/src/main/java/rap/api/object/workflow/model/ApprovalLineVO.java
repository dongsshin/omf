/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ApprovalLineVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.workflow.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ApprovalLineVO extends BusinessObjectMasterVO {
    private String        appliedType                                       ;
    private String        appliedPolicy                                     ;
    private Boolean       isDefault                                          = (Boolean)false;
    private String        manageType                                        ;
    private String        plantName                                         ;


    public void    setAppliedType(String appliedType){
        this.appliedType = appliedType;
    }
    public void    setAppliedPolicy(String appliedPolicy){
        this.appliedPolicy = appliedPolicy;
    }
    public void    setIsDefault(Boolean isDefault){
        this.isDefault = isDefault;
    }
    public void    setManageType(String manageType){
        this.manageType = manageType;
    }
    public void    setPlantName(String plantName){
        this.plantName = plantName;
    }
    public String getAppliedType(){
        return appliedType;
    }
    public String getAppliedPolicy(){
        return appliedPolicy;
    }
    public Boolean getIsDefault(){
        return isDefault;
    }
    public String getManageType(){
        return manageType;
    }
    public String getPlantName(){
        return plantName;
    }
}

