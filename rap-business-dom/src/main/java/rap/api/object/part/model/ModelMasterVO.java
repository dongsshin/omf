/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ModelMasterVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.part.model;


import rap.api.object.part.model.PartMasterRootVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ModelMasterVO extends PartMasterRootVO {
    private String        cpUnitCode                                        ;
    private String        oldModelCode                                      ;


    public void    setCpUnitCode(String cpUnitCode){
        this.cpUnitCode = cpUnitCode;
    }
    public void    setOldModelCode(String oldModelCode){
        this.oldModelCode = oldModelCode;
    }
    public String getCpUnitCode(){
        return cpUnitCode;
    }
    public String getOldModelCode(){
        return oldModelCode;
    }
}

