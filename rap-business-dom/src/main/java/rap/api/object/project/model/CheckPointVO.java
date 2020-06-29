/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : CheckPointVO.java
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
public class CheckPointVO extends BusinessObjectMasterVO {
    private String        pointName                                         ;
    private String        pointNameEng                                      ;
    private String        descriptionsEng                                   ;
    private String        mainDepartment                                    ;
    private String        mainDepartmentEng                                 ;
    private String        mandatoryInfoByGrade                              ;
    private String        pointColor                                        ;
    private Integer       sortOrderNo                                       ;
    private String        useFlag                                           ;
    private String        estGuideEng                                       ;
    private String        estGuideKor                                       ;
    private String        allottedPointUseFlag                              ;
    private Integer       allottedPoint                                     ;


    public void    setPointName(String pointName){
        this.pointName = pointName;
    }
    public void    setPointNameEng(String pointNameEng){
        this.pointNameEng = pointNameEng;
    }
    public void    setDescriptionsEng(String descriptionsEng){
        this.descriptionsEng = descriptionsEng;
    }
    public void    setMainDepartment(String mainDepartment){
        this.mainDepartment = mainDepartment;
    }
    public void    setMainDepartmentEng(String mainDepartmentEng){
        this.mainDepartmentEng = mainDepartmentEng;
    }
    public void    setMandatoryInfoByGrade(String mandatoryInfoByGrade){
        this.mandatoryInfoByGrade = mandatoryInfoByGrade;
    }
    public void    setPointColor(String pointColor){
        this.pointColor = pointColor;
    }
    public void    setSortOrderNo(Integer sortOrderNo){
        this.sortOrderNo = sortOrderNo;
    }
    public void    setUseFlag(String useFlag){
        this.useFlag = useFlag;
    }
    public void    setEstGuideEng(String estGuideEng){
        this.estGuideEng = estGuideEng;
    }
    public void    setEstGuideKor(String estGuideKor){
        this.estGuideKor = estGuideKor;
    }
    public void    setAllottedPointUseFlag(String allottedPointUseFlag){
        this.allottedPointUseFlag = allottedPointUseFlag;
    }
    public void    setAllottedPoint(Integer allottedPoint){
        this.allottedPoint = allottedPoint;
    }
    public String getPointName(){
        return pointName;
    }
    public String getPointNameEng(){
        return pointNameEng;
    }
    public String getDescriptionsEng(){
        return descriptionsEng;
    }
    public String getMainDepartment(){
        return mainDepartment;
    }
    public String getMainDepartmentEng(){
        return mainDepartmentEng;
    }
    public String getMandatoryInfoByGrade(){
        return mandatoryInfoByGrade;
    }
    public String getPointColor(){
        return pointColor;
    }
    public Integer getSortOrderNo(){
        return sortOrderNo;
    }
    public String getUseFlag(){
        return useFlag;
    }
    public String getEstGuideEng(){
        return estGuideEng;
    }
    public String getEstGuideKor(){
        return estGuideKor;
    }
    public String getAllottedPointUseFlag(){
        return allottedPointUseFlag;
    }
    public Integer getAllottedPoint(){
        return allottedPoint;
    }
}

