/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProdPlanSpecSheetHistoryVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.pp.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ProdPlanSpecSheetHistoryVO extends BusinessObjectMasterVO {
    private String        specHistId                                        ;
    private String        histType                                          ;
    private String        specSheetId                                       ;


    public void    setSpecHistId(String specHistId){
        this.specHistId = specHistId;
    }
    public void    setHistType(String histType){
        this.histType = histType;
    }
    public void    setSpecSheetId(String specSheetId){
        this.specSheetId = specSheetId;
    }
    public String getSpecHistId(){
        return specHistId;
    }
    public String getHistType(){
        return histType;
    }
    public String getSpecSheetId(){
        return specSheetId;
    }
}

