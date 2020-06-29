/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : YyyySupplierSvcCostVO.java
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
public class YyyySupplierSvcCostVO extends BusinessObjectMasterVO {
    private String        yyyy                                              ;
    private String        currency                                          ;
    private String        supplierName                                      ;
    private String        domesticForeign                                   ;
    private String        residentProvisional                               ;
    private String        techGrade                                         ;
    private Integer       cost                                              ;


    public void    setYyyy(String yyyy){
        this.yyyy = yyyy;
    }
    public void    setCurrency(String currency){
        this.currency = currency;
    }
    public void    setSupplierName(String supplierName){
        this.supplierName = supplierName;
    }
    public void    setDomesticForeign(String domesticForeign){
        this.domesticForeign = domesticForeign;
    }
    public void    setResidentProvisional(String residentProvisional){
        this.residentProvisional = residentProvisional;
    }
    public void    setTechGrade(String techGrade){
        this.techGrade = techGrade;
    }
    public void    setCost(Integer cost){
        this.cost = cost;
    }
    public String getYyyy(){
        return yyyy;
    }
    public String getCurrency(){
        return currency;
    }
    public String getSupplierName(){
        return supplierName;
    }
    public String getDomesticForeign(){
        return domesticForeign;
    }
    public String getResidentProvisional(){
        return residentProvisional;
    }
    public String getTechGrade(){
        return techGrade;
    }
    public Integer getCost(){
        return cost;
    }
}

