/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProdPlanPRMSheetHistoryVO.java
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
public class ProdPlanPRMSheetHistoryVO extends BusinessObjectMasterVO {
    private String        prmHistId                                         ;
    private String        histType                                          ;
    private String        prmSheetId                                        ;
    private Integer       subVersion                                        ;


    public void    setPrmHistId(String prmHistId){
        this.prmHistId = prmHistId;
    }
    public void    setHistType(String histType){
        this.histType = histType;
    }
    public void    setPrmSheetId(String prmSheetId){
        this.prmSheetId = prmSheetId;
    }
    public void    setSubVersion(Integer subVersion){
        this.subVersion = subVersion;
    }
    public String getPrmHistId(){
        return prmHistId;
    }
    public String getHistType(){
        return histType;
    }
    public String getPrmSheetId(){
        return prmSheetId;
    }
    public Integer getSubVersion(){
        return subVersion;
    }
}

