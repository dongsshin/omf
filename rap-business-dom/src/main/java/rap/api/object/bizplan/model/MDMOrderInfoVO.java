/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : MDMOrderInfoVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.bizplan.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class MDMOrderInfoVO extends BusinessObjectMasterVO {
    private String        partNo                                            ;
    private Integer       itemSeq                                           ;
    private String        orderReportNo                                     ;
    private String        orderOrgCode                                      ;
    private String        modelName                                         ;
    private String        investTypeCode                                    ;
    private String        mdmProjectCode                                    ;
    private String        orderReportDate                                   ;
    private String        prodTypeCode                                      ;
    private BigDecimal    orderAmount                                        = new BigDecimal(0);


    public void    setPartNo(String partNo){
        this.partNo = partNo;
    }
    public void    setItemSeq(Integer itemSeq){
        this.itemSeq = itemSeq;
    }
    public void    setOrderReportNo(String orderReportNo){
        this.orderReportNo = orderReportNo;
    }
    public void    setOrderOrgCode(String orderOrgCode){
        this.orderOrgCode = orderOrgCode;
    }
    public void    setModelName(String modelName){
        this.modelName = modelName;
    }
    public void    setInvestTypeCode(String investTypeCode){
        this.investTypeCode = investTypeCode;
    }
    public void    setMdmProjectCode(String mdmProjectCode){
        this.mdmProjectCode = mdmProjectCode;
    }
    public void    setOrderReportDate(String orderReportDate){
        this.orderReportDate = orderReportDate;
    }
    public void    setProdTypeCode(String prodTypeCode){
        this.prodTypeCode = prodTypeCode;
    }
    public void    setOrderAmount(BigDecimal orderAmount){
        this.orderAmount = orderAmount;
    }
    public String getPartNo(){
        return partNo;
    }
    public Integer getItemSeq(){
        return itemSeq;
    }
    public String getOrderReportNo(){
        return orderReportNo;
    }
    public String getOrderOrgCode(){
        return orderOrgCode;
    }
    public String getModelName(){
        return modelName;
    }
    public String getInvestTypeCode(){
        return investTypeCode;
    }
    public String getMdmProjectCode(){
        return mdmProjectCode;
    }
    public String getOrderReportDate(){
        return orderReportDate;
    }
    public String getProdTypeCode(){
        return prodTypeCode;
    }
    public BigDecimal getOrderAmount(){
        return orderAmount;
    }
}

