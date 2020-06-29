/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : VCStdMatrixVO.java
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
public class VCStdMatrixVO extends BusinessObjectMasterVO {
    private String        matrixCode                                        ;
    private String        matrixType                                        ;
    private String        matrixAttribute1                                  ;
    private String        matrixAttribute2                                  ;
    private BigDecimal    matrixStepCv1                                      = new BigDecimal(0);
    private BigDecimal    matrixStepCv2                                      = new BigDecimal(0);
    private BigDecimal    matrixStepDv1                                      = new BigDecimal(0);
    private BigDecimal    matrixStepDv2                                      = new BigDecimal(0);
    private BigDecimal    matrixStepPv1                                      = new BigDecimal(0);
    private BigDecimal    matrixStepPv2                                      = new BigDecimal(0);
    private BigDecimal    matrixStepMp                                       = new BigDecimal(0);
    private BigDecimal    matrixTotal                                        = new BigDecimal(0);
    private String        vcProductGroup                                    ;
    private String        vcProductGrade                                    ;
    private String        vcTargetGrade                                     ;
    private String        vcPlatform                                        ;
    private String        vcDevTypeCode                                     ;
    private String        vcRegionCode                                      ;
    private String        vcCountrySpec                                     ;
    private String        vcCommunicationSpec                               ;
    private String        vcSampleGrade                                     ;
    private String        vcOtherOption                                     ;
    private String        vcDevSeparate                                     ;
    private BigDecimal    matrixStepPd                                       = new BigDecimal(0);
    private Float         vcStdRateCv1                                      ;
    private Float         vcStdRateCv2                                      ;
    private Float         vcStdRateDv1                                      ;
    private Float         vcStdRateDv2                                      ;
    private Float         vcStdRatePd                                       ;
    private Float         vcStdRatePv1                                      ;
    private Float         vcStdRatePv2                                      ;
    private Float         vcStdRateMp                                       ;


    public void    setMatrixCode(String matrixCode){
        this.matrixCode = matrixCode;
    }
    public void    setMatrixType(String matrixType){
        this.matrixType = matrixType;
    }
    public void    setMatrixAttribute1(String matrixAttribute1){
        this.matrixAttribute1 = matrixAttribute1;
    }
    public void    setMatrixAttribute2(String matrixAttribute2){
        this.matrixAttribute2 = matrixAttribute2;
    }
    public void    setMatrixStepCv1(BigDecimal matrixStepCv1){
        this.matrixStepCv1 = matrixStepCv1;
    }
    public void    setMatrixStepCv2(BigDecimal matrixStepCv2){
        this.matrixStepCv2 = matrixStepCv2;
    }
    public void    setMatrixStepDv1(BigDecimal matrixStepDv1){
        this.matrixStepDv1 = matrixStepDv1;
    }
    public void    setMatrixStepDv2(BigDecimal matrixStepDv2){
        this.matrixStepDv2 = matrixStepDv2;
    }
    public void    setMatrixStepPv1(BigDecimal matrixStepPv1){
        this.matrixStepPv1 = matrixStepPv1;
    }
    public void    setMatrixStepPv2(BigDecimal matrixStepPv2){
        this.matrixStepPv2 = matrixStepPv2;
    }
    public void    setMatrixStepMp(BigDecimal matrixStepMp){
        this.matrixStepMp = matrixStepMp;
    }
    public void    setMatrixTotal(BigDecimal matrixTotal){
        this.matrixTotal = matrixTotal;
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
    public void    setVcOtherOption(String vcOtherOption){
        this.vcOtherOption = vcOtherOption;
    }
    public void    setVcDevSeparate(String vcDevSeparate){
        this.vcDevSeparate = vcDevSeparate;
    }
    public void    setMatrixStepPd(BigDecimal matrixStepPd){
        this.matrixStepPd = matrixStepPd;
    }
    public void    setVcStdRateCv1(Float vcStdRateCv1){
        this.vcStdRateCv1 = vcStdRateCv1;
    }
    public void    setVcStdRateCv2(Float vcStdRateCv2){
        this.vcStdRateCv2 = vcStdRateCv2;
    }
    public void    setVcStdRateDv1(Float vcStdRateDv1){
        this.vcStdRateDv1 = vcStdRateDv1;
    }
    public void    setVcStdRateDv2(Float vcStdRateDv2){
        this.vcStdRateDv2 = vcStdRateDv2;
    }
    public void    setVcStdRatePd(Float vcStdRatePd){
        this.vcStdRatePd = vcStdRatePd;
    }
    public void    setVcStdRatePv1(Float vcStdRatePv1){
        this.vcStdRatePv1 = vcStdRatePv1;
    }
    public void    setVcStdRatePv2(Float vcStdRatePv2){
        this.vcStdRatePv2 = vcStdRatePv2;
    }
    public void    setVcStdRateMp(Float vcStdRateMp){
        this.vcStdRateMp = vcStdRateMp;
    }
    public String getMatrixCode(){
        return matrixCode;
    }
    public String getMatrixType(){
        return matrixType;
    }
    public String getMatrixAttribute1(){
        return matrixAttribute1;
    }
    public String getMatrixAttribute2(){
        return matrixAttribute2;
    }
    public BigDecimal getMatrixStepCv1(){
        return matrixStepCv1;
    }
    public BigDecimal getMatrixStepCv2(){
        return matrixStepCv2;
    }
    public BigDecimal getMatrixStepDv1(){
        return matrixStepDv1;
    }
    public BigDecimal getMatrixStepDv2(){
        return matrixStepDv2;
    }
    public BigDecimal getMatrixStepPv1(){
        return matrixStepPv1;
    }
    public BigDecimal getMatrixStepPv2(){
        return matrixStepPv2;
    }
    public BigDecimal getMatrixStepMp(){
        return matrixStepMp;
    }
    public BigDecimal getMatrixTotal(){
        return matrixTotal;
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
    public String getVcOtherOption(){
        return vcOtherOption;
    }
    public String getVcDevSeparate(){
        return vcDevSeparate;
    }
    public BigDecimal getMatrixStepPd(){
        return matrixStepPd;
    }
    public Float getVcStdRateCv1(){
        return vcStdRateCv1;
    }
    public Float getVcStdRateCv2(){
        return vcStdRateCv2;
    }
    public Float getVcStdRateDv1(){
        return vcStdRateDv1;
    }
    public Float getVcStdRateDv2(){
        return vcStdRateDv2;
    }
    public Float getVcStdRatePd(){
        return vcStdRatePd;
    }
    public Float getVcStdRatePv1(){
        return vcStdRatePv1;
    }
    public Float getVcStdRatePv2(){
        return vcStdRatePv2;
    }
    public Float getVcStdRateMp(){
        return vcStdRateMp;
    }
}

