/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : BizTripCostVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.bizplan.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class BizTripCostVO extends BusinessObjectMasterVO {
    private String        localYn                                           ;
    private String        placeCd                                           ;
    private String        placeName                                         ;
    private String        currencyCode                                      ;
    private Float         execTransExpense                                  ;
    private Float         execActivityExpense                               ;
    private Float         execLodgingExpense                                ;
    private Float         empTransExpense                                   ;
    private Float         empActivityExpense                                ;
    private Float         empLodgingExpense                                 ;
    private String        longDistanceYn                                    ;
    private String        useYn                                             ;


    public void    setLocalYn(String localYn){
        this.localYn = localYn;
    }
    public void    setPlaceCd(String placeCd){
        this.placeCd = placeCd;
    }
    public void    setPlaceName(String placeName){
        this.placeName = placeName;
    }
    public void    setCurrencyCode(String currencyCode){
        this.currencyCode = currencyCode;
    }
    public void    setExecTransExpense(Float execTransExpense){
        this.execTransExpense = execTransExpense;
    }
    public void    setExecActivityExpense(Float execActivityExpense){
        this.execActivityExpense = execActivityExpense;
    }
    public void    setExecLodgingExpense(Float execLodgingExpense){
        this.execLodgingExpense = execLodgingExpense;
    }
    public void    setEmpTransExpense(Float empTransExpense){
        this.empTransExpense = empTransExpense;
    }
    public void    setEmpActivityExpense(Float empActivityExpense){
        this.empActivityExpense = empActivityExpense;
    }
    public void    setEmpLodgingExpense(Float empLodgingExpense){
        this.empLodgingExpense = empLodgingExpense;
    }
    public void    setLongDistanceYn(String longDistanceYn){
        this.longDistanceYn = longDistanceYn;
    }
    public void    setUseYn(String useYn){
        this.useYn = useYn;
    }
    public String getLocalYn(){
        return localYn;
    }
    public String getPlaceCd(){
        return placeCd;
    }
    public String getPlaceName(){
        return placeName;
    }
    public String getCurrencyCode(){
        return currencyCode;
    }
    public Float getExecTransExpense(){
        return execTransExpense;
    }
    public Float getExecActivityExpense(){
        return execActivityExpense;
    }
    public Float getExecLodgingExpense(){
        return execLodgingExpense;
    }
    public Float getEmpTransExpense(){
        return empTransExpense;
    }
    public Float getEmpActivityExpense(){
        return empActivityExpense;
    }
    public Float getEmpLodgingExpense(){
        return empLodgingExpense;
    }
    public String getLongDistanceYn(){
        return longDistanceYn;
    }
    public String getUseYn(){
        return useYn;
    }
}

