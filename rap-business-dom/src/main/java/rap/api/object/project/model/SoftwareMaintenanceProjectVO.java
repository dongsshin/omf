/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : SoftwareMaintenanceProjectVO.java
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
public class SoftwareMaintenanceProjectVO extends ProductProjectsVO {
    private String        previousOsVersion                                 ;
    private String        newOsVersion                                      ;
    private String        changeInformation                                 ;


    public void    setPreviousOsVersion(String previousOsVersion){
        this.previousOsVersion = previousOsVersion;
    }
    public void    setNewOsVersion(String newOsVersion){
        this.newOsVersion = newOsVersion;
    }
    public void    setChangeInformation(String changeInformation){
        this.changeInformation = changeInformation;
    }
    public String getPreviousOsVersion(){
        return previousOsVersion;
    }
    public String getNewOsVersion(){
        return newOsVersion;
    }
    public String getChangeInformation(){
        return changeInformation;
    }
}

