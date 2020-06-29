/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProdPlanPRMProductSiteColorVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.pp.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ProdPlanPRMProductSiteColorVO extends BusinessObjectMasterVO {
    private String        productSiteColorId                                ;
    private String        divisionCode                                      ;
    private String        productType                                       ;
    private String        siteCode                                          ;
    private Integer       orderNo                                           ;
    private String        color                                             ;
    private String        fontcolor                                         ;
    private String        shapeCode                                         ;
    private String        useYn                                             ;
    private String        attribute1                                        ;
    private String        attribute2                                        ;
    private String        attribute3                                        ;
    private String        attribute4                                        ;
    private String        mfgPrmSiteCode                                    ;


    public void    setProductSiteColorId(String productSiteColorId){
        this.productSiteColorId = productSiteColorId;
    }
    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setProductType(String productType){
        this.productType = productType;
    }
    public void    setSiteCode(String siteCode){
        this.siteCode = siteCode;
    }
    public void    setOrderNo(Integer orderNo){
        this.orderNo = orderNo;
    }
    public void    setColor(String color){
        this.color = color;
    }
    public void    setFontcolor(String fontcolor){
        this.fontcolor = fontcolor;
    }
    public void    setShapeCode(String shapeCode){
        this.shapeCode = shapeCode;
    }
    public void    setUseYn(String useYn){
        this.useYn = useYn;
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
    public void    setMfgPrmSiteCode(String mfgPrmSiteCode){
        this.mfgPrmSiteCode = mfgPrmSiteCode;
    }
    public String getProductSiteColorId(){
        return productSiteColorId;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getProductType(){
        return productType;
    }
    public String getSiteCode(){
        return siteCode;
    }
    public Integer getOrderNo(){
        return orderNo;
    }
    public String getColor(){
        return color;
    }
    public String getFontcolor(){
        return fontcolor;
    }
    public String getShapeCode(){
        return shapeCode;
    }
    public String getUseYn(){
        return useYn;
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
    public String getMfgPrmSiteCode(){
        return mfgPrmSiteCode;
    }
}

