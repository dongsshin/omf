/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : SpecItemsVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.pdr.model;


import rap.api.object.pdr.model.FeaturesVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class SpecItemsVO extends FeaturesVO {
    private String        itemShortName                                     ;
    private String        mandatoryFlag                                     ;
    private String        itemTypeCode                                      ;
    private String        attachFileId                                      ;
    private String        workDelimiterCode                                 ;
    private String        rbisIfFlag                                        ;
    private String        propertyFlag                                      ;
    private String        priOnlyFlag                                       ;
    private String        gpinTarget                                        ;
    private String        useFlag                                           ;
    private String        pdrFlag                                           ;
    private String        prjFlag                                           ;
    private String        uspFlag                                           ;
    private String        ppFlag                                            ;
    private String        copyFlag                                          ;
    private Integer       itemLevel                                         ;
    private String        attribute1                                        ;
    private String        attribute2                                        ;
    private String        attribute3                                        ;
    private String        attribute4                                        ;
    private String        attribute5                                        ;
    private Integer       orderValue                                        ;


    public void    setItemShortName(String itemShortName){
        this.itemShortName = itemShortName;
    }
    public void    setMandatoryFlag(String mandatoryFlag){
        this.mandatoryFlag = mandatoryFlag;
    }
    public void    setItemTypeCode(String itemTypeCode){
        this.itemTypeCode = itemTypeCode;
    }
    public void    setAttachFileId(String attachFileId){
        this.attachFileId = attachFileId;
    }
    public void    setWorkDelimiterCode(String workDelimiterCode){
        this.workDelimiterCode = workDelimiterCode;
    }
    public void    setRbisIfFlag(String rbisIfFlag){
        this.rbisIfFlag = rbisIfFlag;
    }
    public void    setPropertyFlag(String propertyFlag){
        this.propertyFlag = propertyFlag;
    }
    public void    setPriOnlyFlag(String priOnlyFlag){
        this.priOnlyFlag = priOnlyFlag;
    }
    public void    setGpinTarget(String gpinTarget){
        this.gpinTarget = gpinTarget;
    }
    public void    setUseFlag(String useFlag){
        this.useFlag = useFlag;
    }
    public void    setPdrFlag(String pdrFlag){
        this.pdrFlag = pdrFlag;
    }
    public void    setPrjFlag(String prjFlag){
        this.prjFlag = prjFlag;
    }
    public void    setUspFlag(String uspFlag){
        this.uspFlag = uspFlag;
    }
    public void    setPpFlag(String ppFlag){
        this.ppFlag = ppFlag;
    }
    public void    setCopyFlag(String copyFlag){
        this.copyFlag = copyFlag;
    }
    public void    setItemLevel(Integer itemLevel){
        this.itemLevel = itemLevel;
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
    public void    setOrderValue(Integer orderValue){
        this.orderValue = orderValue;
    }
    public String getItemShortName(){
        return itemShortName;
    }
    public String getMandatoryFlag(){
        return mandatoryFlag;
    }
    public String getItemTypeCode(){
        return itemTypeCode;
    }
    public String getAttachFileId(){
        return attachFileId;
    }
    public String getWorkDelimiterCode(){
        return workDelimiterCode;
    }
    public String getRbisIfFlag(){
        return rbisIfFlag;
    }
    public String getPropertyFlag(){
        return propertyFlag;
    }
    public String getPriOnlyFlag(){
        return priOnlyFlag;
    }
    public String getGpinTarget(){
        return gpinTarget;
    }
    public String getUseFlag(){
        return useFlag;
    }
    public String getPdrFlag(){
        return pdrFlag;
    }
    public String getPrjFlag(){
        return prjFlag;
    }
    public String getUspFlag(){
        return uspFlag;
    }
    public String getPpFlag(){
        return ppFlag;
    }
    public String getCopyFlag(){
        return copyFlag;
    }
    public Integer getItemLevel(){
        return itemLevel;
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
    public Integer getOrderValue(){
        return orderValue;
    }
}

