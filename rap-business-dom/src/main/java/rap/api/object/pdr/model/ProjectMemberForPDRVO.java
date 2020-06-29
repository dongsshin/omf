/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectMemberForPDRVO.java
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
public class ProjectMemberForPDRVO extends BusinessObjectMasterVO {
    private String        divisionCode                                      ;
    private String        cpUnitCode                                        ;
    private String        pmEmpNo                                           ;
    private String        hwEmpNo                                           ;
    private String        swEmpNo                                           ;
    private String        mechEmpNo                                         ;
    private String        manualEmpNo                                       ;
    private String        packageEmpNo                                      ;
    private String        bomEmpNo                                          ;
    private String        useFlag                                           ;
    private String        attribute1                                        ;
    private String        attribute2                                        ;
    private String        attribute3                                        ;
    private String        attribute4                                        ;
    private String        attribute5                                        ;


    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setCpUnitCode(String cpUnitCode){
        this.cpUnitCode = cpUnitCode;
    }
    public void    setPmEmpNo(String pmEmpNo){
        this.pmEmpNo = pmEmpNo;
    }
    public void    setHwEmpNo(String hwEmpNo){
        this.hwEmpNo = hwEmpNo;
    }
    public void    setSwEmpNo(String swEmpNo){
        this.swEmpNo = swEmpNo;
    }
    public void    setMechEmpNo(String mechEmpNo){
        this.mechEmpNo = mechEmpNo;
    }
    public void    setManualEmpNo(String manualEmpNo){
        this.manualEmpNo = manualEmpNo;
    }
    public void    setPackageEmpNo(String packageEmpNo){
        this.packageEmpNo = packageEmpNo;
    }
    public void    setBomEmpNo(String bomEmpNo){
        this.bomEmpNo = bomEmpNo;
    }
    public void    setUseFlag(String useFlag){
        this.useFlag = useFlag;
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
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getCpUnitCode(){
        return cpUnitCode;
    }
    public String getPmEmpNo(){
        return pmEmpNo;
    }
    public String getHwEmpNo(){
        return hwEmpNo;
    }
    public String getSwEmpNo(){
        return swEmpNo;
    }
    public String getMechEmpNo(){
        return mechEmpNo;
    }
    public String getManualEmpNo(){
        return manualEmpNo;
    }
    public String getPackageEmpNo(){
        return packageEmpNo;
    }
    public String getBomEmpNo(){
        return bomEmpNo;
    }
    public String getUseFlag(){
        return useFlag;
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
}

