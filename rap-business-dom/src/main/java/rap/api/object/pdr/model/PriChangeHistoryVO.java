/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PriChangeHistoryVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.pdr.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class PriChangeHistoryVO extends BusinessObjectMasterVO {
    private String        priobid                                           ;
    private String        prino                                             ;
    private String        previouspriobid                                   ;
    private String        previousprino                                     ;
    private Integer       currentprirevision                                ;
    private String        hasspecinfobypdrobid                              ;
    private String        specitemobid                                      ;
    private String        specitemname                                      ;
    private String        specitemclass                                     ;
    private String        specitemtypecode                                  ;
    private String        specitemvalueobid                                 ;
    private String        specitemvaluename                                 ;
    private String        item1stvalue                                      ;
    private String        item2ndvalue                                      ;
    private String        previous1stvalue                                  ;
    private String        previous2ndvalue                                  ;
    private String        workdelimitercode                                 ;
    private String        propertyflag                                      ;
    private String        specitemcode                                      ;
    private Integer       pdrId                                             ;
    private String        pdrRevision                                       ;


    public void    setPriobid(String priobid){
        this.priobid = priobid;
    }
    public void    setPrino(String prino){
        this.prino = prino;
    }
    public void    setPreviouspriobid(String previouspriobid){
        this.previouspriobid = previouspriobid;
    }
    public void    setPreviousprino(String previousprino){
        this.previousprino = previousprino;
    }
    public void    setCurrentprirevision(Integer currentprirevision){
        this.currentprirevision = currentprirevision;
    }
    public void    setHasspecinfobypdrobid(String hasspecinfobypdrobid){
        this.hasspecinfobypdrobid = hasspecinfobypdrobid;
    }
    public void    setSpecitemobid(String specitemobid){
        this.specitemobid = specitemobid;
    }
    public void    setSpecitemname(String specitemname){
        this.specitemname = specitemname;
    }
    public void    setSpecitemclass(String specitemclass){
        this.specitemclass = specitemclass;
    }
    public void    setSpecitemtypecode(String specitemtypecode){
        this.specitemtypecode = specitemtypecode;
    }
    public void    setSpecitemvalueobid(String specitemvalueobid){
        this.specitemvalueobid = specitemvalueobid;
    }
    public void    setSpecitemvaluename(String specitemvaluename){
        this.specitemvaluename = specitemvaluename;
    }
    public void    setItem1stvalue(String item1stvalue){
        this.item1stvalue = item1stvalue;
    }
    public void    setItem2ndvalue(String item2ndvalue){
        this.item2ndvalue = item2ndvalue;
    }
    public void    setPrevious1stvalue(String previous1stvalue){
        this.previous1stvalue = previous1stvalue;
    }
    public void    setPrevious2ndvalue(String previous2ndvalue){
        this.previous2ndvalue = previous2ndvalue;
    }
    public void    setWorkdelimitercode(String workdelimitercode){
        this.workdelimitercode = workdelimitercode;
    }
    public void    setPropertyflag(String propertyflag){
        this.propertyflag = propertyflag;
    }
    public void    setSpecitemcode(String specitemcode){
        this.specitemcode = specitemcode;
    }
    public void    setPdrId(Integer pdrId){
        this.pdrId = pdrId;
    }
    public void    setPdrRevision(String pdrRevision){
        this.pdrRevision = pdrRevision;
    }
    public String getPriobid(){
        return priobid;
    }
    public String getPrino(){
        return prino;
    }
    public String getPreviouspriobid(){
        return previouspriobid;
    }
    public String getPreviousprino(){
        return previousprino;
    }
    public Integer getCurrentprirevision(){
        return currentprirevision;
    }
    public String getHasspecinfobypdrobid(){
        return hasspecinfobypdrobid;
    }
    public String getSpecitemobid(){
        return specitemobid;
    }
    public String getSpecitemname(){
        return specitemname;
    }
    public String getSpecitemclass(){
        return specitemclass;
    }
    public String getSpecitemtypecode(){
        return specitemtypecode;
    }
    public String getSpecitemvalueobid(){
        return specitemvalueobid;
    }
    public String getSpecitemvaluename(){
        return specitemvaluename;
    }
    public String getItem1stvalue(){
        return item1stvalue;
    }
    public String getItem2ndvalue(){
        return item2ndvalue;
    }
    public String getPrevious1stvalue(){
        return previous1stvalue;
    }
    public String getPrevious2ndvalue(){
        return previous2ndvalue;
    }
    public String getWorkdelimitercode(){
        return workdelimitercode;
    }
    public String getPropertyflag(){
        return propertyflag;
    }
    public String getSpecitemcode(){
        return specitemcode;
    }
    public Integer getPdrId(){
        return pdrId;
    }
    public String getPdrRevision(){
        return pdrRevision;
    }
}

