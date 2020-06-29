/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : OSCConsultTempVO.java
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
public class OSCConsultTempVO extends BusinessObjectMasterVO {
    private String        oscSeq                                            ;
    private String        supplierSelectMethod                              ;
    private String        inAdvanceInputYn                                  ;
    private String        inAdvanceInputReason                              ;


    public void    setOscSeq(String oscSeq){
        this.oscSeq = oscSeq;
    }
    public void    setSupplierSelectMethod(String supplierSelectMethod){
        this.supplierSelectMethod = supplierSelectMethod;
    }
    public void    setInAdvanceInputYn(String inAdvanceInputYn){
        this.inAdvanceInputYn = inAdvanceInputYn;
    }
    public void    setInAdvanceInputReason(String inAdvanceInputReason){
        this.inAdvanceInputReason = inAdvanceInputReason;
    }
    public String getOscSeq(){
        return oscSeq;
    }
    public String getSupplierSelectMethod(){
        return supplierSelectMethod;
    }
    public String getInAdvanceInputYn(){
        return inAdvanceInputYn;
    }
    public String getInAdvanceInputReason(){
        return inAdvanceInputReason;
    }
}

