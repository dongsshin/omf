/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : TargetValueForPDRVO.java
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
public class TargetValueForPDRVO extends BusinessObjectMasterVO {
    private String        targetId                                          ;
    private Integer       targetValueSeq                                    ;
    private String        targetValueCode                                   ;
    private String        target1stValue                                    ;
    private String        target2ndValue                                    ;


    public void    setTargetId(String targetId){
        this.targetId = targetId;
    }
    public void    setTargetValueSeq(Integer targetValueSeq){
        this.targetValueSeq = targetValueSeq;
    }
    public void    setTargetValueCode(String targetValueCode){
        this.targetValueCode = targetValueCode;
    }
    public void    setTarget1stValue(String target1stValue){
        this.target1stValue = target1stValue;
    }
    public void    setTarget2ndValue(String target2ndValue){
        this.target2ndValue = target2ndValue;
    }
    public String getTargetId(){
        return targetId;
    }
    public Integer getTargetValueSeq(){
        return targetValueSeq;
    }
    public String getTargetValueCode(){
        return targetValueCode;
    }
    public String getTarget1stValue(){
        return target1stValue;
    }
    public String getTarget2ndValue(){
        return target2ndValue;
    }
}

