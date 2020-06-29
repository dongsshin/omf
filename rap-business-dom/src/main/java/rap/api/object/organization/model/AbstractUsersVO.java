/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : AbstractUsersVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.organization.model;


import java.util.Date;

import com.rap.omc.api.object.model.BusinessObjectMasterVO;


@SuppressWarnings("serial")
public class AbstractUsersVO extends BusinessObjectMasterVO {
    private String        nameEng                                           ;
    private Date          hireDate                                          ;
    private Date          retiredDate                                       ;
    private Date          restmentDate                                      ;
    private Date          reinstatementDate                                 ;
    private String        jobName                                           ;
    private String        titleCode                                         ;
    private String        titleName                                         ;
    private String        gradeCode                                         ;
    private String        gradeName                                         ;
    private Integer       gradeSeniority                                    ;
    private String        positionName                                      ;
    private String        hrDepartmentCode                                  ;
    private String        hrDepartmentEng                                   ;
    private String        hrDepartmentKor                                   ;
    private String        fseYn                                             ;
    private String        leaderEmployeeNo                                  ;
    private String        leaderYn                                           = "N";
    private String        inOffiStatFlag                                    ;
    private String        searchingForUser                                  ;
    private String        searchingForDepartment                            ;
    private String        displayTitleKr                                    ;
    private String        displayTitleEn                                    ;


    public void    setNameEng(String nameEng){
        this.nameEng = nameEng;
    }
    public void    setHireDate(Date hireDate){
        this.hireDate = hireDate;
    }
    public void    setHireDate(String    hireDate){
        this.hireDate = this.omcConvertStr2Date(hireDate);
    }
    public void    setRetiredDate(Date retiredDate){
        this.retiredDate = retiredDate;
    }
    public void    setRetiredDate(String    retiredDate){
        this.retiredDate = this.omcConvertStr2Date(retiredDate);
    }
    public void    setRestmentDate(Date restmentDate){
        this.restmentDate = restmentDate;
    }
    public void    setRestmentDate(String    restmentDate){
        this.restmentDate = this.omcConvertStr2Date(restmentDate);
    }
    public void    setReinstatementDate(Date reinstatementDate){
        this.reinstatementDate = reinstatementDate;
    }
    public void    setReinstatementDate(String    reinstatementDate){
        this.reinstatementDate = this.omcConvertStr2Date(reinstatementDate);
    }
    public void    setJobName(String jobName){
        this.jobName = jobName;
    }
    public void    setTitleCode(String titleCode){
        this.titleCode = titleCode;
    }
    public void    setTitleName(String titleName){
        this.titleName = titleName;
    }
    public void    setGradeCode(String gradeCode){
        this.gradeCode = gradeCode;
    }
    public void    setGradeName(String gradeName){
        this.gradeName = gradeName;
    }
    public void    setGradeSeniority(Integer gradeSeniority){
        this.gradeSeniority = gradeSeniority;
    }
    public void    setPositionName(String positionName){
        this.positionName = positionName;
    }
    public void    setHrDepartmentCode(String hrDepartmentCode){
        this.hrDepartmentCode = hrDepartmentCode;
    }
    public void    setHrDepartmentEng(String hrDepartmentEng){
        this.hrDepartmentEng = hrDepartmentEng;
    }
    public void    setHrDepartmentKor(String hrDepartmentKor){
        this.hrDepartmentKor = hrDepartmentKor;
    }
    public void    setFseYn(String fseYn){
        this.fseYn = fseYn;
    }
    public void    setLeaderEmployeeNo(String leaderEmployeeNo){
        this.leaderEmployeeNo = leaderEmployeeNo;
    }
    public void    setLeaderYn(String leaderYn){
        this.leaderYn = leaderYn;
    }
    public void    setInOffiStatFlag(String inOffiStatFlag){
        this.inOffiStatFlag = inOffiStatFlag;
    }
    public void    setSearchingForUser(String searchingForUser){
        this.searchingForUser = searchingForUser;
    }
    public void    setSearchingForDepartment(String searchingForDepartment){
        this.searchingForDepartment = searchingForDepartment;
    }
    public void    setDisplayTitleKr(String displayTitleKr){
        this.displayTitleKr = displayTitleKr;
    }
    public void    setDisplayTitleEn(String displayTitleEn){
        this.displayTitleEn = displayTitleEn;
    }
    public String getNameEng(){
        return nameEng;
    }
    public Date getHireDate(){
        return hireDate;
    }
    public Date getRetiredDate(){
        return retiredDate;
    }
    public Date getRestmentDate(){
        return restmentDate;
    }
    public Date getReinstatementDate(){
        return reinstatementDate;
    }
    public String getJobName(){
        return jobName;
    }
    public String getTitleCode(){
        return titleCode;
    }
    public String getTitleName(){
        return titleName;
    }
    public String getGradeCode(){
        return gradeCode;
    }
    public String getGradeName(){
        return gradeName;
    }
    public Integer getGradeSeniority(){
        return gradeSeniority;
    }
    public String getPositionName(){
        return positionName;
    }
    public String getHrDepartmentCode(){
        return hrDepartmentCode;
    }
    public String getHrDepartmentEng(){
        return hrDepartmentEng;
    }
    public String getHrDepartmentKor(){
        return hrDepartmentKor;
    }
    public String getFseYn(){
        return fseYn;
    }
    public String getLeaderEmployeeNo(){
        return leaderEmployeeNo;
    }
    public String getLeaderYn(){
        return leaderYn;
    }
    public String getInOffiStatFlag(){
        return inOffiStatFlag;
    }
    public String getSearchingForUser(){
        return searchingForUser;
    }
    public String getSearchingForDepartment(){
        return searchingForDepartment;
    }
    public String getDisplayTitleKr(){
        return displayTitleKr;
    }
    public String getDisplayTitleEn(){
        return displayTitleEn;
    }
}

