/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ConsultDesignRefPriceVO.java
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
public class ConsultDesignRefPriceVO extends BusinessObjectMasterVO {
    private String        priceType                                         ;
    private String        productGroup                                      ;
    private String        priceCategory1                                    ;
    private String        priceCategory2                                    ;
    private String        priceCategory3                                    ;
    private String        priceCategory4                                    ;
    private String        priceCategory5                                    ;
    private BigDecimal    referenceCost                                      = new BigDecimal(0);
    private String        referenceQty                                      ;
    private String        referenceScope                                    ;
    private Integer       referencePeriod                                   ;


    public void    setPriceType(String priceType){
        this.priceType = priceType;
    }
    public void    setProductGroup(String productGroup){
        this.productGroup = productGroup;
    }
    public void    setPriceCategory1(String priceCategory1){
        this.priceCategory1 = priceCategory1;
    }
    public void    setPriceCategory2(String priceCategory2){
        this.priceCategory2 = priceCategory2;
    }
    public void    setPriceCategory3(String priceCategory3){
        this.priceCategory3 = priceCategory3;
    }
    public void    setPriceCategory4(String priceCategory4){
        this.priceCategory4 = priceCategory4;
    }
    public void    setPriceCategory5(String priceCategory5){
        this.priceCategory5 = priceCategory5;
    }
    public void    setReferenceCost(BigDecimal referenceCost){
        this.referenceCost = referenceCost;
    }
    public void    setReferenceQty(String referenceQty){
        this.referenceQty = referenceQty;
    }
    public void    setReferenceScope(String referenceScope){
        this.referenceScope = referenceScope;
    }
    public void    setReferencePeriod(Integer referencePeriod){
        this.referencePeriod = referencePeriod;
    }
    public String getPriceType(){
        return priceType;
    }
    public String getProductGroup(){
        return productGroup;
    }
    public String getPriceCategory1(){
        return priceCategory1;
    }
    public String getPriceCategory2(){
        return priceCategory2;
    }
    public String getPriceCategory3(){
        return priceCategory3;
    }
    public String getPriceCategory4(){
        return priceCategory4;
    }
    public String getPriceCategory5(){
        return priceCategory5;
    }
    public BigDecimal getReferenceCost(){
        return referenceCost;
    }
    public String getReferenceQty(){
        return referenceQty;
    }
    public String getReferenceScope(){
        return referenceScope;
    }
    public Integer getReferencePeriod(){
        return referencePeriod;
    }
}

