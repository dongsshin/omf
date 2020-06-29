/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PartProjectFullMasterVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.partproject.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class PartProjectFullMasterVO extends BusinessObjectMasterVO {
    private String        prodProjectGubun                                  ;
    private Float         partQty                                           ;
    private String        changePoint                                       ;
    private String        changeReason                                      ;
    private Date          drw3dApprovalDate                                 ;
    private Date          drw2dApprovalDate                                 ;
    private Integer       drwEcoCount                                       ;
    private Date          drwFinalEcoDate                                   ;
    private String        toolingType                                       ;
    private String        toolCavity                                        ;
    private String        toolmaker                                         ;
    private String        toolCurrency                                      ;
    private String        toolCost                                          ;
    private Date          toolDrwFixDate                                    ;
    private Date          toolFirstShotDate                                 ;
    private Date          toolTestApprovalDate                              ;
    private Date          drbfmDate                                         ;
    private Date          preiqGateDate                                     ;
    private Date          testRequestDate                                   ;
    private String        testStatus                                        ;
    private Date          testApprovalDate                                  ;
    private String        testResult                                        ;
    private Integer       testSeq                                           ;
    private String        yieldTargetdv                                     ;
    private String        yieldResultdv                                     ;
    private String        yieldTargetpv                                     ;
    private String        yieldResultpv                                     ;
    private String        yieldTargetPreMp                                  ;
    private String        yieldResultPreMp                                  ;
    private String        priceTargetDesign                                 ;
    private String        priceBase                                         ;
    private String        priceFirstProd                                    ;
    private Integer       act001Sd                                          ;
    private Date          act001ActualEndDate                               ;
    private String        act001OwnerName                                   ;
    private Integer       act002Sd                                          ;
    private Date          act002ActualEndDate                               ;
    private String        act002OwnerName                                   ;
    private Integer       act003Sd                                          ;
    private Date          act003ActualEndDate                               ;
    private String        act003OwnerName                                   ;
    private Integer       act004Sd                                          ;
    private Date          act004ActualEndDate                               ;
    private String        act004OwnerName                                   ;
    private Integer       act005Sd                                          ;
    private Date          act005ActualEndDate                               ;
    private String        act005OwnerName                                   ;
    private Integer       act006Sd                                          ;
    private Date          act006ActualEndDate                               ;
    private String        act006OwnerName                                   ;
    private Integer       act007Sd                                          ;
    private Date          act007ActualEndDate                               ;
    private String        act007OwnerName                                   ;
    private Integer       act008Sd                                          ;
    private Date          act008ActualEndDate                               ;
    private String        act008OwnerName                                   ;
    private Integer       act009Sd                                          ;
    private Date          act009ActualEndDate                               ;
    private String        act009OwnerName                                   ;
    private Integer       act010Sd                                          ;
    private Date          act010ActualEndDate                               ;
    private String        act010OwnerName                                   ;
    private Integer       act011Sd                                          ;
    private Date          act011ActualEndDate                               ;
    private String        act011OwnerName                                   ;
    private Integer       act012Sd                                          ;
    private Date          act012ActualEndDate                               ;
    private String        act012OwnerName                                   ;
    private Integer       act013Sd                                          ;
    private Date          act013ActualEndDate                               ;
    private String        act013OwnerName                                   ;
    private Integer       act014Sd                                          ;
    private Date          act014ActualEndDate                               ;
    private String        act014OwnerName                                   ;
    private Integer       act015Sd                                          ;
    private Date          act015ActualEndDate                               ;
    private String        act015OwnerName                                   ;
    private Integer       act016Sd                                          ;
    private Date          act016ActualEndDate                               ;
    private String        act016OwnerName                                   ;
    private Integer       act017Sd                                          ;
    private Date          act017ActualEndDate                               ;
    private String        act017OwnerName                                   ;
    private Integer       act018Sd                                          ;
    private Date          act018ActualEndDate                               ;
    private String        act018OwnerName                                   ;
    private Integer       act019Sd                                          ;
    private Date          act019ActualEndDate                               ;
    private String        act019OwnerName                                   ;
    private Integer       act019testSeq                                     ;
    private Integer       act020Sd                                          ;
    private Date          act020ActualEndDate                               ;
    private String        act020OwnerName                                   ;
    private String        act020testStaus                                   ;
    private Integer       act020testSeq                                     ;
    private String        act020testResult                                  ;
    private Integer       act021Sd                                          ;
    private Date          act021ActualEndDate                               ;
    private String        act021OwnerName                                   ;
    private Integer       act022Sd                                          ;
    private Date          act022ActualEndDate                               ;
    private String        act022OwnerName                                   ;
    private Integer       act023Sd                                          ;
    private Date          act023ActualEndDate                               ;
    private String        act023OwnerName                                   ;
    private String        rolePartDevelopment                               ;
    private String        rolePurchaser                                     ;
    private String        rolesqe                                           ;
    private String        roleTooling                                       ;
    private String        rolepdm                                           ;
    private String        rolePartQuality                                   ;
    private String        roleProdQuality                                   ;
    private String        roleEngineer                                      ;
    private String        roleSupport                                       ;
    private Date          act001PlanEndDate                                 ;
    private String        act001StatusName                                  ;
    private Integer       act002Delay                                       ;
    private Date          act002PlanEndDate                                 ;
    private String        act002StatusName                                  ;
    private Integer       act003Delay                                       ;
    private Date          act003PlanEndDate                                 ;
    private String        act003StatusName                                  ;
    private Integer       act004Delay                                       ;
    private Date          act004PlanEndDate                                 ;
    private String        act004StatusName                                  ;
    private Integer       act005Delay                                       ;
    private Date          act005PlanEndDate                                 ;
    private String        act005StatusName                                  ;
    private Integer       act006Delay                                       ;
    private Date          act006PlanEndDate                                 ;
    private String        act006StatusName                                  ;
    private Integer       act007Delay                                       ;
    private Date          act007PlanEndDate                                 ;
    private String        act007StatusName                                  ;
    private Integer       act008Delay                                       ;
    private Date          act008PlanEndDate                                 ;
    private String        act008StatusName                                  ;
    private Integer       act009Delay                                       ;
    private Date          act009PlanEndDate                                 ;
    private String        act009StatusName                                  ;
    private Integer       act010Delay                                       ;
    private Date          act010PlanEndDate                                 ;
    private String        act010StatusName                                  ;
    private Integer       act011Delay                                       ;
    private Date          act011PlanEndDate                                 ;
    private String        act011StatusName                                  ;
    private Integer       act012Delay                                       ;
    private Date          act012PlanEndDate                                 ;
    private String        act012StatusName                                  ;
    private Integer       act013Delay                                       ;
    private Date          act013PlanEndDate                                 ;
    private String        act013StatusName                                  ;
    private Integer       act014Delay                                       ;
    private Date          act014PlanEndDate                                 ;
    private String        act014StatusName                                  ;
    private Integer       act015Delay                                       ;
    private Date          act015PlanEndDate                                 ;
    private String        act015StatusName                                  ;
    private Integer       act016Delay                                       ;
    private Date          act016PlanEndDate                                 ;
    private String        act016StatusName                                  ;
    private Integer       act017Delay                                       ;
    private Date          act017PlanEndDate                                 ;
    private String        act017StatusName                                  ;
    private Integer       act018Delay                                       ;
    private Date          act018PlanEndDate                                 ;
    private String        act018StatusName                                  ;
    private Integer       act019Delay                                       ;
    private Date          act019PlanEndDate                                 ;
    private String        act019StatusName                                  ;
    private Integer       act020Delay                                       ;
    private Date          act020PlanEndDate                                 ;
    private String        act020StatusName                                  ;
    private Integer       act021Delay                                       ;
    private Date          act021PlanEndDate                                 ;
    private String        act021StatusName                                  ;
    private Integer       act022Delay                                       ;
    private Date          act022PlanEndDate                                 ;
    private String        act022StatusName                                  ;
    private Integer       act023Delay                                       ;
    private Date          act023PlanEndDate                                 ;
    private String        act023StatusName                                  ;


    public void    setProdProjectGubun(String prodProjectGubun){
        this.prodProjectGubun = prodProjectGubun;
    }
    public void    setPartQty(Float partQty){
        this.partQty = partQty;
    }
    public void    setChangePoint(String changePoint){
        this.changePoint = changePoint;
    }
    public void    setChangeReason(String changeReason){
        this.changeReason = changeReason;
    }
    public void    setDrw3dApprovalDate(Date drw3dApprovalDate){
        this.drw3dApprovalDate = drw3dApprovalDate;
    }
    public void    setDrw3dApprovalDate(String    drw3dApprovalDate){
        this.drw3dApprovalDate = this.omcConvertStr2Date(drw3dApprovalDate);
    }
    public void    setDrw2dApprovalDate(Date drw2dApprovalDate){
        this.drw2dApprovalDate = drw2dApprovalDate;
    }
    public void    setDrw2dApprovalDate(String    drw2dApprovalDate){
        this.drw2dApprovalDate = this.omcConvertStr2Date(drw2dApprovalDate);
    }
    public void    setDrwEcoCount(Integer drwEcoCount){
        this.drwEcoCount = drwEcoCount;
    }
    public void    setDrwFinalEcoDate(Date drwFinalEcoDate){
        this.drwFinalEcoDate = drwFinalEcoDate;
    }
    public void    setDrwFinalEcoDate(String    drwFinalEcoDate){
        this.drwFinalEcoDate = this.omcConvertStr2Date(drwFinalEcoDate);
    }
    public void    setToolingType(String toolingType){
        this.toolingType = toolingType;
    }
    public void    setToolCavity(String toolCavity){
        this.toolCavity = toolCavity;
    }
    public void    setToolmaker(String toolmaker){
        this.toolmaker = toolmaker;
    }
    public void    setToolCurrency(String toolCurrency){
        this.toolCurrency = toolCurrency;
    }
    public void    setToolCost(String toolCost){
        this.toolCost = toolCost;
    }
    public void    setToolDrwFixDate(Date toolDrwFixDate){
        this.toolDrwFixDate = toolDrwFixDate;
    }
    public void    setToolDrwFixDate(String    toolDrwFixDate){
        this.toolDrwFixDate = this.omcConvertStr2Date(toolDrwFixDate);
    }
    public void    setToolFirstShotDate(Date toolFirstShotDate){
        this.toolFirstShotDate = toolFirstShotDate;
    }
    public void    setToolFirstShotDate(String    toolFirstShotDate){
        this.toolFirstShotDate = this.omcConvertStr2Date(toolFirstShotDate);
    }
    public void    setToolTestApprovalDate(Date toolTestApprovalDate){
        this.toolTestApprovalDate = toolTestApprovalDate;
    }
    public void    setToolTestApprovalDate(String    toolTestApprovalDate){
        this.toolTestApprovalDate = this.omcConvertStr2Date(toolTestApprovalDate);
    }
    public void    setDrbfmDate(Date drbfmDate){
        this.drbfmDate = drbfmDate;
    }
    public void    setDrbfmDate(String    drbfmDate){
        this.drbfmDate = this.omcConvertStr2Date(drbfmDate);
    }
    public void    setPreiqGateDate(Date preiqGateDate){
        this.preiqGateDate = preiqGateDate;
    }
    public void    setPreiqGateDate(String    preiqGateDate){
        this.preiqGateDate = this.omcConvertStr2Date(preiqGateDate);
    }
    public void    setTestRequestDate(Date testRequestDate){
        this.testRequestDate = testRequestDate;
    }
    public void    setTestRequestDate(String    testRequestDate){
        this.testRequestDate = this.omcConvertStr2Date(testRequestDate);
    }
    public void    setTestStatus(String testStatus){
        this.testStatus = testStatus;
    }
    public void    setTestApprovalDate(Date testApprovalDate){
        this.testApprovalDate = testApprovalDate;
    }
    public void    setTestApprovalDate(String    testApprovalDate){
        this.testApprovalDate = this.omcConvertStr2Date(testApprovalDate);
    }
    public void    setTestResult(String testResult){
        this.testResult = testResult;
    }
    public void    setTestSeq(Integer testSeq){
        this.testSeq = testSeq;
    }
    public void    setYieldTargetdv(String yieldTargetdv){
        this.yieldTargetdv = yieldTargetdv;
    }
    public void    setYieldResultdv(String yieldResultdv){
        this.yieldResultdv = yieldResultdv;
    }
    public void    setYieldTargetpv(String yieldTargetpv){
        this.yieldTargetpv = yieldTargetpv;
    }
    public void    setYieldResultpv(String yieldResultpv){
        this.yieldResultpv = yieldResultpv;
    }
    public void    setYieldTargetPreMp(String yieldTargetPreMp){
        this.yieldTargetPreMp = yieldTargetPreMp;
    }
    public void    setYieldResultPreMp(String yieldResultPreMp){
        this.yieldResultPreMp = yieldResultPreMp;
    }
    public void    setPriceTargetDesign(String priceTargetDesign){
        this.priceTargetDesign = priceTargetDesign;
    }
    public void    setPriceBase(String priceBase){
        this.priceBase = priceBase;
    }
    public void    setPriceFirstProd(String priceFirstProd){
        this.priceFirstProd = priceFirstProd;
    }
    public void    setAct001Sd(Integer act001Sd){
        this.act001Sd = act001Sd;
    }
    public void    setAct001ActualEndDate(Date act001ActualEndDate){
        this.act001ActualEndDate = act001ActualEndDate;
    }
    public void    setAct001ActualEndDate(String    act001ActualEndDate){
        this.act001ActualEndDate = this.omcConvertStr2Date(act001ActualEndDate);
    }
    public void    setAct001OwnerName(String act001OwnerName){
        this.act001OwnerName = act001OwnerName;
    }
    public void    setAct002Sd(Integer act002Sd){
        this.act002Sd = act002Sd;
    }
    public void    setAct002ActualEndDate(Date act002ActualEndDate){
        this.act002ActualEndDate = act002ActualEndDate;
    }
    public void    setAct002ActualEndDate(String    act002ActualEndDate){
        this.act002ActualEndDate = this.omcConvertStr2Date(act002ActualEndDate);
    }
    public void    setAct002OwnerName(String act002OwnerName){
        this.act002OwnerName = act002OwnerName;
    }
    public void    setAct003Sd(Integer act003Sd){
        this.act003Sd = act003Sd;
    }
    public void    setAct003ActualEndDate(Date act003ActualEndDate){
        this.act003ActualEndDate = act003ActualEndDate;
    }
    public void    setAct003ActualEndDate(String    act003ActualEndDate){
        this.act003ActualEndDate = this.omcConvertStr2Date(act003ActualEndDate);
    }
    public void    setAct003OwnerName(String act003OwnerName){
        this.act003OwnerName = act003OwnerName;
    }
    public void    setAct004Sd(Integer act004Sd){
        this.act004Sd = act004Sd;
    }
    public void    setAct004ActualEndDate(Date act004ActualEndDate){
        this.act004ActualEndDate = act004ActualEndDate;
    }
    public void    setAct004ActualEndDate(String    act004ActualEndDate){
        this.act004ActualEndDate = this.omcConvertStr2Date(act004ActualEndDate);
    }
    public void    setAct004OwnerName(String act004OwnerName){
        this.act004OwnerName = act004OwnerName;
    }
    public void    setAct005Sd(Integer act005Sd){
        this.act005Sd = act005Sd;
    }
    public void    setAct005ActualEndDate(Date act005ActualEndDate){
        this.act005ActualEndDate = act005ActualEndDate;
    }
    public void    setAct005ActualEndDate(String    act005ActualEndDate){
        this.act005ActualEndDate = this.omcConvertStr2Date(act005ActualEndDate);
    }
    public void    setAct005OwnerName(String act005OwnerName){
        this.act005OwnerName = act005OwnerName;
    }
    public void    setAct006Sd(Integer act006Sd){
        this.act006Sd = act006Sd;
    }
    public void    setAct006ActualEndDate(Date act006ActualEndDate){
        this.act006ActualEndDate = act006ActualEndDate;
    }
    public void    setAct006ActualEndDate(String    act006ActualEndDate){
        this.act006ActualEndDate = this.omcConvertStr2Date(act006ActualEndDate);
    }
    public void    setAct006OwnerName(String act006OwnerName){
        this.act006OwnerName = act006OwnerName;
    }
    public void    setAct007Sd(Integer act007Sd){
        this.act007Sd = act007Sd;
    }
    public void    setAct007ActualEndDate(Date act007ActualEndDate){
        this.act007ActualEndDate = act007ActualEndDate;
    }
    public void    setAct007ActualEndDate(String    act007ActualEndDate){
        this.act007ActualEndDate = this.omcConvertStr2Date(act007ActualEndDate);
    }
    public void    setAct007OwnerName(String act007OwnerName){
        this.act007OwnerName = act007OwnerName;
    }
    public void    setAct008Sd(Integer act008Sd){
        this.act008Sd = act008Sd;
    }
    public void    setAct008ActualEndDate(Date act008ActualEndDate){
        this.act008ActualEndDate = act008ActualEndDate;
    }
    public void    setAct008ActualEndDate(String    act008ActualEndDate){
        this.act008ActualEndDate = this.omcConvertStr2Date(act008ActualEndDate);
    }
    public void    setAct008OwnerName(String act008OwnerName){
        this.act008OwnerName = act008OwnerName;
    }
    public void    setAct009Sd(Integer act009Sd){
        this.act009Sd = act009Sd;
    }
    public void    setAct009ActualEndDate(Date act009ActualEndDate){
        this.act009ActualEndDate = act009ActualEndDate;
    }
    public void    setAct009ActualEndDate(String    act009ActualEndDate){
        this.act009ActualEndDate = this.omcConvertStr2Date(act009ActualEndDate);
    }
    public void    setAct009OwnerName(String act009OwnerName){
        this.act009OwnerName = act009OwnerName;
    }
    public void    setAct010Sd(Integer act010Sd){
        this.act010Sd = act010Sd;
    }
    public void    setAct010ActualEndDate(Date act010ActualEndDate){
        this.act010ActualEndDate = act010ActualEndDate;
    }
    public void    setAct010ActualEndDate(String    act010ActualEndDate){
        this.act010ActualEndDate = this.omcConvertStr2Date(act010ActualEndDate);
    }
    public void    setAct010OwnerName(String act010OwnerName){
        this.act010OwnerName = act010OwnerName;
    }
    public void    setAct011Sd(Integer act011Sd){
        this.act011Sd = act011Sd;
    }
    public void    setAct011ActualEndDate(Date act011ActualEndDate){
        this.act011ActualEndDate = act011ActualEndDate;
    }
    public void    setAct011ActualEndDate(String    act011ActualEndDate){
        this.act011ActualEndDate = this.omcConvertStr2Date(act011ActualEndDate);
    }
    public void    setAct011OwnerName(String act011OwnerName){
        this.act011OwnerName = act011OwnerName;
    }
    public void    setAct012Sd(Integer act012Sd){
        this.act012Sd = act012Sd;
    }
    public void    setAct012ActualEndDate(Date act012ActualEndDate){
        this.act012ActualEndDate = act012ActualEndDate;
    }
    public void    setAct012ActualEndDate(String    act012ActualEndDate){
        this.act012ActualEndDate = this.omcConvertStr2Date(act012ActualEndDate);
    }
    public void    setAct012OwnerName(String act012OwnerName){
        this.act012OwnerName = act012OwnerName;
    }
    public void    setAct013Sd(Integer act013Sd){
        this.act013Sd = act013Sd;
    }
    public void    setAct013ActualEndDate(Date act013ActualEndDate){
        this.act013ActualEndDate = act013ActualEndDate;
    }
    public void    setAct013ActualEndDate(String    act013ActualEndDate){
        this.act013ActualEndDate = this.omcConvertStr2Date(act013ActualEndDate);
    }
    public void    setAct013OwnerName(String act013OwnerName){
        this.act013OwnerName = act013OwnerName;
    }
    public void    setAct014Sd(Integer act014Sd){
        this.act014Sd = act014Sd;
    }
    public void    setAct014ActualEndDate(Date act014ActualEndDate){
        this.act014ActualEndDate = act014ActualEndDate;
    }
    public void    setAct014ActualEndDate(String    act014ActualEndDate){
        this.act014ActualEndDate = this.omcConvertStr2Date(act014ActualEndDate);
    }
    public void    setAct014OwnerName(String act014OwnerName){
        this.act014OwnerName = act014OwnerName;
    }
    public void    setAct015Sd(Integer act015Sd){
        this.act015Sd = act015Sd;
    }
    public void    setAct015ActualEndDate(Date act015ActualEndDate){
        this.act015ActualEndDate = act015ActualEndDate;
    }
    public void    setAct015ActualEndDate(String    act015ActualEndDate){
        this.act015ActualEndDate = this.omcConvertStr2Date(act015ActualEndDate);
    }
    public void    setAct015OwnerName(String act015OwnerName){
        this.act015OwnerName = act015OwnerName;
    }
    public void    setAct016Sd(Integer act016Sd){
        this.act016Sd = act016Sd;
    }
    public void    setAct016ActualEndDate(Date act016ActualEndDate){
        this.act016ActualEndDate = act016ActualEndDate;
    }
    public void    setAct016ActualEndDate(String    act016ActualEndDate){
        this.act016ActualEndDate = this.omcConvertStr2Date(act016ActualEndDate);
    }
    public void    setAct016OwnerName(String act016OwnerName){
        this.act016OwnerName = act016OwnerName;
    }
    public void    setAct017Sd(Integer act017Sd){
        this.act017Sd = act017Sd;
    }
    public void    setAct017ActualEndDate(Date act017ActualEndDate){
        this.act017ActualEndDate = act017ActualEndDate;
    }
    public void    setAct017ActualEndDate(String    act017ActualEndDate){
        this.act017ActualEndDate = this.omcConvertStr2Date(act017ActualEndDate);
    }
    public void    setAct017OwnerName(String act017OwnerName){
        this.act017OwnerName = act017OwnerName;
    }
    public void    setAct018Sd(Integer act018Sd){
        this.act018Sd = act018Sd;
    }
    public void    setAct018ActualEndDate(Date act018ActualEndDate){
        this.act018ActualEndDate = act018ActualEndDate;
    }
    public void    setAct018ActualEndDate(String    act018ActualEndDate){
        this.act018ActualEndDate = this.omcConvertStr2Date(act018ActualEndDate);
    }
    public void    setAct018OwnerName(String act018OwnerName){
        this.act018OwnerName = act018OwnerName;
    }
    public void    setAct019Sd(Integer act019Sd){
        this.act019Sd = act019Sd;
    }
    public void    setAct019ActualEndDate(Date act019ActualEndDate){
        this.act019ActualEndDate = act019ActualEndDate;
    }
    public void    setAct019ActualEndDate(String    act019ActualEndDate){
        this.act019ActualEndDate = this.omcConvertStr2Date(act019ActualEndDate);
    }
    public void    setAct019OwnerName(String act019OwnerName){
        this.act019OwnerName = act019OwnerName;
    }
    public void    setAct019testSeq(Integer act019testSeq){
        this.act019testSeq = act019testSeq;
    }
    public void    setAct020Sd(Integer act020Sd){
        this.act020Sd = act020Sd;
    }
    public void    setAct020ActualEndDate(Date act020ActualEndDate){
        this.act020ActualEndDate = act020ActualEndDate;
    }
    public void    setAct020ActualEndDate(String    act020ActualEndDate){
        this.act020ActualEndDate = this.omcConvertStr2Date(act020ActualEndDate);
    }
    public void    setAct020OwnerName(String act020OwnerName){
        this.act020OwnerName = act020OwnerName;
    }
    public void    setAct020testStaus(String act020testStaus){
        this.act020testStaus = act020testStaus;
    }
    public void    setAct020testSeq(Integer act020testSeq){
        this.act020testSeq = act020testSeq;
    }
    public void    setAct020testResult(String act020testResult){
        this.act020testResult = act020testResult;
    }
    public void    setAct021Sd(Integer act021Sd){
        this.act021Sd = act021Sd;
    }
    public void    setAct021ActualEndDate(Date act021ActualEndDate){
        this.act021ActualEndDate = act021ActualEndDate;
    }
    public void    setAct021ActualEndDate(String    act021ActualEndDate){
        this.act021ActualEndDate = this.omcConvertStr2Date(act021ActualEndDate);
    }
    public void    setAct021OwnerName(String act021OwnerName){
        this.act021OwnerName = act021OwnerName;
    }
    public void    setAct022Sd(Integer act022Sd){
        this.act022Sd = act022Sd;
    }
    public void    setAct022ActualEndDate(Date act022ActualEndDate){
        this.act022ActualEndDate = act022ActualEndDate;
    }
    public void    setAct022ActualEndDate(String    act022ActualEndDate){
        this.act022ActualEndDate = this.omcConvertStr2Date(act022ActualEndDate);
    }
    public void    setAct022OwnerName(String act022OwnerName){
        this.act022OwnerName = act022OwnerName;
    }
    public void    setAct023Sd(Integer act023Sd){
        this.act023Sd = act023Sd;
    }
    public void    setAct023ActualEndDate(Date act023ActualEndDate){
        this.act023ActualEndDate = act023ActualEndDate;
    }
    public void    setAct023ActualEndDate(String    act023ActualEndDate){
        this.act023ActualEndDate = this.omcConvertStr2Date(act023ActualEndDate);
    }
    public void    setAct023OwnerName(String act023OwnerName){
        this.act023OwnerName = act023OwnerName;
    }
    public void    setRolePartDevelopment(String rolePartDevelopment){
        this.rolePartDevelopment = rolePartDevelopment;
    }
    public void    setRolePurchaser(String rolePurchaser){
        this.rolePurchaser = rolePurchaser;
    }
    public void    setRolesqe(String rolesqe){
        this.rolesqe = rolesqe;
    }
    public void    setRoleTooling(String roleTooling){
        this.roleTooling = roleTooling;
    }
    public void    setRolepdm(String rolepdm){
        this.rolepdm = rolepdm;
    }
    public void    setRolePartQuality(String rolePartQuality){
        this.rolePartQuality = rolePartQuality;
    }
    public void    setRoleProdQuality(String roleProdQuality){
        this.roleProdQuality = roleProdQuality;
    }
    public void    setRoleEngineer(String roleEngineer){
        this.roleEngineer = roleEngineer;
    }
    public void    setRoleSupport(String roleSupport){
        this.roleSupport = roleSupport;
    }
    public void    setAct001PlanEndDate(Date act001PlanEndDate){
        this.act001PlanEndDate = act001PlanEndDate;
    }
    public void    setAct001PlanEndDate(String    act001PlanEndDate){
        this.act001PlanEndDate = this.omcConvertStr2Date(act001PlanEndDate);
    }
    public void    setAct001StatusName(String act001StatusName){
        this.act001StatusName = act001StatusName;
    }
    public void    setAct002Delay(Integer act002Delay){
        this.act002Delay = act002Delay;
    }
    public void    setAct002PlanEndDate(Date act002PlanEndDate){
        this.act002PlanEndDate = act002PlanEndDate;
    }
    public void    setAct002PlanEndDate(String    act002PlanEndDate){
        this.act002PlanEndDate = this.omcConvertStr2Date(act002PlanEndDate);
    }
    public void    setAct002StatusName(String act002StatusName){
        this.act002StatusName = act002StatusName;
    }
    public void    setAct003Delay(Integer act003Delay){
        this.act003Delay = act003Delay;
    }
    public void    setAct003PlanEndDate(Date act003PlanEndDate){
        this.act003PlanEndDate = act003PlanEndDate;
    }
    public void    setAct003PlanEndDate(String    act003PlanEndDate){
        this.act003PlanEndDate = this.omcConvertStr2Date(act003PlanEndDate);
    }
    public void    setAct003StatusName(String act003StatusName){
        this.act003StatusName = act003StatusName;
    }
    public void    setAct004Delay(Integer act004Delay){
        this.act004Delay = act004Delay;
    }
    public void    setAct004PlanEndDate(Date act004PlanEndDate){
        this.act004PlanEndDate = act004PlanEndDate;
    }
    public void    setAct004PlanEndDate(String    act004PlanEndDate){
        this.act004PlanEndDate = this.omcConvertStr2Date(act004PlanEndDate);
    }
    public void    setAct004StatusName(String act004StatusName){
        this.act004StatusName = act004StatusName;
    }
    public void    setAct005Delay(Integer act005Delay){
        this.act005Delay = act005Delay;
    }
    public void    setAct005PlanEndDate(Date act005PlanEndDate){
        this.act005PlanEndDate = act005PlanEndDate;
    }
    public void    setAct005PlanEndDate(String    act005PlanEndDate){
        this.act005PlanEndDate = this.omcConvertStr2Date(act005PlanEndDate);
    }
    public void    setAct005StatusName(String act005StatusName){
        this.act005StatusName = act005StatusName;
    }
    public void    setAct006Delay(Integer act006Delay){
        this.act006Delay = act006Delay;
    }
    public void    setAct006PlanEndDate(Date act006PlanEndDate){
        this.act006PlanEndDate = act006PlanEndDate;
    }
    public void    setAct006PlanEndDate(String    act006PlanEndDate){
        this.act006PlanEndDate = this.omcConvertStr2Date(act006PlanEndDate);
    }
    public void    setAct006StatusName(String act006StatusName){
        this.act006StatusName = act006StatusName;
    }
    public void    setAct007Delay(Integer act007Delay){
        this.act007Delay = act007Delay;
    }
    public void    setAct007PlanEndDate(Date act007PlanEndDate){
        this.act007PlanEndDate = act007PlanEndDate;
    }
    public void    setAct007PlanEndDate(String    act007PlanEndDate){
        this.act007PlanEndDate = this.omcConvertStr2Date(act007PlanEndDate);
    }
    public void    setAct007StatusName(String act007StatusName){
        this.act007StatusName = act007StatusName;
    }
    public void    setAct008Delay(Integer act008Delay){
        this.act008Delay = act008Delay;
    }
    public void    setAct008PlanEndDate(Date act008PlanEndDate){
        this.act008PlanEndDate = act008PlanEndDate;
    }
    public void    setAct008PlanEndDate(String    act008PlanEndDate){
        this.act008PlanEndDate = this.omcConvertStr2Date(act008PlanEndDate);
    }
    public void    setAct008StatusName(String act008StatusName){
        this.act008StatusName = act008StatusName;
    }
    public void    setAct009Delay(Integer act009Delay){
        this.act009Delay = act009Delay;
    }
    public void    setAct009PlanEndDate(Date act009PlanEndDate){
        this.act009PlanEndDate = act009PlanEndDate;
    }
    public void    setAct009PlanEndDate(String    act009PlanEndDate){
        this.act009PlanEndDate = this.omcConvertStr2Date(act009PlanEndDate);
    }
    public void    setAct009StatusName(String act009StatusName){
        this.act009StatusName = act009StatusName;
    }
    public void    setAct010Delay(Integer act010Delay){
        this.act010Delay = act010Delay;
    }
    public void    setAct010PlanEndDate(Date act010PlanEndDate){
        this.act010PlanEndDate = act010PlanEndDate;
    }
    public void    setAct010PlanEndDate(String    act010PlanEndDate){
        this.act010PlanEndDate = this.omcConvertStr2Date(act010PlanEndDate);
    }
    public void    setAct010StatusName(String act010StatusName){
        this.act010StatusName = act010StatusName;
    }
    public void    setAct011Delay(Integer act011Delay){
        this.act011Delay = act011Delay;
    }
    public void    setAct011PlanEndDate(Date act011PlanEndDate){
        this.act011PlanEndDate = act011PlanEndDate;
    }
    public void    setAct011PlanEndDate(String    act011PlanEndDate){
        this.act011PlanEndDate = this.omcConvertStr2Date(act011PlanEndDate);
    }
    public void    setAct011StatusName(String act011StatusName){
        this.act011StatusName = act011StatusName;
    }
    public void    setAct012Delay(Integer act012Delay){
        this.act012Delay = act012Delay;
    }
    public void    setAct012PlanEndDate(Date act012PlanEndDate){
        this.act012PlanEndDate = act012PlanEndDate;
    }
    public void    setAct012PlanEndDate(String    act012PlanEndDate){
        this.act012PlanEndDate = this.omcConvertStr2Date(act012PlanEndDate);
    }
    public void    setAct012StatusName(String act012StatusName){
        this.act012StatusName = act012StatusName;
    }
    public void    setAct013Delay(Integer act013Delay){
        this.act013Delay = act013Delay;
    }
    public void    setAct013PlanEndDate(Date act013PlanEndDate){
        this.act013PlanEndDate = act013PlanEndDate;
    }
    public void    setAct013PlanEndDate(String    act013PlanEndDate){
        this.act013PlanEndDate = this.omcConvertStr2Date(act013PlanEndDate);
    }
    public void    setAct013StatusName(String act013StatusName){
        this.act013StatusName = act013StatusName;
    }
    public void    setAct014Delay(Integer act014Delay){
        this.act014Delay = act014Delay;
    }
    public void    setAct014PlanEndDate(Date act014PlanEndDate){
        this.act014PlanEndDate = act014PlanEndDate;
    }
    public void    setAct014PlanEndDate(String    act014PlanEndDate){
        this.act014PlanEndDate = this.omcConvertStr2Date(act014PlanEndDate);
    }
    public void    setAct014StatusName(String act014StatusName){
        this.act014StatusName = act014StatusName;
    }
    public void    setAct015Delay(Integer act015Delay){
        this.act015Delay = act015Delay;
    }
    public void    setAct015PlanEndDate(Date act015PlanEndDate){
        this.act015PlanEndDate = act015PlanEndDate;
    }
    public void    setAct015PlanEndDate(String    act015PlanEndDate){
        this.act015PlanEndDate = this.omcConvertStr2Date(act015PlanEndDate);
    }
    public void    setAct015StatusName(String act015StatusName){
        this.act015StatusName = act015StatusName;
    }
    public void    setAct016Delay(Integer act016Delay){
        this.act016Delay = act016Delay;
    }
    public void    setAct016PlanEndDate(Date act016PlanEndDate){
        this.act016PlanEndDate = act016PlanEndDate;
    }
    public void    setAct016PlanEndDate(String    act016PlanEndDate){
        this.act016PlanEndDate = this.omcConvertStr2Date(act016PlanEndDate);
    }
    public void    setAct016StatusName(String act016StatusName){
        this.act016StatusName = act016StatusName;
    }
    public void    setAct017Delay(Integer act017Delay){
        this.act017Delay = act017Delay;
    }
    public void    setAct017PlanEndDate(Date act017PlanEndDate){
        this.act017PlanEndDate = act017PlanEndDate;
    }
    public void    setAct017PlanEndDate(String    act017PlanEndDate){
        this.act017PlanEndDate = this.omcConvertStr2Date(act017PlanEndDate);
    }
    public void    setAct017StatusName(String act017StatusName){
        this.act017StatusName = act017StatusName;
    }
    public void    setAct018Delay(Integer act018Delay){
        this.act018Delay = act018Delay;
    }
    public void    setAct018PlanEndDate(Date act018PlanEndDate){
        this.act018PlanEndDate = act018PlanEndDate;
    }
    public void    setAct018PlanEndDate(String    act018PlanEndDate){
        this.act018PlanEndDate = this.omcConvertStr2Date(act018PlanEndDate);
    }
    public void    setAct018StatusName(String act018StatusName){
        this.act018StatusName = act018StatusName;
    }
    public void    setAct019Delay(Integer act019Delay){
        this.act019Delay = act019Delay;
    }
    public void    setAct019PlanEndDate(Date act019PlanEndDate){
        this.act019PlanEndDate = act019PlanEndDate;
    }
    public void    setAct019PlanEndDate(String    act019PlanEndDate){
        this.act019PlanEndDate = this.omcConvertStr2Date(act019PlanEndDate);
    }
    public void    setAct019StatusName(String act019StatusName){
        this.act019StatusName = act019StatusName;
    }
    public void    setAct020Delay(Integer act020Delay){
        this.act020Delay = act020Delay;
    }
    public void    setAct020PlanEndDate(Date act020PlanEndDate){
        this.act020PlanEndDate = act020PlanEndDate;
    }
    public void    setAct020PlanEndDate(String    act020PlanEndDate){
        this.act020PlanEndDate = this.omcConvertStr2Date(act020PlanEndDate);
    }
    public void    setAct020StatusName(String act020StatusName){
        this.act020StatusName = act020StatusName;
    }
    public void    setAct021Delay(Integer act021Delay){
        this.act021Delay = act021Delay;
    }
    public void    setAct021PlanEndDate(Date act021PlanEndDate){
        this.act021PlanEndDate = act021PlanEndDate;
    }
    public void    setAct021PlanEndDate(String    act021PlanEndDate){
        this.act021PlanEndDate = this.omcConvertStr2Date(act021PlanEndDate);
    }
    public void    setAct021StatusName(String act021StatusName){
        this.act021StatusName = act021StatusName;
    }
    public void    setAct022Delay(Integer act022Delay){
        this.act022Delay = act022Delay;
    }
    public void    setAct022PlanEndDate(Date act022PlanEndDate){
        this.act022PlanEndDate = act022PlanEndDate;
    }
    public void    setAct022PlanEndDate(String    act022PlanEndDate){
        this.act022PlanEndDate = this.omcConvertStr2Date(act022PlanEndDate);
    }
    public void    setAct022StatusName(String act022StatusName){
        this.act022StatusName = act022StatusName;
    }
    public void    setAct023Delay(Integer act023Delay){
        this.act023Delay = act023Delay;
    }
    public void    setAct023PlanEndDate(Date act023PlanEndDate){
        this.act023PlanEndDate = act023PlanEndDate;
    }
    public void    setAct023PlanEndDate(String    act023PlanEndDate){
        this.act023PlanEndDate = this.omcConvertStr2Date(act023PlanEndDate);
    }
    public void    setAct023StatusName(String act023StatusName){
        this.act023StatusName = act023StatusName;
    }
    public String getProdProjectGubun(){
        return prodProjectGubun;
    }
    public Float getPartQty(){
        return partQty;
    }
    public String getChangePoint(){
        return changePoint;
    }
    public String getChangeReason(){
        return changeReason;
    }
    public Date getDrw3dApprovalDate(){
        return drw3dApprovalDate;
    }
    public Date getDrw2dApprovalDate(){
        return drw2dApprovalDate;
    }
    public Integer getDrwEcoCount(){
        return drwEcoCount;
    }
    public Date getDrwFinalEcoDate(){
        return drwFinalEcoDate;
    }
    public String getToolingType(){
        return toolingType;
    }
    public String getToolCavity(){
        return toolCavity;
    }
    public String getToolmaker(){
        return toolmaker;
    }
    public String getToolCurrency(){
        return toolCurrency;
    }
    public String getToolCost(){
        return toolCost;
    }
    public Date getToolDrwFixDate(){
        return toolDrwFixDate;
    }
    public Date getToolFirstShotDate(){
        return toolFirstShotDate;
    }
    public Date getToolTestApprovalDate(){
        return toolTestApprovalDate;
    }
    public Date getDrbfmDate(){
        return drbfmDate;
    }
    public Date getPreiqGateDate(){
        return preiqGateDate;
    }
    public Date getTestRequestDate(){
        return testRequestDate;
    }
    public String getTestStatus(){
        return testStatus;
    }
    public Date getTestApprovalDate(){
        return testApprovalDate;
    }
    public String getTestResult(){
        return testResult;
    }
    public Integer getTestSeq(){
        return testSeq;
    }
    public String getYieldTargetdv(){
        return yieldTargetdv;
    }
    public String getYieldResultdv(){
        return yieldResultdv;
    }
    public String getYieldTargetpv(){
        return yieldTargetpv;
    }
    public String getYieldResultpv(){
        return yieldResultpv;
    }
    public String getYieldTargetPreMp(){
        return yieldTargetPreMp;
    }
    public String getYieldResultPreMp(){
        return yieldResultPreMp;
    }
    public String getPriceTargetDesign(){
        return priceTargetDesign;
    }
    public String getPriceBase(){
        return priceBase;
    }
    public String getPriceFirstProd(){
        return priceFirstProd;
    }
    public Integer getAct001Sd(){
        return act001Sd;
    }
    public Date getAct001ActualEndDate(){
        return act001ActualEndDate;
    }
    public String getAct001OwnerName(){
        return act001OwnerName;
    }
    public Integer getAct002Sd(){
        return act002Sd;
    }
    public Date getAct002ActualEndDate(){
        return act002ActualEndDate;
    }
    public String getAct002OwnerName(){
        return act002OwnerName;
    }
    public Integer getAct003Sd(){
        return act003Sd;
    }
    public Date getAct003ActualEndDate(){
        return act003ActualEndDate;
    }
    public String getAct003OwnerName(){
        return act003OwnerName;
    }
    public Integer getAct004Sd(){
        return act004Sd;
    }
    public Date getAct004ActualEndDate(){
        return act004ActualEndDate;
    }
    public String getAct004OwnerName(){
        return act004OwnerName;
    }
    public Integer getAct005Sd(){
        return act005Sd;
    }
    public Date getAct005ActualEndDate(){
        return act005ActualEndDate;
    }
    public String getAct005OwnerName(){
        return act005OwnerName;
    }
    public Integer getAct006Sd(){
        return act006Sd;
    }
    public Date getAct006ActualEndDate(){
        return act006ActualEndDate;
    }
    public String getAct006OwnerName(){
        return act006OwnerName;
    }
    public Integer getAct007Sd(){
        return act007Sd;
    }
    public Date getAct007ActualEndDate(){
        return act007ActualEndDate;
    }
    public String getAct007OwnerName(){
        return act007OwnerName;
    }
    public Integer getAct008Sd(){
        return act008Sd;
    }
    public Date getAct008ActualEndDate(){
        return act008ActualEndDate;
    }
    public String getAct008OwnerName(){
        return act008OwnerName;
    }
    public Integer getAct009Sd(){
        return act009Sd;
    }
    public Date getAct009ActualEndDate(){
        return act009ActualEndDate;
    }
    public String getAct009OwnerName(){
        return act009OwnerName;
    }
    public Integer getAct010Sd(){
        return act010Sd;
    }
    public Date getAct010ActualEndDate(){
        return act010ActualEndDate;
    }
    public String getAct010OwnerName(){
        return act010OwnerName;
    }
    public Integer getAct011Sd(){
        return act011Sd;
    }
    public Date getAct011ActualEndDate(){
        return act011ActualEndDate;
    }
    public String getAct011OwnerName(){
        return act011OwnerName;
    }
    public Integer getAct012Sd(){
        return act012Sd;
    }
    public Date getAct012ActualEndDate(){
        return act012ActualEndDate;
    }
    public String getAct012OwnerName(){
        return act012OwnerName;
    }
    public Integer getAct013Sd(){
        return act013Sd;
    }
    public Date getAct013ActualEndDate(){
        return act013ActualEndDate;
    }
    public String getAct013OwnerName(){
        return act013OwnerName;
    }
    public Integer getAct014Sd(){
        return act014Sd;
    }
    public Date getAct014ActualEndDate(){
        return act014ActualEndDate;
    }
    public String getAct014OwnerName(){
        return act014OwnerName;
    }
    public Integer getAct015Sd(){
        return act015Sd;
    }
    public Date getAct015ActualEndDate(){
        return act015ActualEndDate;
    }
    public String getAct015OwnerName(){
        return act015OwnerName;
    }
    public Integer getAct016Sd(){
        return act016Sd;
    }
    public Date getAct016ActualEndDate(){
        return act016ActualEndDate;
    }
    public String getAct016OwnerName(){
        return act016OwnerName;
    }
    public Integer getAct017Sd(){
        return act017Sd;
    }
    public Date getAct017ActualEndDate(){
        return act017ActualEndDate;
    }
    public String getAct017OwnerName(){
        return act017OwnerName;
    }
    public Integer getAct018Sd(){
        return act018Sd;
    }
    public Date getAct018ActualEndDate(){
        return act018ActualEndDate;
    }
    public String getAct018OwnerName(){
        return act018OwnerName;
    }
    public Integer getAct019Sd(){
        return act019Sd;
    }
    public Date getAct019ActualEndDate(){
        return act019ActualEndDate;
    }
    public String getAct019OwnerName(){
        return act019OwnerName;
    }
    public Integer getAct019testSeq(){
        return act019testSeq;
    }
    public Integer getAct020Sd(){
        return act020Sd;
    }
    public Date getAct020ActualEndDate(){
        return act020ActualEndDate;
    }
    public String getAct020OwnerName(){
        return act020OwnerName;
    }
    public String getAct020testStaus(){
        return act020testStaus;
    }
    public Integer getAct020testSeq(){
        return act020testSeq;
    }
    public String getAct020testResult(){
        return act020testResult;
    }
    public Integer getAct021Sd(){
        return act021Sd;
    }
    public Date getAct021ActualEndDate(){
        return act021ActualEndDate;
    }
    public String getAct021OwnerName(){
        return act021OwnerName;
    }
    public Integer getAct022Sd(){
        return act022Sd;
    }
    public Date getAct022ActualEndDate(){
        return act022ActualEndDate;
    }
    public String getAct022OwnerName(){
        return act022OwnerName;
    }
    public Integer getAct023Sd(){
        return act023Sd;
    }
    public Date getAct023ActualEndDate(){
        return act023ActualEndDate;
    }
    public String getAct023OwnerName(){
        return act023OwnerName;
    }
    public String getRolePartDevelopment(){
        return rolePartDevelopment;
    }
    public String getRolePurchaser(){
        return rolePurchaser;
    }
    public String getRolesqe(){
        return rolesqe;
    }
    public String getRoleTooling(){
        return roleTooling;
    }
    public String getRolepdm(){
        return rolepdm;
    }
    public String getRolePartQuality(){
        return rolePartQuality;
    }
    public String getRoleProdQuality(){
        return roleProdQuality;
    }
    public String getRoleEngineer(){
        return roleEngineer;
    }
    public String getRoleSupport(){
        return roleSupport;
    }
    public Date getAct001PlanEndDate(){
        return act001PlanEndDate;
    }
    public String getAct001StatusName(){
        return act001StatusName;
    }
    public Integer getAct002Delay(){
        return act002Delay;
    }
    public Date getAct002PlanEndDate(){
        return act002PlanEndDate;
    }
    public String getAct002StatusName(){
        return act002StatusName;
    }
    public Integer getAct003Delay(){
        return act003Delay;
    }
    public Date getAct003PlanEndDate(){
        return act003PlanEndDate;
    }
    public String getAct003StatusName(){
        return act003StatusName;
    }
    public Integer getAct004Delay(){
        return act004Delay;
    }
    public Date getAct004PlanEndDate(){
        return act004PlanEndDate;
    }
    public String getAct004StatusName(){
        return act004StatusName;
    }
    public Integer getAct005Delay(){
        return act005Delay;
    }
    public Date getAct005PlanEndDate(){
        return act005PlanEndDate;
    }
    public String getAct005StatusName(){
        return act005StatusName;
    }
    public Integer getAct006Delay(){
        return act006Delay;
    }
    public Date getAct006PlanEndDate(){
        return act006PlanEndDate;
    }
    public String getAct006StatusName(){
        return act006StatusName;
    }
    public Integer getAct007Delay(){
        return act007Delay;
    }
    public Date getAct007PlanEndDate(){
        return act007PlanEndDate;
    }
    public String getAct007StatusName(){
        return act007StatusName;
    }
    public Integer getAct008Delay(){
        return act008Delay;
    }
    public Date getAct008PlanEndDate(){
        return act008PlanEndDate;
    }
    public String getAct008StatusName(){
        return act008StatusName;
    }
    public Integer getAct009Delay(){
        return act009Delay;
    }
    public Date getAct009PlanEndDate(){
        return act009PlanEndDate;
    }
    public String getAct009StatusName(){
        return act009StatusName;
    }
    public Integer getAct010Delay(){
        return act010Delay;
    }
    public Date getAct010PlanEndDate(){
        return act010PlanEndDate;
    }
    public String getAct010StatusName(){
        return act010StatusName;
    }
    public Integer getAct011Delay(){
        return act011Delay;
    }
    public Date getAct011PlanEndDate(){
        return act011PlanEndDate;
    }
    public String getAct011StatusName(){
        return act011StatusName;
    }
    public Integer getAct012Delay(){
        return act012Delay;
    }
    public Date getAct012PlanEndDate(){
        return act012PlanEndDate;
    }
    public String getAct012StatusName(){
        return act012StatusName;
    }
    public Integer getAct013Delay(){
        return act013Delay;
    }
    public Date getAct013PlanEndDate(){
        return act013PlanEndDate;
    }
    public String getAct013StatusName(){
        return act013StatusName;
    }
    public Integer getAct014Delay(){
        return act014Delay;
    }
    public Date getAct014PlanEndDate(){
        return act014PlanEndDate;
    }
    public String getAct014StatusName(){
        return act014StatusName;
    }
    public Integer getAct015Delay(){
        return act015Delay;
    }
    public Date getAct015PlanEndDate(){
        return act015PlanEndDate;
    }
    public String getAct015StatusName(){
        return act015StatusName;
    }
    public Integer getAct016Delay(){
        return act016Delay;
    }
    public Date getAct016PlanEndDate(){
        return act016PlanEndDate;
    }
    public String getAct016StatusName(){
        return act016StatusName;
    }
    public Integer getAct017Delay(){
        return act017Delay;
    }
    public Date getAct017PlanEndDate(){
        return act017PlanEndDate;
    }
    public String getAct017StatusName(){
        return act017StatusName;
    }
    public Integer getAct018Delay(){
        return act018Delay;
    }
    public Date getAct018PlanEndDate(){
        return act018PlanEndDate;
    }
    public String getAct018StatusName(){
        return act018StatusName;
    }
    public Integer getAct019Delay(){
        return act019Delay;
    }
    public Date getAct019PlanEndDate(){
        return act019PlanEndDate;
    }
    public String getAct019StatusName(){
        return act019StatusName;
    }
    public Integer getAct020Delay(){
        return act020Delay;
    }
    public Date getAct020PlanEndDate(){
        return act020PlanEndDate;
    }
    public String getAct020StatusName(){
        return act020StatusName;
    }
    public Integer getAct021Delay(){
        return act021Delay;
    }
    public Date getAct021PlanEndDate(){
        return act021PlanEndDate;
    }
    public String getAct021StatusName(){
        return act021StatusName;
    }
    public Integer getAct022Delay(){
        return act022Delay;
    }
    public Date getAct022PlanEndDate(){
        return act022PlanEndDate;
    }
    public String getAct022StatusName(){
        return act022StatusName;
    }
    public Integer getAct023Delay(){
        return act023Delay;
    }
    public Date getAct023PlanEndDate(){
        return act023PlanEndDate;
    }
    public String getAct023StatusName(){
        return act023StatusName;
    }
}

