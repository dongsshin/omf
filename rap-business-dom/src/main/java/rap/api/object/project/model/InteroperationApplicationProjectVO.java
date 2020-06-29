/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : InteroperationApplicationProjectVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import rap.api.object.project.model.ProductProjectsVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class InteroperationApplicationProjectVO extends ProductProjectsVO {
    private String        appType                                           ;
    private Integer       mrStep                                             = 1;
    private String        appProductCode                                    ;
    private String        appProductCodeEtc                                 ;
    private String        appSpec                                           ;
    private String        b2bCustomer                                       ;
    private String        executedDevice                                    ;
    private String        deliverySite                                      ;
    private String        deliverySiteEtc                                   ;
    private String        osVersion                                         ;


    public void    setAppType(String appType){
        this.appType = appType;
    }
    public void    setMrStep(Integer mrStep){
        this.mrStep = mrStep;
    }
    public void    setAppProductCode(String appProductCode){
        this.appProductCode = appProductCode;
    }
    public void    setAppProductCodeEtc(String appProductCodeEtc){
        this.appProductCodeEtc = appProductCodeEtc;
    }
    public void    setAppSpec(String appSpec){
        this.appSpec = appSpec;
    }
    public void    setB2bCustomer(String b2bCustomer){
        this.b2bCustomer = b2bCustomer;
    }
    public void    setExecutedDevice(String executedDevice){
        this.executedDevice = executedDevice;
    }
    public void    setDeliverySite(String deliverySite){
        this.deliverySite = deliverySite;
    }
    public void    setDeliverySiteEtc(String deliverySiteEtc){
        this.deliverySiteEtc = deliverySiteEtc;
    }
    public void    setOsVersion(String osVersion){
        this.osVersion = osVersion;
    }
    public String getAppType(){
        return appType;
    }
    public Integer getMrStep(){
        return mrStep;
    }
    public String getAppProductCode(){
        return appProductCode;
    }
    public String getAppProductCodeEtc(){
        return appProductCodeEtc;
    }
    public String getAppSpec(){
        return appSpec;
    }
    public String getB2bCustomer(){
        return b2bCustomer;
    }
    public String getExecutedDevice(){
        return executedDevice;
    }
    public String getDeliverySite(){
        return deliverySite;
    }
    public String getDeliverySiteEtc(){
        return deliverySiteEtc;
    }
    public String getOsVersion(){
        return osVersion;
    }
}

