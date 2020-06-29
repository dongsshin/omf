/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : OSCSupplierCategoryVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.consult.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class OSCSupplierCategoryVO extends BusinessObjectMasterVO {
    private String        supplierCode                                      ;
    private String        supplierName                                      ;
    private Integer       sortOrder                                         ;
    private String        useYn                                             ;
    private String        categoryCode                                      ;


    public void    setSupplierCode(String supplierCode){
        this.supplierCode = supplierCode;
    }
    public void    setSupplierName(String supplierName){
        this.supplierName = supplierName;
    }
    public void    setSortOrder(Integer sortOrder){
        this.sortOrder = sortOrder;
    }
    public void    setUseYn(String useYn){
        this.useYn = useYn;
    }
    public void    setCategoryCode(String categoryCode){
        this.categoryCode = categoryCode;
    }
    public String getSupplierCode(){
        return supplierCode;
    }
    public String getSupplierName(){
        return supplierName;
    }
    public Integer getSortOrder(){
        return sortOrder;
    }
    public String getUseYn(){
        return useYn;
    }
    public String getCategoryCode(){
        return categoryCode;
    }
}

