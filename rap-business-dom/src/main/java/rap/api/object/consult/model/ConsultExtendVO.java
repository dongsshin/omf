/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ConsultExtendVO.java
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
public class ConsultExtendVO extends BusinessObjectMasterVO {
    private String        contracttype2                                     ;
    private Integer       totalMm                                           ;
    private String        mmInputType                                       ;
    private String        actualConsultObid                                 ;


    public void    setContracttype2(String contracttype2){
        this.contracttype2 = contracttype2;
    }
    public void    setTotalMm(Integer totalMm){
        this.totalMm = totalMm;
    }
    public void    setMmInputType(String mmInputType){
        this.mmInputType = mmInputType;
    }
    public void    setActualConsultObid(String actualConsultObid){
        this.actualConsultObid = actualConsultObid;
    }
    public String getContracttype2(){
        return contracttype2;
    }
    public Integer getTotalMm(){
        return totalMm;
    }
    public String getMmInputType(){
        return mmInputType;
    }
    public String getActualConsultObid(){
        return actualConsultObid;
    }
}

