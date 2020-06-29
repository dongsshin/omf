/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProdPlanProductTypeVO.java
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
public class ProdPlanProductTypeVO extends BusinessObjectMasterVO {
    private String        divisionCode                                      ;
    private String        productType                                       ;
    private String        useYn                                             ;
    private String        module                                            ;
    private Integer       orderNo                                           ;


    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setProductType(String productType){
        this.productType = productType;
    }
    public void    setUseYn(String useYn){
        this.useYn = useYn;
    }
    public void    setModule(String module){
        this.module = module;
    }
    public void    setOrderNo(Integer orderNo){
        this.orderNo = orderNo;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getProductType(){
        return productType;
    }
    public String getUseYn(){
        return useYn;
    }
    public String getModule(){
        return module;
    }
    public Integer getOrderNo(){
        return orderNo;
    }
}

