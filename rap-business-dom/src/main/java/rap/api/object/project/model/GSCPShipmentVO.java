/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : GSCPShipmentVO.java
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
public class GSCPShipmentVO extends BusinessObjectMasterVO {
    private String        divisionCode                                      ;
    private String        workYyyymmdd                                      ;
    private String        modelSuffix                                       ;
    private String        uncntShipmentYyyymmdd                             ;
    private Integer       uncntShipmentCount                                ;
    private String        shipmentYyyymmdd                                  ;
    private Integer       shipmentCount                                     ;
    private String        shipmentType                                       = "PLAN";


    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setWorkYyyymmdd(String workYyyymmdd){
        this.workYyyymmdd = workYyyymmdd;
    }
    public void    setModelSuffix(String modelSuffix){
        this.modelSuffix = modelSuffix;
    }
    public void    setUncntShipmentYyyymmdd(String uncntShipmentYyyymmdd){
        this.uncntShipmentYyyymmdd = uncntShipmentYyyymmdd;
    }
    public void    setUncntShipmentCount(Integer uncntShipmentCount){
        this.uncntShipmentCount = uncntShipmentCount;
    }
    public void    setShipmentYyyymmdd(String shipmentYyyymmdd){
        this.shipmentYyyymmdd = shipmentYyyymmdd;
    }
    public void    setShipmentCount(Integer shipmentCount){
        this.shipmentCount = shipmentCount;
    }
    public void    setShipmentType(String shipmentType){
        this.shipmentType = shipmentType;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getWorkYyyymmdd(){
        return workYyyymmdd;
    }
    public String getModelSuffix(){
        return modelSuffix;
    }
    public String getUncntShipmentYyyymmdd(){
        return uncntShipmentYyyymmdd;
    }
    public Integer getUncntShipmentCount(){
        return uncntShipmentCount;
    }
    public String getShipmentYyyymmdd(){
        return shipmentYyyymmdd;
    }
    public Integer getShipmentCount(){
        return shipmentCount;
    }
    public String getShipmentType(){
        return shipmentType;
    }
}

