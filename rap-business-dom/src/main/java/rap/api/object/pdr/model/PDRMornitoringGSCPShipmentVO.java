/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PDRMornitoringGSCPShipmentVO.java
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
public class PDRMornitoringGSCPShipmentVO extends BusinessObjectMasterVO {
    private String        workDate                                          ;
    private String        divisionCode                                      ;
    private Integer       seq                                               ;
    private String        modelName                                         ;
    private String        modelSuffixCode                                   ;
    private String        team                                              ;
    private String        site                                              ;
    private String        bomRev                                            ;
    private String        buyerCode                                         ;
    private String        buyerName                                         ;
    private String        shipmentResult                                    ;
    private Integer       totalW1W6                                         ;
    private Integer       totalW7W8                                         ;
    private String        attribute1                                        ;
    private String        attribute2                                        ;
    private String        attribute3                                        ;
    private String        attribute4                                        ;
    private String        attribute5                                        ;
    private Integer       w1                                                ;
    private Integer       w2                                                ;
    private Integer       w3                                                ;
    private Integer       w4                                                ;
    private Integer       w5                                                ;
    private Integer       w6                                                ;
    private Integer       w7                                                ;
    private Integer       w8                                                ;
    private Integer       w9                                                ;
    private Integer       w10                                               ;
    private Integer       w11                                               ;
    private Integer       w12                                               ;
    private Integer       w13                                               ;
    private Integer       w14                                               ;
    private Integer       w15                                               ;
    private Integer       w16                                               ;
    private Integer       w17                                               ;
    private Integer       w18                                               ;
    private Integer       w19                                               ;
    private Integer       w20                                               ;
    private Integer       w21                                               ;
    private Integer       w22                                               ;
    private Integer       w23                                               ;
    private Integer       w24                                               ;
    private Integer       w25                                               ;
    private Integer       w26                                               ;


    public void    setWorkDate(String workDate){
        this.workDate = workDate;
    }
    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setSeq(Integer seq){
        this.seq = seq;
    }
    public void    setModelName(String modelName){
        this.modelName = modelName;
    }
    public void    setModelSuffixCode(String modelSuffixCode){
        this.modelSuffixCode = modelSuffixCode;
    }
    public void    setTeam(String team){
        this.team = team;
    }
    public void    setSite(String site){
        this.site = site;
    }
    public void    setBomRev(String bomRev){
        this.bomRev = bomRev;
    }
    public void    setBuyerCode(String buyerCode){
        this.buyerCode = buyerCode;
    }
    public void    setBuyerName(String buyerName){
        this.buyerName = buyerName;
    }
    public void    setShipmentResult(String shipmentResult){
        this.shipmentResult = shipmentResult;
    }
    public void    setTotalW1W6(Integer totalW1W6){
        this.totalW1W6 = totalW1W6;
    }
    public void    setTotalW7W8(Integer totalW7W8){
        this.totalW7W8 = totalW7W8;
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
    public void    setW1(Integer w1){
        this.w1 = w1;
    }
    public void    setW2(Integer w2){
        this.w2 = w2;
    }
    public void    setW3(Integer w3){
        this.w3 = w3;
    }
    public void    setW4(Integer w4){
        this.w4 = w4;
    }
    public void    setW5(Integer w5){
        this.w5 = w5;
    }
    public void    setW6(Integer w6){
        this.w6 = w6;
    }
    public void    setW7(Integer w7){
        this.w7 = w7;
    }
    public void    setW8(Integer w8){
        this.w8 = w8;
    }
    public void    setW9(Integer w9){
        this.w9 = w9;
    }
    public void    setW10(Integer w10){
        this.w10 = w10;
    }
    public void    setW11(Integer w11){
        this.w11 = w11;
    }
    public void    setW12(Integer w12){
        this.w12 = w12;
    }
    public void    setW13(Integer w13){
        this.w13 = w13;
    }
    public void    setW14(Integer w14){
        this.w14 = w14;
    }
    public void    setW15(Integer w15){
        this.w15 = w15;
    }
    public void    setW16(Integer w16){
        this.w16 = w16;
    }
    public void    setW17(Integer w17){
        this.w17 = w17;
    }
    public void    setW18(Integer w18){
        this.w18 = w18;
    }
    public void    setW19(Integer w19){
        this.w19 = w19;
    }
    public void    setW20(Integer w20){
        this.w20 = w20;
    }
    public void    setW21(Integer w21){
        this.w21 = w21;
    }
    public void    setW22(Integer w22){
        this.w22 = w22;
    }
    public void    setW23(Integer w23){
        this.w23 = w23;
    }
    public void    setW24(Integer w24){
        this.w24 = w24;
    }
    public void    setW25(Integer w25){
        this.w25 = w25;
    }
    public void    setW26(Integer w26){
        this.w26 = w26;
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
    public String getModelName(){
        return modelName;
    }
    public String getModelSuffixCode(){
        return modelSuffixCode;
    }
    public String getTeam(){
        return team;
    }
    public String getSite(){
        return site;
    }
    public String getBomRev(){
        return bomRev;
    }
    public String getBuyerCode(){
        return buyerCode;
    }
    public String getBuyerName(){
        return buyerName;
    }
    public String getShipmentResult(){
        return shipmentResult;
    }
    public Integer getTotalW1W6(){
        return totalW1W6;
    }
    public Integer getTotalW7W8(){
        return totalW7W8;
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
    public Integer getW1(){
        return w1;
    }
    public Integer getW2(){
        return w2;
    }
    public Integer getW3(){
        return w3;
    }
    public Integer getW4(){
        return w4;
    }
    public Integer getW5(){
        return w5;
    }
    public Integer getW6(){
        return w6;
    }
    public Integer getW7(){
        return w7;
    }
    public Integer getW8(){
        return w8;
    }
    public Integer getW9(){
        return w9;
    }
    public Integer getW10(){
        return w10;
    }
    public Integer getW11(){
        return w11;
    }
    public Integer getW12(){
        return w12;
    }
    public Integer getW13(){
        return w13;
    }
    public Integer getW14(){
        return w14;
    }
    public Integer getW15(){
        return w15;
    }
    public Integer getW16(){
        return w16;
    }
    public Integer getW17(){
        return w17;
    }
    public Integer getW18(){
        return w18;
    }
    public Integer getW19(){
        return w19;
    }
    public Integer getW20(){
        return w20;
    }
    public Integer getW21(){
        return w21;
    }
    public Integer getW22(){
        return w22;
    }
    public Integer getW23(){
        return w23;
    }
    public Integer getW24(){
        return w24;
    }
    public Integer getW25(){
        return w25;
    }
    public Integer getW26(){
        return w26;
    }
}

