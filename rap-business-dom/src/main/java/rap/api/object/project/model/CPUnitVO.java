/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : CPUnitVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class CPUnitVO extends BusinessObjectMasterVO {
    private String        platformCd                                        ;
    private String        divisionCode                                      ;
    private Integer       platformLevel                                     ;
    private String        platformEngName                                   ;
    private String        platformId                                        ;
    private String        platformSpec                                      ;
    private String        platformSpec2                                     ;
    private String        platformSpec3                                     ;
    private String        platformSpec4                                     ;
    private String        platformSpec5                                     ;
    private String        platformSpec6                                     ;
    private String        platformSpec7                                     ;
    private String        platformSpec8                                     ;
    private String        useFlag                                           ;
    private String        attribute01                                       ;
    private String        attribute02                                       ;
    private String        attribute03                                       ;
    private String        attribute04                                       ;
    private String        attribute05                                       ;


    public void    setPlatformCd(String platformCd){
        this.platformCd = platformCd;
    }
    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setPlatformLevel(Integer platformLevel){
        this.platformLevel = platformLevel;
    }
    public void    setPlatformEngName(String platformEngName){
        this.platformEngName = platformEngName;
    }
    public void    setPlatformId(String platformId){
        this.platformId = platformId;
    }
    public void    setPlatformSpec(String platformSpec){
        this.platformSpec = platformSpec;
    }
    public void    setPlatformSpec2(String platformSpec2){
        this.platformSpec2 = platformSpec2;
    }
    public void    setPlatformSpec3(String platformSpec3){
        this.platformSpec3 = platformSpec3;
    }
    public void    setPlatformSpec4(String platformSpec4){
        this.platformSpec4 = platformSpec4;
    }
    public void    setPlatformSpec5(String platformSpec5){
        this.platformSpec5 = platformSpec5;
    }
    public void    setPlatformSpec6(String platformSpec6){
        this.platformSpec6 = platformSpec6;
    }
    public void    setPlatformSpec7(String platformSpec7){
        this.platformSpec7 = platformSpec7;
    }
    public void    setPlatformSpec8(String platformSpec8){
        this.platformSpec8 = platformSpec8;
    }
    public void    setUseFlag(String useFlag){
        this.useFlag = useFlag;
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
    public String getPlatformCd(){
        return platformCd;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public Integer getPlatformLevel(){
        return platformLevel;
    }
    public String getPlatformEngName(){
        return platformEngName;
    }
    public String getPlatformId(){
        return platformId;
    }
    public String getPlatformSpec(){
        return platformSpec;
    }
    public String getPlatformSpec2(){
        return platformSpec2;
    }
    public String getPlatformSpec3(){
        return platformSpec3;
    }
    public String getPlatformSpec4(){
        return platformSpec4;
    }
    public String getPlatformSpec5(){
        return platformSpec5;
    }
    public String getPlatformSpec6(){
        return platformSpec6;
    }
    public String getPlatformSpec7(){
        return platformSpec7;
    }
    public String getPlatformSpec8(){
        return platformSpec8;
    }
    public String getUseFlag(){
        return useFlag;
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
}

