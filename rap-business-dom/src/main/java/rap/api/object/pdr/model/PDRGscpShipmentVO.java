/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PDRGscpShipmentVO.java
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
public class PDRGscpShipmentVO extends BusinessObjectMasterVO {
    private String        workDate                                          ;
    private String        divisionCode                                       = "WWZ";
    private Integer       seq                                               ;
    private String        salesModelName                                    ;
    private String        salesModelSuffixCode                              ;
    private String        fromAffiliateCode                                 ;
    private Integer       shippingWeekPlan                                   = 0;
    private Integer       shippingWeekVolume                                 = 0;
    private String        itemName                                          ;
    private String        attribute1                                        ;
    private String        attribute2                                        ;
    private String        attribute3                                        ;
    private String        attribute4                                        ;
    private String        attribute5                                        ;


    public void    setWorkDate(String workDate){
        this.workDate = workDate;
    }
    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setSeq(Integer seq){
        this.seq = seq;
    }
    public void    setSalesModelName(String salesModelName){
        this.salesModelName = salesModelName;
    }
    public void    setSalesModelSuffixCode(String salesModelSuffixCode){
        this.salesModelSuffixCode = salesModelSuffixCode;
    }
    public void    setFromAffiliateCode(String fromAffiliateCode){
        this.fromAffiliateCode = fromAffiliateCode;
    }
    public void    setShippingWeekPlan(Integer shippingWeekPlan){
        this.shippingWeekPlan = shippingWeekPlan;
    }
    public void    setShippingWeekVolume(Integer shippingWeekVolume){
        this.shippingWeekVolume = shippingWeekVolume;
    }
    public void    setItemName(String itemName){
        this.itemName = itemName;
    }
    public void    setAttribute1(String attribute1){
        this.attribute1 = attribute1;
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
    public String getWorkDate(){
        return workDate;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public Integer getSeq(){
        return seq;
    }
    public String getSalesModelName(){
        return salesModelName;
    }
    public String getSalesModelSuffixCode(){
        return salesModelSuffixCode;
    }
    public String getFromAffiliateCode(){
        return fromAffiliateCode;
    }
    public Integer getShippingWeekPlan(){
        return shippingWeekPlan;
    }
    public Integer getShippingWeekVolume(){
        return shippingWeekVolume;
    }
    public String getItemName(){
        return itemName;
    }
    public String getAttribute1(){
        return attribute1;
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
}

