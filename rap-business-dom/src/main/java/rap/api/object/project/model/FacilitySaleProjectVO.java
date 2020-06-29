/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : FacilitySaleProjectVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import rap.api.object.project.model.FacilityProjectsVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class FacilitySaleProjectVO extends FacilityProjectsVO {
    private String        companySalesType                                  ;
    private String        countrySalesType                                  ;
    private String        b2bBillToName                                     ;
    private String        b2bCurrency                                       ;
    private String        b2bProjectCode                                    ;
    private String        modelName                                         ;
    private String        isProjectChange                                   ;


    public void    setCompanySalesType(String companySalesType){
        this.companySalesType = companySalesType;
    }
    public void    setCountrySalesType(String countrySalesType){
        this.countrySalesType = countrySalesType;
    }
    public void    setB2bBillToName(String b2bBillToName){
        this.b2bBillToName = b2bBillToName;
    }
    public void    setB2bCurrency(String b2bCurrency){
        this.b2bCurrency = b2bCurrency;
    }
    public void    setB2bProjectCode(String b2bProjectCode){
        this.b2bProjectCode = b2bProjectCode;
    }
    public void    setModelName(String modelName){
        this.modelName = modelName;
    }
    public void    setIsProjectChange(String isProjectChange){
        this.isProjectChange = isProjectChange;
    }
    public String getCompanySalesType(){
        return companySalesType;
    }
    public String getCountrySalesType(){
        return countrySalesType;
    }
    public String getB2bBillToName(){
        return b2bBillToName;
    }
    public String getB2bCurrency(){
        return b2bCurrency;
    }
    public String getB2bProjectCode(){
        return b2bProjectCode;
    }
    public String getModelName(){
        return modelName;
    }
    public String getIsProjectChange(){
        return isProjectChange;
    }
}

