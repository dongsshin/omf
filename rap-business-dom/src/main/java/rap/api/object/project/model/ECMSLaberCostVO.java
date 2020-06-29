/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ECMSLaberCostVO.java
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
public class ECMSLaberCostVO extends BusinessObjectMasterVO {
    private String        job                                               ;
    private String        scenarioCode                                      ;
    private String        jobDetail                                         ;
    private String        yyyy                                              ;
    private String        laborcostMonth                                    ;
    private Float         mm                                                ;
    private Float         ecmsLaborCost                                     ;
    private String        position                                          ;
    private Float         januaryMm                                         ;
    private Float         februaryMm                                        ;
    private Float         marchMm                                           ;
    private Float         aprilMm                                           ;
    private Float         mayMm                                             ;
    private Float         juneMm                                            ;
    private Float         julyMm                                            ;
    private Float         augustMm                                          ;
    private Float         septemberMm                                       ;
    private Float         octoberMm                                         ;
    private Float         novemberMm                                        ;
    private Float         decemberMm                                        ;
    private String        departmentName                                    ;
    private String        departmentCode                                    ;


    public void    setJob(String job){
        this.job = job;
    }
    public void    setScenarioCode(String scenarioCode){
        this.scenarioCode = scenarioCode;
    }
    public void    setJobDetail(String jobDetail){
        this.jobDetail = jobDetail;
    }
    public void    setYyyy(String yyyy){
        this.yyyy = yyyy;
    }
    public void    setLaborcostMonth(String laborcostMonth){
        this.laborcostMonth = laborcostMonth;
    }
    public void    setMm(Float mm){
        this.mm = mm;
    }
    public void    setEcmsLaborCost(Float ecmsLaborCost){
        this.ecmsLaborCost = ecmsLaborCost;
    }
    public void    setPosition(String position){
        this.position = position;
    }
    public void    setJanuaryMm(Float januaryMm){
        this.januaryMm = januaryMm;
    }
    public void    setFebruaryMm(Float februaryMm){
        this.februaryMm = februaryMm;
    }
    public void    setMarchMm(Float marchMm){
        this.marchMm = marchMm;
    }
    public void    setAprilMm(Float aprilMm){
        this.aprilMm = aprilMm;
    }
    public void    setMayMm(Float mayMm){
        this.mayMm = mayMm;
    }
    public void    setJuneMm(Float juneMm){
        this.juneMm = juneMm;
    }
    public void    setJulyMm(Float julyMm){
        this.julyMm = julyMm;
    }
    public void    setAugustMm(Float augustMm){
        this.augustMm = augustMm;
    }
    public void    setSeptemberMm(Float septemberMm){
        this.septemberMm = septemberMm;
    }
    public void    setOctoberMm(Float octoberMm){
        this.octoberMm = octoberMm;
    }
    public void    setNovemberMm(Float novemberMm){
        this.novemberMm = novemberMm;
    }
    public void    setDecemberMm(Float decemberMm){
        this.decemberMm = decemberMm;
    }
    public void    setDepartmentName(String departmentName){
        this.departmentName = departmentName;
    }
    public void    setDepartmentCode(String departmentCode){
        this.departmentCode = departmentCode;
    }
    public String getJob(){
        return job;
    }
    public String getScenarioCode(){
        return scenarioCode;
    }
    public String getJobDetail(){
        return jobDetail;
    }
    public String getYyyy(){
        return yyyy;
    }
    public String getLaborcostMonth(){
        return laborcostMonth;
    }
    public Float getMm(){
        return mm;
    }
    public Float getEcmsLaborCost(){
        return ecmsLaborCost;
    }
    public String getPosition(){
        return position;
    }
    public Float getJanuaryMm(){
        return januaryMm;
    }
    public Float getFebruaryMm(){
        return februaryMm;
    }
    public Float getMarchMm(){
        return marchMm;
    }
    public Float getAprilMm(){
        return aprilMm;
    }
    public Float getMayMm(){
        return mayMm;
    }
    public Float getJuneMm(){
        return juneMm;
    }
    public Float getJulyMm(){
        return julyMm;
    }
    public Float getAugustMm(){
        return augustMm;
    }
    public Float getSeptemberMm(){
        return septemberMm;
    }
    public Float getOctoberMm(){
        return octoberMm;
    }
    public Float getNovemberMm(){
        return novemberMm;
    }
    public Float getDecemberMm(){
        return decemberMm;
    }
    public String getDepartmentName(){
        return departmentName;
    }
    public String getDepartmentCode(){
        return departmentCode;
    }
}

