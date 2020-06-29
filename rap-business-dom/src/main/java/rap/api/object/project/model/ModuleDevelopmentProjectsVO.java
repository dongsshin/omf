/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ModuleDevelopmentProjectsVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import rap.api.object.project.model.ProductProjectsVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ModuleDevelopmentProjectsVO extends ProductProjectsVO {
    private String        devSite1Code                                      ;
    private String        devSite2Code                                      ;
    private String        outsourcingTypeCode                               ;
    private String        firstDevEventCode                                 ;
    private String        devEventCode                                      ;
    private String        depreciation                                      ;
    private String        developmentType                                   ;
    private String        moduleName                                        ;
    private String        newPartStatus                                      = "Unregistered";


    public void    setDevSite1Code(String devSite1Code){
        this.devSite1Code = devSite1Code;
    }
    public void    setDevSite2Code(String devSite2Code){
        this.devSite2Code = devSite2Code;
    }
    public void    setOutsourcingTypeCode(String outsourcingTypeCode){
        this.outsourcingTypeCode = outsourcingTypeCode;
    }
    public void    setFirstDevEventCode(String firstDevEventCode){
        this.firstDevEventCode = firstDevEventCode;
    }
    public void    setDevEventCode(String devEventCode){
        this.devEventCode = devEventCode;
    }
    public void    setDepreciation(String depreciation){
        this.depreciation = depreciation;
    }
    public void    setDevelopmentType(String developmentType){
        this.developmentType = developmentType;
    }
    public void    setModuleName(String moduleName){
        this.moduleName = moduleName;
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
    public String getOutsourcingTypeCode(){
        return outsourcingTypeCode;
    }
    public String getFirstDevEventCode(){
        return firstDevEventCode;
    }
    public String getDevEventCode(){
        return devEventCode;
    }
    public String getDepreciation(){
        return depreciation;
    }
    public String getDevelopmentType(){
        return developmentType;
    }
    public String getModuleName(){
        return moduleName;
    }
    public String getNewPartStatus(){
        return newPartStatus;
    }
}

