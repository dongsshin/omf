/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ConsultChemicalItemVO.java
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
public class ConsultChemicalItemVO extends BusinessObjectMasterVO {
    private String        reqUserCode                                       ;
    private String        userEqFlag                                        ;
    private String        useUserCode                                       ;
    private String        cmsSiteCode                                       ;
    private String        siteCode                                          ;
    private String        deptCode                                          ;
    private String        dtlPlaceCode                                      ;
    private String        etcUseInfo                                        ;
    private String        prodName                                          ;
    private String        prodTypeCode                                      ;
    private String        buyTypeCode                                       ;
    private String        useTypeCode                                       ;
    private Integer       expectUse                                         ;
    private String        expectUseUnitCode                                 ;
    private String        areaTypeCode                                      ;
    private String        subModCode                                        ;
    private BigDecimal    buyAmount                                          = new BigDecimal(0);
    private String        buyAmountUnitCode                                 ;
    private String        ppNos                                             ;
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


    public void    setReqUserCode(String reqUserCode){
        this.reqUserCode = reqUserCode;
    }
    public void    setUserEqFlag(String userEqFlag){
        this.userEqFlag = userEqFlag;
    }
    public void    setUseUserCode(String useUserCode){
        this.useUserCode = useUserCode;
    }
    public void    setCmsSiteCode(String cmsSiteCode){
        this.cmsSiteCode = cmsSiteCode;
    }
    public void    setSiteCode(String siteCode){
        this.siteCode = siteCode;
    }
    public void    setDeptCode(String deptCode){
        this.deptCode = deptCode;
    }
    public void    setDtlPlaceCode(String dtlPlaceCode){
        this.dtlPlaceCode = dtlPlaceCode;
    }
    public void    setEtcUseInfo(String etcUseInfo){
        this.etcUseInfo = etcUseInfo;
    }
    public void    setProdName(String prodName){
        this.prodName = prodName;
    }
    public void    setProdTypeCode(String prodTypeCode){
        this.prodTypeCode = prodTypeCode;
    }
    public void    setBuyTypeCode(String buyTypeCode){
        this.buyTypeCode = buyTypeCode;
    }
    public void    setUseTypeCode(String useTypeCode){
        this.useTypeCode = useTypeCode;
    }
    public void    setExpectUse(Integer expectUse){
        this.expectUse = expectUse;
    }
    public void    setExpectUseUnitCode(String expectUseUnitCode){
        this.expectUseUnitCode = expectUseUnitCode;
    }
    public void    setAreaTypeCode(String areaTypeCode){
        this.areaTypeCode = areaTypeCode;
    }
    public void    setSubModCode(String subModCode){
        this.subModCode = subModCode;
    }
    public void    setBuyAmount(BigDecimal buyAmount){
        this.buyAmount = buyAmount;
    }
    public void    setBuyAmountUnitCode(String buyAmountUnitCode){
        this.buyAmountUnitCode = buyAmountUnitCode;
    }
    public void    setPpNos(String ppNos){
        this.ppNos = ppNos;
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
    public String getReqUserCode(){
        return reqUserCode;
    }
    public String getUserEqFlag(){
        return userEqFlag;
    }
    public String getUseUserCode(){
        return useUserCode;
    }
    public String getCmsSiteCode(){
        return cmsSiteCode;
    }
    public String getSiteCode(){
        return siteCode;
    }
    public String getDeptCode(){
        return deptCode;
    }
    public String getDtlPlaceCode(){
        return dtlPlaceCode;
    }
    public String getEtcUseInfo(){
        return etcUseInfo;
    }
    public String getProdName(){
        return prodName;
    }
    public String getProdTypeCode(){
        return prodTypeCode;
    }
    public String getBuyTypeCode(){
        return buyTypeCode;
    }
    public String getUseTypeCode(){
        return useTypeCode;
    }
    public Integer getExpectUse(){
        return expectUse;
    }
    public String getExpectUseUnitCode(){
        return expectUseUnitCode;
    }
    public String getAreaTypeCode(){
        return areaTypeCode;
    }
    public String getSubModCode(){
        return subModCode;
    }
    public BigDecimal getBuyAmount(){
        return buyAmount;
    }
    public String getBuyAmountUnitCode(){
        return buyAmountUnitCode;
    }
    public String getPpNos(){
        return ppNos;
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
}

