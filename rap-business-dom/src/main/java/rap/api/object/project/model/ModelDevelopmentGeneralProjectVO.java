/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ModelDevelopmentGeneralProjectVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import rap.api.object.project.model.ModelDevelopmentProjectsVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ModelDevelopmentGeneralProjectVO extends ModelDevelopmentProjectsVO {
    private String        devSite1Code                                      ;
    private String        devSite2Code                                      ;
    private String        firstDevEventCode                                 ;
    private String        devEventCode                                      ;
    private String        outsourcingTypeCode                               ;
    private String        outsourcingSiteObid                               ;
    private String        bizType                                           ;
    private String        marketGrade                                       ;
    private String        depreciation                                      ;
    private String        inclusionOutunitYn                                ;
    private String        salesTypeCode                                     ;
    private String        pimsModule                                        ;
    private String        deriMainCode                                      ;
    private String        deriSubCode                                       ;
    private String        newPartStatus                                      = "Unregistered";


    public void    setDevSite1Code(String devSite1Code){
        this.devSite1Code = devSite1Code;
    }
    public void    setDevSite2Code(String devSite2Code){
        this.devSite2Code = devSite2Code;
    }
    public void    setFirstDevEventCode(String firstDevEventCode){
        this.firstDevEventCode = firstDevEventCode;
    }
    public void    setDevEventCode(String devEventCode){
        this.devEventCode = devEventCode;
    }
    public void    setOutsourcingTypeCode(String outsourcingTypeCode){
        this.outsourcingTypeCode = outsourcingTypeCode;
    }
    public void    setOutsourcingSiteObid(String outsourcingSiteObid){
        this.outsourcingSiteObid = outsourcingSiteObid;
    }
    public void    setBizType(String bizType){
        this.bizType = bizType;
    }
    public void    setMarketGrade(String marketGrade){
        this.marketGrade = marketGrade;
    }
    public void    setDepreciation(String depreciation){
        this.depreciation = depreciation;
    }
    public void    setInclusionOutunitYn(String inclusionOutunitYn){
        this.inclusionOutunitYn = inclusionOutunitYn;
    }
    public void    setSalesTypeCode(String salesTypeCode){
        this.salesTypeCode = salesTypeCode;
    }
    public void    setPimsModule(String pimsModule){
        this.pimsModule = pimsModule;
    }
    public void    setDeriMainCode(String deriMainCode){
        this.deriMainCode = deriMainCode;
    }
    public void    setDeriSubCode(String deriSubCode){
        this.deriSubCode = deriSubCode;
    }
    public void    setNewPartStatus(String newPartStatus){
        this.newPartStatus = newPartStatus;
    }
    public String getDevSite1Code(){
        return devSite1Code;
    }
    public String getDevSite2Code(){
        return devSite2Code;
    }
    public String getFirstDevEventCode(){
        return firstDevEventCode;
    }
    public String getDevEventCode(){
        return devEventCode;
    }
    public String getOutsourcingTypeCode(){
        return outsourcingTypeCode;
    }
    public String getOutsourcingSiteObid(){
        return outsourcingSiteObid;
    }
    public String getBizType(){
        return bizType;
    }
    public String getMarketGrade(){
        return marketGrade;
    }
    public String getDepreciation(){
        return depreciation;
    }
    public String getInclusionOutunitYn(){
        return inclusionOutunitYn;
    }
    public String getSalesTypeCode(){
        return salesTypeCode;
    }
    public String getPimsModule(){
        return pimsModule;
    }
    public String getDeriMainCode(){
        return deriMainCode;
    }
    public String getDeriSubCode(){
        return deriSubCode;
    }
    public String getNewPartStatus(){
        return newPartStatus;
    }
}

