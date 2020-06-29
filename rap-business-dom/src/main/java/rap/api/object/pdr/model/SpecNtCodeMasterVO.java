/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : SpecNtCodeMasterVO.java
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
public class SpecNtCodeMasterVO extends BusinessObjectMasterVO {
    private String        customerName                                      ;
    private String        customerSuffixCode                                ;
    private Integer       customerSuffixCodeOrder                           ;
    private String        mcc                                               ;
    private String        mnc                                               ;
    private String        gid1                                              ;
    private String        gid2                                              ;
    private String        subset                                            ;
    private String        ntcodeString                                      ;
    private String        attribute1                                        ;


    public void    setCustomerName(String customerName){
        this.customerName = customerName;
    }
    public void    setCustomerSuffixCode(String customerSuffixCode){
        this.customerSuffixCode = customerSuffixCode;
    }
    public void    setCustomerSuffixCodeOrder(Integer customerSuffixCodeOrder){
        this.customerSuffixCodeOrder = customerSuffixCodeOrder;
    }
    public void    setMcc(String mcc){
        this.mcc = mcc;
    }
    public void    setMnc(String mnc){
        this.mnc = mnc;
    }
    public void    setGid1(String gid1){
        this.gid1 = gid1;
    }
    public void    setGid2(String gid2){
        this.gid2 = gid2;
    }
    public void    setSubset(String subset){
        this.subset = subset;
    }
    public void    setNtcodeString(String ntcodeString){
        this.ntcodeString = ntcodeString;
    }
    public void    setAttribute1(String attribute1){
        this.attribute1 = attribute1;
    }
    public String getCustomerName(){
        return customerName;
    }
    public String getCustomerSuffixCode(){
        return customerSuffixCode;
    }
    public Integer getCustomerSuffixCodeOrder(){
        return customerSuffixCodeOrder;
    }
    public String getMcc(){
        return mcc;
    }
    public String getMnc(){
        return mnc;
    }
    public String getGid1(){
        return gid1;
    }
    public String getGid2(){
        return gid2;
    }
    public String getSubset(){
        return subset;
    }
    public String getNtcodeString(){
        return ntcodeString;
    }
    public String getAttribute1(){
        return attribute1;
    }
}

