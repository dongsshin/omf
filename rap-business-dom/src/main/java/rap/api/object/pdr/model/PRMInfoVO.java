/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PRMInfoVO.java
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
public class PRMInfoVO extends BusinessObjectMasterVO {
    private String        prmCode                                           ;
    private String        prmNo                                             ;
    private String        divisionCode                                      ;
    private String        product                                           ;
    private String        region                                            ;
    private String        nation                                            ;
    private String        step                                              ;
    private String        prmUrl                                            ;
    private String        attribute2                                        ;
    private String        attribute3                                        ;
    private String        attribute4                                        ;
    private String        attribute5                                        ;
    private String        attribute6                                        ;
    private String        attribute7                                        ;
    private String        attribute8                                        ;
    private String        attribute9                                        ;
    private String        attribute10                                       ;
    private String        prmCreator                                        ;
    private Date          prmCreated                                        ;
    private String        prmModifier                                       ;
    private Date          prmModified                                       ;


    public void    setPrmCode(String prmCode){
        this.prmCode = prmCode;
    }
    public void    setPrmNo(String prmNo){
        this.prmNo = prmNo;
    }
    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setProduct(String product){
        this.product = product;
    }
    public void    setRegion(String region){
        this.region = region;
    }
    public void    setNation(String nation){
        this.nation = nation;
    }
    public void    setStep(String step){
        this.step = step;
    }
    public void    setPrmUrl(String prmUrl){
        this.prmUrl = prmUrl;
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
    public void    setPrmCreator(String prmCreator){
        this.prmCreator = prmCreator;
    }
    public void    setPrmCreated(Date prmCreated){
        this.prmCreated = prmCreated;
    }
    public void    setPrmCreated(String    prmCreated){
        this.prmCreated = this.omcConvertStr2Date(prmCreated);
    }
    public void    setPrmModifier(String prmModifier){
        this.prmModifier = prmModifier;
    }
    public void    setPrmModified(Date prmModified){
        this.prmModified = prmModified;
    }
    public void    setPrmModified(String    prmModified){
        this.prmModified = this.omcConvertStr2Date(prmModified);
    }
    public String getPrmCode(){
        return prmCode;
    }
    public String getPrmNo(){
        return prmNo;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getProduct(){
        return product;
    }
    public String getRegion(){
        return region;
    }
    public String getNation(){
        return nation;
    }
    public String getStep(){
        return step;
    }
    public String getPrmUrl(){
        return prmUrl;
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
    public String getPrmCreator(){
        return prmCreator;
    }
    public Date getPrmCreated(){
        return prmCreated;
    }
    public String getPrmModifier(){
        return prmModifier;
    }
    public Date getPrmModified(){
        return prmModified;
    }
}

