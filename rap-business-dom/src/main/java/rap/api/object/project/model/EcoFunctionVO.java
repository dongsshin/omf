/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : EcoFunctionVO.java
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
public class EcoFunctionVO extends BusinessObjectMasterVO {
    private String        upperFuncCode                                     ;
    private Integer       funcLevel                                         ;
    private String        useFlag                                           ;


    public void    setUpperFuncCode(String upperFuncCode){
        this.upperFuncCode = upperFuncCode;
    }
    public void    setFuncLevel(Integer funcLevel){
        this.funcLevel = funcLevel;
    }
    public void    setUseFlag(String useFlag){
        this.useFlag = useFlag;
    }
    public String getUpperFuncCode(){
        return upperFuncCode;
    }
    public Integer getFuncLevel(){
        return funcLevel;
    }
    public String getUseFlag(){
        return useFlag;
    }
}

