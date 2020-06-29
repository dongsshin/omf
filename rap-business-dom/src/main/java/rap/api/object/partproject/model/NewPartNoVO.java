/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : NewPartNoVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.partproject.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class NewPartNoVO extends BusinessObjectMasterVO {
    private String        partNo                                            ;
    private String        division                                          ;
    private String        affiliateCode                                     ;
    private String        systemDate                                        ;
    private String        classCode                                         ;
    private String        partClassName                                     ;
    private String        technicalSpecification                            ;
    private String        technicalSpecficationLayout                       ;
    private String        uom                                               ;
    private String        uit                                               ;
    private String        status                                            ;
    private String        creationDivision                                  ;
    private String        creationStatus                                    ;
    private Date          interfaceDate                                     ;


    public void    setPartNo(String partNo){
        this.partNo = partNo;
    }
    public void    setDivision(String division){
        this.division = division;
    }
    public void    setAffiliateCode(String affiliateCode){
        this.affiliateCode = affiliateCode;
    }
    public void    setSystemDate(String systemDate){
        this.systemDate = systemDate;
    }
    public void    setClassCode(String classCode){
        this.classCode = classCode;
    }
    public void    setPartClassName(String partClassName){
        this.partClassName = partClassName;
    }
    public void    setTechnicalSpecification(String technicalSpecification){
        this.technicalSpecification = technicalSpecification;
    }
    public void    setTechnicalSpecficationLayout(String technicalSpecficationLayout){
        this.technicalSpecficationLayout = technicalSpecficationLayout;
    }
    public void    setUom(String uom){
        this.uom = uom;
    }
    public void    setUit(String uit){
        this.uit = uit;
    }
    public void    setStatus(String status){
        this.status = status;
    }
    public void    setCreationDivision(String creationDivision){
        this.creationDivision = creationDivision;
    }
    public void    setCreationStatus(String creationStatus){
        this.creationStatus = creationStatus;
    }
    public void    setInterfaceDate(Date interfaceDate){
        this.interfaceDate = interfaceDate;
    }
    public void    setInterfaceDate(String    interfaceDate){
        this.interfaceDate = this.omcConvertStr2Date(interfaceDate);
    }
    public String getPartNo(){
        return partNo;
    }
    public String getDivision(){
        return division;
    }
    public String getAffiliateCode(){
        return affiliateCode;
    }
    public String getSystemDate(){
        return systemDate;
    }
    public String getClassCode(){
        return classCode;
    }
    public String getPartClassName(){
        return partClassName;
    }
    public String getTechnicalSpecification(){
        return technicalSpecification;
    }
    public String getTechnicalSpecficationLayout(){
        return technicalSpecficationLayout;
    }
    public String getUom(){
        return uom;
    }
    public String getUit(){
        return uit;
    }
    public String getStatus(){
        return status;
    }
    public String getCreationDivision(){
        return creationDivision;
    }
    public String getCreationStatus(){
        return creationStatus;
    }
    public Date getInterfaceDate(){
        return interfaceDate;
    }
}

