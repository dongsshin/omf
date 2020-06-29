/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProdPlanSheetAuthVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.pp.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ProdPlanSheetAuthVO extends BusinessObjectMasterVO {
    private String        module                                            ;
    private String        divisionCode                                      ;
    private String        productType                                       ;
    private String        regionCode                                        ;
    private String        countryCode                                       ;
    private String        authUser                                          ;
    private String        editableYn                                        ;


    public void    setModule(String module){
        this.module = module;
    }
    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setProductType(String productType){
        this.productType = productType;
    }
    public void    setRegionCode(String regionCode){
        this.regionCode = regionCode;
    }
    public void    setCountryCode(String countryCode){
        this.countryCode = countryCode;
    }
    public void    setAuthUser(String authUser){
        this.authUser = authUser;
    }
    public void    setEditableYn(String editableYn){
        this.editableYn = editableYn;
    }
    public String getModule(){
        return module;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getProductType(){
        return productType;
    }
    public String getRegionCode(){
        return regionCode;
    }
    public String getCountryCode(){
        return countryCode;
    }
    public String getAuthUser(){
        return authUser;
    }
    public String getEditableYn(){
        return editableYn;
    }
}

