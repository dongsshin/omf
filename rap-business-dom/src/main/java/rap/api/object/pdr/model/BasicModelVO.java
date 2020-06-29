/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : BasicModelVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.pdr.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class BasicModelVO extends BusinessObjectMasterVO {
    private String        baseModelCode                                     ;
    private String        useFlag                                           ;
    private String        toolName                                          ;
    private String        toolValueCode                                     ;
    private String        transferFlag                                      ;


    public void    setBaseModelCode(String baseModelCode){
        this.baseModelCode = baseModelCode;
    }
    public void    setUseFlag(String useFlag){
        this.useFlag = useFlag;
    }
    public void    setToolName(String toolName){
        this.toolName = toolName;
    }
    public void    setToolValueCode(String toolValueCode){
        this.toolValueCode = toolValueCode;
    }
    public void    setTransferFlag(String transferFlag){
        this.transferFlag = transferFlag;
    }
    public String getBaseModelCode(){
        return baseModelCode;
    }
    public String getUseFlag(){
        return useFlag;
    }
    public String getToolName(){
        return toolName;
    }
    public String getToolValueCode(){
        return toolValueCode;
    }
    public String getTransferFlag(){
        return transferFlag;
    }
}

