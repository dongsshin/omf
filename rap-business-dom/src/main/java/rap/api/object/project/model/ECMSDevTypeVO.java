/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ECMSDevTypeVO.java
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
public class ECMSDevTypeVO extends BusinessObjectMasterVO {
    private String        typeCode                                          ;
    private String        typeDesc                                          ;
    private String        typeGradeCode                                     ;
    private Date          cpStart                                           ;
    private Date          ppStart                                           ;
    private Date          dvStart                                           ;
    private Date          pvStart                                           ;
    private Date          mpStart                                           ;


    public void    setTypeCode(String typeCode){
        this.typeCode = typeCode;
    }
    public void    setTypeDesc(String typeDesc){
        this.typeDesc = typeDesc;
    }
    public void    setTypeGradeCode(String typeGradeCode){
        this.typeGradeCode = typeGradeCode;
    }
    public void    setCpStart(Date cpStart){
        this.cpStart = cpStart;
    }
    public void    setCpStart(String    cpStart){
        this.cpStart = this.omcConvertStr2Date(cpStart);
    }
    public void    setPpStart(Date ppStart){
        this.ppStart = ppStart;
    }
    public void    setPpStart(String    ppStart){
        this.ppStart = this.omcConvertStr2Date(ppStart);
    }
    public void    setDvStart(Date dvStart){
        this.dvStart = dvStart;
    }
    public void    setDvStart(String    dvStart){
        this.dvStart = this.omcConvertStr2Date(dvStart);
    }
    public void    setPvStart(Date pvStart){
        this.pvStart = pvStart;
    }
    public void    setPvStart(String    pvStart){
        this.pvStart = this.omcConvertStr2Date(pvStart);
    }
    public void    setMpStart(Date mpStart){
        this.mpStart = mpStart;
    }
    public void    setMpStart(String    mpStart){
        this.mpStart = this.omcConvertStr2Date(mpStart);
    }
    public String getTypeCode(){
        return typeCode;
    }
    public String getTypeDesc(){
        return typeDesc;
    }
    public String getTypeGradeCode(){
        return typeGradeCode;
    }
    public Date getCpStart(){
        return cpStart;
    }
    public Date getPpStart(){
        return ppStart;
    }
    public Date getDvStart(){
        return dvStart;
    }
    public Date getPvStart(){
        return pvStart;
    }
    public Date getMpStart(){
        return mpStart;
    }
}

