/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectModelForD5VO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.relation.model;


import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ProjectModelForD5VO extends BusinessRelationObjectVO {
    private String        representativeModelFlag                           ;
    private String        masterSystem                                       = "GPDM";
    private String        modelStatus                                       ;
    private String        modelCategory                                      = "-";
    private String        developGrade                                      ;
    private String        developStartYyyymmdd                              ;
    private String        developPlanYyyymmdd                               ;
    private String        developResultYyyymmdd                             ;
    private String        swTaPlanYyyymmdd                                  ;
    private String        swTaResultYyyymmdd                                ;
    private String        swTaChangeInfo                                    ;
    private String        cVariPlanYyyymmdd                                 ;
    private String        cVariResultYyyymmdd                               ;
    private String        dropRemark                                        ;
    private String        prodAppPlanYyyymmdd                               ;
    private String        prodAppEndYyyymmdd                                ;
    private String        partAppPlanYyyymmdd                               ;
    private String        partAppEndYyyymmdd                                ;
    private String        region                                            ;
    private String        country                                           ;
    private String        zmoRev                                            ;
    private String        swApprovalPlanYyyymmdd                            ;
    private String        swApprovalResultYyyymmdd                          ;
    private String        swVerFixPlanYyyymmdd                              ;
    private String        swVerFixResultYyyymmdd                            ;
    private String        attribute01                                       ;
    private String        attribute02                                       ;
    private String        attribute03                                       ;
    private String        attribute04                                       ;
    private String        attribute05                                       ;
    private String        attribute06                                       ;
    private String        developFirstYyyymmdd                              ;
    private String        attribute07                                       ;
    private String        attribute08                                       ;
    private String        attribute09                                       ;
    private String        attribute10                                       ;


    public void    setRepresentativeModelFlag(String representativeModelFlag){
        this.representativeModelFlag = representativeModelFlag;
    }
    public void    setMasterSystem(String masterSystem){
        this.masterSystem = masterSystem;
    }
    public void    setModelStatus(String modelStatus){
        this.modelStatus = modelStatus;
    }
    public void    setModelCategory(String modelCategory){
        this.modelCategory = modelCategory;
    }
    public void    setDevelopGrade(String developGrade){
        this.developGrade = developGrade;
    }
    public void    setDevelopStartYyyymmdd(String developStartYyyymmdd){
        this.developStartYyyymmdd = developStartYyyymmdd;
    }
    public void    setDevelopPlanYyyymmdd(String developPlanYyyymmdd){
        this.developPlanYyyymmdd = developPlanYyyymmdd;
    }
    public void    setDevelopResultYyyymmdd(String developResultYyyymmdd){
        this.developResultYyyymmdd = developResultYyyymmdd;
    }
    public void    setSwTaPlanYyyymmdd(String swTaPlanYyyymmdd){
        this.swTaPlanYyyymmdd = swTaPlanYyyymmdd;
    }
    public void    setSwTaResultYyyymmdd(String swTaResultYyyymmdd){
        this.swTaResultYyyymmdd = swTaResultYyyymmdd;
    }
    public void    setSwTaChangeInfo(String swTaChangeInfo){
        this.swTaChangeInfo = swTaChangeInfo;
    }
    public void    setCVariPlanYyyymmdd(String cVariPlanYyyymmdd){
        this.cVariPlanYyyymmdd = cVariPlanYyyymmdd;
    }
    public void    setCVariResultYyyymmdd(String cVariResultYyyymmdd){
        this.cVariResultYyyymmdd = cVariResultYyyymmdd;
    }
    public void    setDropRemark(String dropRemark){
        this.dropRemark = dropRemark;
    }
    public void    setProdAppPlanYyyymmdd(String prodAppPlanYyyymmdd){
        this.prodAppPlanYyyymmdd = prodAppPlanYyyymmdd;
    }
    public void    setProdAppEndYyyymmdd(String prodAppEndYyyymmdd){
        this.prodAppEndYyyymmdd = prodAppEndYyyymmdd;
    }
    public void    setPartAppPlanYyyymmdd(String partAppPlanYyyymmdd){
        this.partAppPlanYyyymmdd = partAppPlanYyyymmdd;
    }
    public void    setPartAppEndYyyymmdd(String partAppEndYyyymmdd){
        this.partAppEndYyyymmdd = partAppEndYyyymmdd;
    }
    public void    setRegion(String region){
        this.region = region;
    }
    public void    setCountry(String country){
        this.country = country;
    }
    public void    setZmoRev(String zmoRev){
        this.zmoRev = zmoRev;
    }
    public void    setSwApprovalPlanYyyymmdd(String swApprovalPlanYyyymmdd){
        this.swApprovalPlanYyyymmdd = swApprovalPlanYyyymmdd;
    }
    public void    setSwApprovalResultYyyymmdd(String swApprovalResultYyyymmdd){
        this.swApprovalResultYyyymmdd = swApprovalResultYyyymmdd;
    }
    public void    setSwVerFixPlanYyyymmdd(String swVerFixPlanYyyymmdd){
        this.swVerFixPlanYyyymmdd = swVerFixPlanYyyymmdd;
    }
    public void    setSwVerFixResultYyyymmdd(String swVerFixResultYyyymmdd){
        this.swVerFixResultYyyymmdd = swVerFixResultYyyymmdd;
    }
    public void    setAttribute01(String attribute01){
        this.attribute01 = attribute01;
    }
    public void    setAttribute02(String attribute02){
        this.attribute02 = attribute02;
    }
    public void    setAttribute03(String attribute03){
        this.attribute03 = attribute03;
    }
    public void    setAttribute04(String attribute04){
        this.attribute04 = attribute04;
    }
    public void    setAttribute05(String attribute05){
        this.attribute05 = attribute05;
    }
    public void    setAttribute06(String attribute06){
        this.attribute06 = attribute06;
    }
    public void    setDevelopFirstYyyymmdd(String developFirstYyyymmdd){
        this.developFirstYyyymmdd = developFirstYyyymmdd;
    }
    public void    setAttribute07(String attribute07){
        this.attribute07 = attribute07;
    }
    public void    setAttribute08(String attribute08){
        this.attribute08 = attribute08;
    }
    public void    setAttribute09(String attribute09){
        this.attribute09 = attribute09;
    }
    public void    setAttribute10(String attribute10){
        this.attribute10 = attribute10;
    }
    public String getRepresentativeModelFlag(){
        return representativeModelFlag;
    }
    public String getMasterSystem(){
        return masterSystem;
    }
    public String getModelStatus(){
        return modelStatus;
    }
    public String getModelCategory(){
        return modelCategory;
    }
    public String getDevelopGrade(){
        return developGrade;
    }
    public String getDevelopStartYyyymmdd(){
        return developStartYyyymmdd;
    }
    public String getDevelopPlanYyyymmdd(){
        return developPlanYyyymmdd;
    }
    public String getDevelopResultYyyymmdd(){
        return developResultYyyymmdd;
    }
    public String getSwTaPlanYyyymmdd(){
        return swTaPlanYyyymmdd;
    }
    public String getSwTaResultYyyymmdd(){
        return swTaResultYyyymmdd;
    }
    public String getSwTaChangeInfo(){
        return swTaChangeInfo;
    }
    public String getCVariPlanYyyymmdd(){
        return cVariPlanYyyymmdd;
    }
    public String getCVariResultYyyymmdd(){
        return cVariResultYyyymmdd;
    }
    public String getDropRemark(){
        return dropRemark;
    }
    public String getProdAppPlanYyyymmdd(){
        return prodAppPlanYyyymmdd;
    }
    public String getProdAppEndYyyymmdd(){
        return prodAppEndYyyymmdd;
    }
    public String getPartAppPlanYyyymmdd(){
        return partAppPlanYyyymmdd;
    }
    public String getPartAppEndYyyymmdd(){
        return partAppEndYyyymmdd;
    }
    public String getRegion(){
        return region;
    }
    public String getCountry(){
        return country;
    }
    public String getZmoRev(){
        return zmoRev;
    }
    public String getSwApprovalPlanYyyymmdd(){
        return swApprovalPlanYyyymmdd;
    }
    public String getSwApprovalResultYyyymmdd(){
        return swApprovalResultYyyymmdd;
    }
    public String getSwVerFixPlanYyyymmdd(){
        return swVerFixPlanYyyymmdd;
    }
    public String getSwVerFixResultYyyymmdd(){
        return swVerFixResultYyyymmdd;
    }
    public String getAttribute01(){
        return attribute01;
    }
    public String getAttribute02(){
        return attribute02;
    }
    public String getAttribute03(){
        return attribute03;
    }
    public String getAttribute04(){
        return attribute04;
    }
    public String getAttribute05(){
        return attribute05;
    }
    public String getAttribute06(){
        return attribute06;
    }
    public String getDevelopFirstYyyymmdd(){
        return developFirstYyyymmdd;
    }
    public String getAttribute07(){
        return attribute07;
    }
    public String getAttribute08(){
        return attribute08;
    }
    public String getAttribute09(){
        return attribute09;
    }
    public String getAttribute10(){
        return attribute10;
    }
}

