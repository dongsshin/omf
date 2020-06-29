/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ConsultFormEssentialFileVO.java
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
public class ConsultFormEssentialFileVO extends BusinessObjectMasterVO {
    private String        consAcctCd                                        ;
    private Integer       sno                                               ;
    private String        appendNm                                          ;
    private String        frnCtrYn                                           = "N";
    private String        appendEngNm                                       ;


    public void    setConsAcctCd(String consAcctCd){
        this.consAcctCd = consAcctCd;
    }
    public void    setSno(Integer sno){
        this.sno = sno;
    }
    public void    setAppendNm(String appendNm){
        this.appendNm = appendNm;
    }
    public void    setFrnCtrYn(String frnCtrYn){
        this.frnCtrYn = frnCtrYn;
    }
    public void    setAppendEngNm(String appendEngNm){
        this.appendEngNm = appendEngNm;
    }
    public String getConsAcctCd(){
        return consAcctCd;
    }
    public Integer getSno(){
        return sno;
    }
    public String getAppendNm(){
        return appendNm;
    }
    public String getFrnCtrYn(){
        return frnCtrYn;
    }
    public String getAppendEngNm(){
        return appendEngNm;
    }
}

