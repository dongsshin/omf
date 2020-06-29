/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : SpecItemValueLogVO.java
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
public class SpecItemValueLogVO extends BusinessObjectMasterVO {
    private String        fromObid                                          ;
    private String        opType                                            ;
    private String        specItemObid                                      ;
    private String        item1stValue                                      ;
    private String        item1stValueDesc                                  ;
    private String        item2ndValue                                      ;
    private String        item2ndValueDesc                                  ;
    private String        attachFileId                                      ;
    private String        useFlag                                           ;
    private String        interfaceCode                                     ;
    private String        attribute2                                        ;
    private String        attribute3                                        ;
    private String        attribute4                                        ;
    private String        attribute5                                        ;
    private Integer       orderValue                                        ;


    public void    setFromObid(String fromObid){
        this.fromObid = fromObid;
    }
    public void    setOpType(String opType){
        this.opType = opType;
    }
    public void    setSpecItemObid(String specItemObid){
        this.specItemObid = specItemObid;
    }
    public void    setItem1stValue(String item1stValue){
        this.item1stValue = item1stValue;
    }
    public void    setItem1stValueDesc(String item1stValueDesc){
        this.item1stValueDesc = item1stValueDesc;
    }
    public void    setItem2ndValue(String item2ndValue){
        this.item2ndValue = item2ndValue;
    }
    public void    setItem2ndValueDesc(String item2ndValueDesc){
        this.item2ndValueDesc = item2ndValueDesc;
    }
    public void    setAttachFileId(String attachFileId){
        this.attachFileId = attachFileId;
    }
    public void    setUseFlag(String useFlag){
        this.useFlag = useFlag;
    }
    public void    setInterfaceCode(String interfaceCode){
        this.interfaceCode = interfaceCode;
    }
    public void    setAttribute2(String attribute2){
        this.attribute2 = attribute2;
    }
    public void    setAttribute3(String attribute3){
        this.attribute3 = attribute3;
    }
    public void    setAttribute4(String attribute4){
        this.attribute4 = attribute4;
    }
    public void    setAttribute5(String attribute5){
        this.attribute5 = attribute5;
    }
    public void    setOrderValue(Integer orderValue){
        this.orderValue = orderValue;
    }
    public String getFromObid(){
        return fromObid;
    }
    public String getOpType(){
        return opType;
    }
    public String getSpecItemObid(){
        return specItemObid;
    }
    public String getItem1stValue(){
        return item1stValue;
    }
    public String getItem1stValueDesc(){
        return item1stValueDesc;
    }
    public String getItem2ndValue(){
        return item2ndValue;
    }
    public String getItem2ndValueDesc(){
        return item2ndValueDesc;
    }
    public String getAttachFileId(){
        return attachFileId;
    }
    public String getUseFlag(){
        return useFlag;
    }
    public String getInterfaceCode(){
        return interfaceCode;
    }
    public String getAttribute2(){
        return attribute2;
    }
    public String getAttribute3(){
        return attribute3;
    }
    public String getAttribute4(){
        return attribute4;
    }
    public String getAttribute5(){
        return attribute5;
    }
    public Integer getOrderValue(){
        return orderValue;
    }
}

