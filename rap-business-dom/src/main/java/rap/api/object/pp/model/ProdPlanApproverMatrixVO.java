/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProdPlanApproverMatrixVO.java
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
public class ProdPlanApproverMatrixVO extends BusinessObjectMasterVO {
    private String        module                                            ;
    private String        divisionCode                                      ;
    private String        productType                                       ;
    private String        regionCode                                        ;
    private String        countryCode                                       ;
    private Integer       approvalSeq                                       ;
    private String        approver                                          ;


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
    public void    setApprovalSeq(Integer approvalSeq){
        this.approvalSeq = approvalSeq;
    }
    public void    setApprover(String approver){
        this.approver = approver;
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
    public Integer getApprovalSeq(){
        return approvalSeq;
    }
    public String getApprover(){
        return approver;
    }
}

