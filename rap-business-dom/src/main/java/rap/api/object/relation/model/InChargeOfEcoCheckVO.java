/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : InChargeOfEcoCheckVO.java
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
public class InChargeOfEcoCheckVO extends BusinessRelationObjectVO {
    private String        ecoRoleCode                                       ;
    private String        selfFinalFlag                                     ;


    public void    setEcoRoleCode(String ecoRoleCode){
        this.ecoRoleCode = ecoRoleCode;
    }
    public void    setSelfFinalFlag(String selfFinalFlag){
        this.selfFinalFlag = selfFinalFlag;
    }
    public String getEcoRoleCode(){
        return ecoRoleCode;
    }
    public String getSelfFinalFlag(){
        return selfFinalFlag;
    }
}

