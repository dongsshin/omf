/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : MDTopDeptVO.java
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
public class MDTopDeptVO extends BusinessObjectMasterVO {
    private String        deptCode                                          ;
    private String        deptName                                          ;
    private String        deptShortName                                     ;
    private String        companyCode                                       ;
    private String        divisionCode                                      ;
    private Float         timeZoneValue                                     ;
    private String        timeZoneGmt                                       ;
    private String        mdTopYn                                           ;
    private String        description                                       ;
    private Integer       sorting                                           ;
    private String        upperDept                                         ;
    private String        useYn                                             ;
    private String        reportDeptYn                                      ;
    private String        attribute1                                        ;
    private String        attribute2                                        ;


    public void    setDeptCode(String deptCode){
        this.deptCode = deptCode;
    }
    public void    setDeptName(String deptName){
        this.deptName = deptName;
    }
    public void    setDeptShortName(String deptShortName){
        this.deptShortName = deptShortName;
    }
    public void    setCompanyCode(String companyCode){
        this.companyCode = companyCode;
    }
    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setTimeZoneValue(Float timeZoneValue){
        this.timeZoneValue = timeZoneValue;
    }
    public void    setTimeZoneGmt(String timeZoneGmt){
        this.timeZoneGmt = timeZoneGmt;
    }
    public void    setMdTopYn(String mdTopYn){
        this.mdTopYn = mdTopYn;
    }
    public void    setDescription(String description){
        this.description = description;
    }
    public void    setSorting(Integer sorting){
        this.sorting = sorting;
    }
    public void    setUpperDept(String upperDept){
        this.upperDept = upperDept;
    }
    public void    setUseYn(String useYn){
        this.useYn = useYn;
    }
    public void    setReportDeptYn(String reportDeptYn){
        this.reportDeptYn = reportDeptYn;
    }
    public void    setAttribute1(String attribute1){
        this.attribute1 = attribute1;
    }
    public void    setAttribute2(String attribute2){
        this.attribute2 = attribute2;
    }
    public String getDeptCode(){
        return deptCode;
    }
    public String getDeptName(){
        return deptName;
    }
    public String getDeptShortName(){
        return deptShortName;
    }
    public String getCompanyCode(){
        return companyCode;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public Float getTimeZoneValue(){
        return timeZoneValue;
    }
    public String getTimeZoneGmt(){
        return timeZoneGmt;
    }
    public String getMdTopYn(){
        return mdTopYn;
    }
    public String getDescription(){
        return description;
    }
    public Integer getSorting(){
        return sorting;
    }
    public String getUpperDept(){
        return upperDept;
    }
    public String getUseYn(){
        return useYn;
    }
    public String getReportDeptYn(){
        return reportDeptYn;
    }
    public String getAttribute1(){
        return attribute1;
    }
    public String getAttribute2(){
        return attribute2;
    }
}

