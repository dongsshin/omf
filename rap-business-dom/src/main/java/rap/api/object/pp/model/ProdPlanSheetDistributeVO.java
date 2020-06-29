/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProdPlanSheetDistributeVO.java
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
public class ProdPlanSheetDistributeVO extends BusinessObjectMasterVO {
    private String        sheetDistId                                       ;
    private String        module                                            ;
    private String        sheetId                                           ;
    private String        toRegionCode                                      ;
    private String        toCountryCode                                     ;
    private Integer       seq                                               ;
    private Date          confirmDate                                       ;
    private String        confirmUser                                       ;
    private String        createdSheetId                                    ;
    private String        deliveryYn                                        ;


    public void    setSheetDistId(String sheetDistId){
        this.sheetDistId = sheetDistId;
    }
    public void    setModule(String module){
        this.module = module;
    }
    public void    setSheetId(String sheetId){
        this.sheetId = sheetId;
    }
    public void    setToRegionCode(String toRegionCode){
        this.toRegionCode = toRegionCode;
    }
    public void    setToCountryCode(String toCountryCode){
        this.toCountryCode = toCountryCode;
    }
    public void    setSeq(Integer seq){
        this.seq = seq;
    }
    public void    setConfirmDate(Date confirmDate){
        this.confirmDate = confirmDate;
    }
    public void    setConfirmDate(String    confirmDate){
        this.confirmDate = this.omcConvertStr2Date(confirmDate);
    }
    public void    setConfirmUser(String confirmUser){
        this.confirmUser = confirmUser;
    }
    public void    setCreatedSheetId(String createdSheetId){
        this.createdSheetId = createdSheetId;
    }
    public void    setDeliveryYn(String deliveryYn){
        this.deliveryYn = deliveryYn;
    }
    public String getSheetDistId(){
        return sheetDistId;
    }
    public String getModule(){
        return module;
    }
    public String getSheetId(){
        return sheetId;
    }
    public String getToRegionCode(){
        return toRegionCode;
    }
    public String getToCountryCode(){
        return toCountryCode;
    }
    public Integer getSeq(){
        return seq;
    }
    public Date getConfirmDate(){
        return confirmDate;
    }
    public String getConfirmUser(){
        return confirmUser;
    }
    public String getCreatedSheetId(){
        return createdSheetId;
    }
    public String getDeliveryYn(){
        return deliveryYn;
    }
}

