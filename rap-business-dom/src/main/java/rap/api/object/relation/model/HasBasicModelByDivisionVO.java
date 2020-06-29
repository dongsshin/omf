/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasBasicModelByDivisionVO.java
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
public class HasBasicModelByDivisionVO extends BusinessRelationObjectVO {
    private String        valueCode                                         ;
    private String        useFlag                                           ;


    public void    setValueCode(String valueCode){
        this.valueCode = valueCode;
    }
    public void    setUseFlag(String useFlag){
        this.useFlag = useFlag;
    }
    public String getValueCode(){
        return valueCode;
    }
    public String getUseFlag(){
        return useFlag;
    }
}

