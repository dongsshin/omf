/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectImageVO.java
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
public class ProjectImageVO extends BusinessObjectMasterVO {
    private String        projectCode                                       ;
    private String        commCode                                          ;
    private Integer       uidx                                              ;
    private Integer       idx                                               ;
    private String        iconPath                                          ;
    private String        imagePath                                         ;
    private String        regYear                                           ;
    private String        regMonth                                          ;
    private String        regDay                                            ;
    private String        regEmpNo                                          ;
    private String        modelName                                         ;
    private String        halfPath                                          ;


    public void    setProjectCode(String projectCode){
        this.projectCode = projectCode;
    }
    public void    setCommCode(String commCode){
        this.commCode = commCode;
    }
    public void    setUidx(Integer uidx){
        this.uidx = uidx;
    }
    public void    setIdx(Integer idx){
        this.idx = idx;
    }
    public void    setIconPath(String iconPath){
        this.iconPath = iconPath;
    }
    public void    setImagePath(String imagePath){
        this.imagePath = imagePath;
    }
    public void    setRegYear(String regYear){
        this.regYear = regYear;
    }
    public void    setRegMonth(String regMonth){
        this.regMonth = regMonth;
    }
    public void    setRegDay(String regDay){
        this.regDay = regDay;
    }
    public void    setRegEmpNo(String regEmpNo){
        this.regEmpNo = regEmpNo;
    }
    public void    setModelName(String modelName){
        this.modelName = modelName;
    }
    public void    setHalfPath(String halfPath){
        this.halfPath = halfPath;
    }
    public String getProjectCode(){
        return projectCode;
    }
    public String getCommCode(){
        return commCode;
    }
    public Integer getUidx(){
        return uidx;
    }
    public Integer getIdx(){
        return idx;
    }
    public String getIconPath(){
        return iconPath;
    }
    public String getImagePath(){
        return imagePath;
    }
    public String getRegYear(){
        return regYear;
    }
    public String getRegMonth(){
        return regMonth;
    }
    public String getRegDay(){
        return regDay;
    }
    public String getRegEmpNo(){
        return regEmpNo;
    }
    public String getModelName(){
        return modelName;
    }
    public String getHalfPath(){
        return halfPath;
    }
}

