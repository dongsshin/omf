/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : GscpShipmentInfoVO.java
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
public class GscpShipmentInfoVO extends BusinessObjectMasterVO {
    private String        divisionCode                                      ;
    private String        fromAffiliate                                     ;
    private String        fromSite                                          ;
    private String        toSite                                            ;
    private String        region                                            ;
    private String        modelSuffix                                       ;
    private String        salesModelSuffix                                  ;
    private String        attribute01                                       ;
    private String        attribute02                                       ;
    private String        attribute03                                       ;
    private String        attribute04                                       ;
    private String        attribute05                                       ;


    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setFromAffiliate(String fromAffiliate){
        this.fromAffiliate = fromAffiliate;
    }
    public void    setFromSite(String fromSite){
        this.fromSite = fromSite;
    }
    public void    setToSite(String toSite){
        this.toSite = toSite;
    }
    public void    setRegion(String region){
        this.region = region;
    }
    public void    setModelSuffix(String modelSuffix){
        this.modelSuffix = modelSuffix;
    }
    public void    setSalesModelSuffix(String salesModelSuffix){
        this.salesModelSuffix = salesModelSuffix;
    }
    public void    setAttribute01(String attribute01){
        this.attribute01 = attribute01;
    }
    public void    setAttribute02(String attribute02){
        this.attribute02 = attribute02;
    }
    public void    setAttribute03(String attribute03){
        this.attribute03 = attribute03;
    }
    public void    setAttribute04(String attribute04){
        this.attribute04 = attribute04;
    }
    public void    setAttribute05(String attribute05){
        this.attribute05 = attribute05;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getFromAffiliate(){
        return fromAffiliate;
    }
    public String getFromSite(){
        return fromSite;
    }
    public String getToSite(){
        return toSite;
    }
    public String getRegion(){
        return region;
    }
    public String getModelSuffix(){
        return modelSuffix;
    }
    public String getSalesModelSuffix(){
        return salesModelSuffix;
    }
    public String getAttribute01(){
        return attribute01;
    }
    public String getAttribute02(){
        return attribute02;
    }
    public String getAttribute03(){
        return attribute03;
    }
    public String getAttribute04(){
        return attribute04;
    }
    public String getAttribute05(){
        return attribute05;
    }
}

