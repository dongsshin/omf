/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : OSCEvaluateItemVO.java
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
public class OSCEvaluateItemVO extends BusinessObjectMasterVO {
    private String        catCode                                           ;
    private String        evalCode                                          ;
    private String        upEvalCode                                        ;
    private Integer       cdLevel                                           ;
    private String        evalNm                                            ;
    private Float         evalWeight                                        ;
    private Integer       sortOrd                                           ;
    private String        evalBasis                                         ;
    private String        useYn                                             ;


    public void    setCatCode(String catCode){
        this.catCode = catCode;
    }
    public void    setEvalCode(String evalCode){
        this.evalCode = evalCode;
    }
    public void    setUpEvalCode(String upEvalCode){
        this.upEvalCode = upEvalCode;
    }
    public void    setCdLevel(Integer cdLevel){
        this.cdLevel = cdLevel;
    }
    public void    setEvalNm(String evalNm){
        this.evalNm = evalNm;
    }
    public void    setEvalWeight(Float evalWeight){
        this.evalWeight = evalWeight;
    }
    public void    setSortOrd(Integer sortOrd){
        this.sortOrd = sortOrd;
    }
    public void    setEvalBasis(String evalBasis){
        this.evalBasis = evalBasis;
    }
    public void    setUseYn(String useYn){
        this.useYn = useYn;
    }
    public String getCatCode(){
        return catCode;
    }
    public String getEvalCode(){
        return evalCode;
    }
    public String getUpEvalCode(){
        return upEvalCode;
    }
    public Integer getCdLevel(){
        return cdLevel;
    }
    public String getEvalNm(){
        return evalNm;
    }
    public Float getEvalWeight(){
        return evalWeight;
    }
    public Integer getSortOrd(){
        return sortOrd;
    }
    public String getEvalBasis(){
        return evalBasis;
    }
    public String getUseYn(){
        return useYn;
    }
}

