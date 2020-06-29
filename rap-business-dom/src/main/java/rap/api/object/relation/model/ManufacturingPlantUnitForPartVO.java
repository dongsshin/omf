/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ManufacturingPlantUnitForPartVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.relation.model;


import rap.api.object.relation.model.ManufacturingPlantUnitRootVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ManufacturingPlantUnitForPartVO extends ManufacturingPlantUnitRootVO {
    private String        pimsModule                                        ;
    private String        partClassCode                                     ;
    private String        partClassName                                     ;
    private String        technicalSpecification                            ;
    private String        uit                                               ;
    private String        statusCode                                        ;
    private String        preferredness                                     ;
    private String        pbFree                                            ;
    private String        statusChangeDate                                  ;
    private String        attribute1                                        ;
    private String        attribute2                                        ;
    private String        attribute3                                        ;
    private String        attribute4                                        ;
    private String        attribute5                                        ;


    public void    setPimsModule(String pimsModule){
        this.pimsModule = pimsModule;
    }
    public void    setPartClassCode(String partClassCode){
        this.partClassCode = partClassCode;
    }
    public void    setPartClassName(String partClassName){
        this.partClassName = partClassName;
    }
    public void    setTechnicalSpecification(String technicalSpecification){
        this.technicalSpecification = technicalSpecification;
    }
    public void    setUit(String uit){
        this.uit = uit;
    }
    public void    setStatusCode(String statusCode){
        this.statusCode = statusCode;
    }
    public void    setPreferredness(String preferredness){
        this.preferredness = preferredness;
    }
    public void    setPbFree(String pbFree){
        this.pbFree = pbFree;
    }
    public void    setStatusChangeDate(String statusChangeDate){
        this.statusChangeDate = statusChangeDate;
    }
    public void    setAttribute1(String attribute1){
        this.attribute1 = attribute1;
    }
    public void    setAttribute2(String attribute2){
        this.attribute2 = attribute2;
    }
    public void    setAttribute3(String attribute3){
        this.attribute3 = attribute3;
    }
    public void    setAttribute4(String attribute4){
        this.attribute4 = attribute4;
    }
    public void    setAttribute5(String attribute5){
        this.attribute5 = attribute5;
    }
    public String getPimsModule(){
        return pimsModule;
    }
    public String getPartClassCode(){
        return partClassCode;
    }
    public String getPartClassName(){
        return partClassName;
    }
    public String getTechnicalSpecification(){
        return technicalSpecification;
    }
    public String getUit(){
        return uit;
    }
    public String getStatusCode(){
        return statusCode;
    }
    public String getPreferredness(){
        return preferredness;
    }
    public String getPbFree(){
        return pbFree;
    }
    public String getStatusChangeDate(){
        return statusChangeDate;
    }
    public String getAttribute1(){
        return attribute1;
    }
    public String getAttribute2(){
        return attribute2;
    }
    public String getAttribute3(){
        return attribute3;
    }
    public String getAttribute4(){
        return attribute4;
    }
    public String getAttribute5(){
        return attribute5;
    }
}

