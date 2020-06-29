/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectPerformanceVO.java
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
public class ProjectPerformanceVO extends BusinessObjectMasterVO {
    private String        divisionCode                                      ;
    private String        yyyy                                              ;
    private String        performanceCategory                               ;
    private Integer       hitCount                                           = 0;
    private String        performanceType                                   ;
    private String        orgCategory1                                      ;
    private String        orgCategory2                                      ;
    private String        prodCategory1                                     ;
    private String        prodCategory2                                     ;
    private String        techCategory1                                     ;
    private String        techCategory2                                     ;


    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setYyyy(String yyyy){
        this.yyyy = yyyy;
    }
    public void    setPerformanceCategory(String performanceCategory){
        this.performanceCategory = performanceCategory;
    }
    public void    setHitCount(Integer hitCount){
        this.hitCount = hitCount;
    }
    public void    setPerformanceType(String performanceType){
        this.performanceType = performanceType;
    }
    public void    setOrgCategory1(String orgCategory1){
        this.orgCategory1 = orgCategory1;
    }
    public void    setOrgCategory2(String orgCategory2){
        this.orgCategory2 = orgCategory2;
    }
    public void    setProdCategory1(String prodCategory1){
        this.prodCategory1 = prodCategory1;
    }
    public void    setProdCategory2(String prodCategory2){
        this.prodCategory2 = prodCategory2;
    }
    public void    setTechCategory1(String techCategory1){
        this.techCategory1 = techCategory1;
    }
    public void    setTechCategory2(String techCategory2){
        this.techCategory2 = techCategory2;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getYyyy(){
        return yyyy;
    }
    public String getPerformanceCategory(){
        return performanceCategory;
    }
    public Integer getHitCount(){
        return hitCount;
    }
    public String getPerformanceType(){
        return performanceType;
    }
    public String getOrgCategory1(){
        return orgCategory1;
    }
    public String getOrgCategory2(){
        return orgCategory2;
    }
    public String getProdCategory1(){
        return prodCategory1;
    }
    public String getProdCategory2(){
        return prodCategory2;
    }
    public String getTechCategory1(){
        return techCategory1;
    }
    public String getTechCategory2(){
        return techCategory2;
    }
}

