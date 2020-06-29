/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : GcisPcModelVO.java
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
public class GcisPcModelVO extends BusinessObjectMasterVO {
    private Integer       modelId                                           ;
    private Integer       transactionId                                     ;
    private String        modelName                                         ;
    private String        modelSuffixCode                                   ;
    private String        corporationCode                                   ;
    private String        insSeq                                            ;
    private String        componentType                                     ;
    private String        componentTypeName                                 ;
    private String        prItemCode                                        ;
    private String        entBankType                                       ;
    private String        componentCode                                     ;
    private String        componentDesc                                     ;
    private String        valueCode                                         ;
    private String        mdmsModelCd                                       ;
    private Date          mdmsRgstDate                                      ;
    private Integer       componentCost                                     ;
    private String        applyFlag                                         ;
    private String        attribute1                                        ;
    private String        attribute2                                        ;
    private String        attribute3                                        ;
    private String        attribute4                                        ;
    private String        attribute5                                        ;
    private String        attribute6                                        ;
    private String        attribute7                                        ;
    private String        attribute8                                        ;
    private String        attribute9                                        ;
    private String        attribute10                                       ;
    private String        attribute11                                       ;
    private String        attribute12                                       ;
    private String        attribute13                                       ;
    private String        attribute14                                       ;
    private String        attribute15                                       ;
    private String        attribute16                                       ;
    private String        attribute17                                       ;
    private String        attribute18                                       ;
    private String        attribute19                                       ;
    private String        attribute20                                       ;


    public void    setModelId(Integer modelId){
        this.modelId = modelId;
    }
    public void    setTransactionId(Integer transactionId){
        this.transactionId = transactionId;
    }
    public void    setModelName(String modelName){
        this.modelName = modelName;
    }
    public void    setModelSuffixCode(String modelSuffixCode){
        this.modelSuffixCode = modelSuffixCode;
    }
    public void    setCorporationCode(String corporationCode){
        this.corporationCode = corporationCode;
    }
    public void    setInsSeq(String insSeq){
        this.insSeq = insSeq;
    }
    public void    setComponentType(String componentType){
        this.componentType = componentType;
    }
    public void    setComponentTypeName(String componentTypeName){
        this.componentTypeName = componentTypeName;
    }
    public void    setPrItemCode(String prItemCode){
        this.prItemCode = prItemCode;
    }
    public void    setEntBankType(String entBankType){
        this.entBankType = entBankType;
    }
    public void    setComponentCode(String componentCode){
        this.componentCode = componentCode;
    }
    public void    setComponentDesc(String componentDesc){
        this.componentDesc = componentDesc;
    }
    public void    setValueCode(String valueCode){
        this.valueCode = valueCode;
    }
    public void    setMdmsModelCd(String mdmsModelCd){
        this.mdmsModelCd = mdmsModelCd;
    }
    public void    setMdmsRgstDate(Date mdmsRgstDate){
        this.mdmsRgstDate = mdmsRgstDate;
    }
    public void    setMdmsRgstDate(String    mdmsRgstDate){
        this.mdmsRgstDate = this.omcConvertStr2Date(mdmsRgstDate);
    }
    public void    setComponentCost(Integer componentCost){
        this.componentCost = componentCost;
    }
    public void    setApplyFlag(String applyFlag){
        this.applyFlag = applyFlag;
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
    public void    setAttribute6(String attribute6){
        this.attribute6 = attribute6;
    }
    public void    setAttribute7(String attribute7){
        this.attribute7 = attribute7;
    }
    public void    setAttribute8(String attribute8){
        this.attribute8 = attribute8;
    }
    public void    setAttribute9(String attribute9){
        this.attribute9 = attribute9;
    }
    public void    setAttribute10(String attribute10){
        this.attribute10 = attribute10;
    }
    public void    setAttribute11(String attribute11){
        this.attribute11 = attribute11;
    }
    public void    setAttribute12(String attribute12){
        this.attribute12 = attribute12;
    }
    public void    setAttribute13(String attribute13){
        this.attribute13 = attribute13;
    }
    public void    setAttribute14(String attribute14){
        this.attribute14 = attribute14;
    }
    public void    setAttribute15(String attribute15){
        this.attribute15 = attribute15;
    }
    public void    setAttribute16(String attribute16){
        this.attribute16 = attribute16;
    }
    public void    setAttribute17(String attribute17){
        this.attribute17 = attribute17;
    }
    public void    setAttribute18(String attribute18){
        this.attribute18 = attribute18;
    }
    public void    setAttribute19(String attribute19){
        this.attribute19 = attribute19;
    }
    public void    setAttribute20(String attribute20){
        this.attribute20 = attribute20;
    }
    public Integer getModelId(){
        return modelId;
    }
    public Integer getTransactionId(){
        return transactionId;
    }
    public String getModelName(){
        return modelName;
    }
    public String getModelSuffixCode(){
        return modelSuffixCode;
    }
    public String getCorporationCode(){
        return corporationCode;
    }
    public String getInsSeq(){
        return insSeq;
    }
    public String getComponentType(){
        return componentType;
    }
    public String getComponentTypeName(){
        return componentTypeName;
    }
    public String getPrItemCode(){
        return prItemCode;
    }
    public String getEntBankType(){
        return entBankType;
    }
    public String getComponentCode(){
        return componentCode;
    }
    public String getComponentDesc(){
        return componentDesc;
    }
    public String getValueCode(){
        return valueCode;
    }
    public String getMdmsModelCd(){
        return mdmsModelCd;
    }
    public Date getMdmsRgstDate(){
        return mdmsRgstDate;
    }
    public Integer getComponentCost(){
        return componentCost;
    }
    public String getApplyFlag(){
        return applyFlag;
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
    public String getAttribute6(){
        return attribute6;
    }
    public String getAttribute7(){
        return attribute7;
    }
    public String getAttribute8(){
        return attribute8;
    }
    public String getAttribute9(){
        return attribute9;
    }
    public String getAttribute10(){
        return attribute10;
    }
    public String getAttribute11(){
        return attribute11;
    }
    public String getAttribute12(){
        return attribute12;
    }
    public String getAttribute13(){
        return attribute13;
    }
    public String getAttribute14(){
        return attribute14;
    }
    public String getAttribute15(){
        return attribute15;
    }
    public String getAttribute16(){
        return attribute16;
    }
    public String getAttribute17(){
        return attribute17;
    }
    public String getAttribute18(){
        return attribute18;
    }
    public String getAttribute19(){
        return attribute19;
    }
    public String getAttribute20(){
        return attribute20;
    }
}

