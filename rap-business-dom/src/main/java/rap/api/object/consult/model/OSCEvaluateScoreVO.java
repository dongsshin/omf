/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : OSCEvaluateScoreVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.consult.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class OSCEvaluateScoreVO extends BusinessObjectMasterVO {
    private String        evalScoreCode                                     ;
    private String        evalScoreDescr                                    ;
    private String        useYn                                             ;


    public void    setEvalScoreCode(String evalScoreCode){
        this.evalScoreCode = evalScoreCode;
    }
    public void    setEvalScoreDescr(String evalScoreDescr){
        this.evalScoreDescr = evalScoreDescr;
    }
    public void    setUseYn(String useYn){
        this.useYn = useYn;
    }
    public String getEvalScoreCode(){
        return evalScoreCode;
    }
    public String getEvalScoreDescr(){
        return evalScoreDescr;
    }
    public String getUseYn(){
        return useYn;
    }
}

