/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProductVO.java
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
public class ProductVO extends BusinessObjectMasterVO {
    private String        productCode                                       ;
    private String        upperProduct                                      ;
    private String        divisionCode                                      ;
    private Integer       productLevel                                      ;
    private String        bigo                                              ;
    private String        depreciation                                      ;
    private Integer       sorting                                           ;


    public void    setProductCode(String productCode){
        this.productCode = productCode;
    }
    public void    setUpperProduct(String upperProduct){
        this.upperProduct = upperProduct;
    }
    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setProductLevel(Integer productLevel){
        this.productLevel = productLevel;
    }
    public void    setBigo(String bigo){
        this.bigo = bigo;
    }
    public void    setDepreciation(String depreciation){
        this.depreciation = depreciation;
    }
    public void    setSorting(Integer sorting){
        this.sorting = sorting;
    }
    public String getProductCode(){
        return productCode;
    }
    public String getUpperProduct(){
        return upperProduct;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public Integer getProductLevel(){
        return productLevel;
    }
    public String getBigo(){
        return bigo;
    }
    public String getDepreciation(){
        return depreciation;
    }
    public Integer getSorting(){
        return sorting;
    }
}

