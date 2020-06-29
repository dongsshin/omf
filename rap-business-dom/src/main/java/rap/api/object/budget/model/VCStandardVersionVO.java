/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : VCStandardVersionVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.budget.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class VCStandardVersionVO extends BusinessObjectMasterVO {
    private String        version                                           ;
    private String        currentYn                                         ;
    private String        vcProductGroup                                    ;
    private String        vcProductGrade                                    ;
    private String        vcTargetGrade                                     ;
    private String        vcPlatform                                        ;
    private String        vcDevTypeCode                                     ;
    private String        vcRegionCode                                      ;
    private String        vcCountrySpec                                     ;
    private String        vcCommunicationSpec                               ;
    private String        vcSampleGrade                                     ;
    private Integer       typeCount                                         ;
    private Integer       toCCount                                          ;


    public void    setVersion(String version){
        this.version = version;
    }
    public void    setCurrentYn(String currentYn){
        this.currentYn = currentYn;
    }
    public void    setVcProductGroup(String vcProductGroup){
        this.vcProductGroup = vcProductGroup;
    }
    public void    setVcProductGrade(String vcProductGrade){
        this.vcProductGrade = vcProductGrade;
    }
    public void    setVcTargetGrade(String vcTargetGrade){
        this.vcTargetGrade = vcTargetGrade;
    }
    public void    setVcPlatform(String vcPlatform){
        this.vcPlatform = vcPlatform;
    }
    public void    setVcDevTypeCode(String vcDevTypeCode){
        this.vcDevTypeCode = vcDevTypeCode;
    }
    public void    setVcRegionCode(String vcRegionCode){
        this.vcRegionCode = vcRegionCode;
    }
    public void    setVcCountrySpec(String vcCountrySpec){
        this.vcCountrySpec = vcCountrySpec;
    }
    public void    setVcCommunicationSpec(String vcCommunicationSpec){
        this.vcCommunicationSpec = vcCommunicationSpec;
    }
    public void    setVcSampleGrade(String vcSampleGrade){
        this.vcSampleGrade = vcSampleGrade;
    }
    public void    setTypeCount(Integer typeCount){
        this.typeCount = typeCount;
    }
    public void    setToCCount(Integer toCCount){
        this.toCCount = toCCount;
    }
    public String getVersion(){
        return version;
    }
    public String getCurrentYn(){
        return currentYn;
    }
    public String getVcProductGroup(){
        return vcProductGroup;
    }
    public String getVcProductGrade(){
        return vcProductGrade;
    }
    public String getVcTargetGrade(){
        return vcTargetGrade;
    }
    public String getVcPlatform(){
        return vcPlatform;
    }
    public String getVcDevTypeCode(){
        return vcDevTypeCode;
    }
    public String getVcRegionCode(){
        return vcRegionCode;
    }
    public String getVcCountrySpec(){
        return vcCountrySpec;
    }
    public String getVcCommunicationSpec(){
        return vcCommunicationSpec;
    }
    public String getVcSampleGrade(){
        return vcSampleGrade;
    }
    public Integer getTypeCount(){
        return typeCount;
    }
    public Integer getToCCount(){
        return toCCount;
    }
}

