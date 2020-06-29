/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : SWMaintenanceVCVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import rap.api.object.project.model.SoftwareMaintenanceProjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class SWMaintenanceVCVO extends SoftwareMaintenanceProjectVO {
    private String        previousMapVersion                                ;
    private String        newMapVersion                                     ;
    private String        oem                                               ;
    private String        platform                                          ;
    private String        shipTo                                            ;


    public void    setPreviousMapVersion(String previousMapVersion){
        this.previousMapVersion = previousMapVersion;
    }
    public void    setNewMapVersion(String newMapVersion){
        this.newMapVersion = newMapVersion;
    }
    public void    setOem(String oem){
        this.oem = oem;
    }
    public void    setPlatform(String platform){
        this.platform = platform;
    }
    public void    setShipTo(String shipTo){
        this.shipTo = shipTo;
    }
    public String getPreviousMapVersion(){
        return previousMapVersion;
    }
    public String getNewMapVersion(){
        return newMapVersion;
    }
    public String getOem(){
        return oem;
    }
    public String getPlatform(){
        return platform;
    }
    public String getShipTo(){
        return shipTo;
    }
}

