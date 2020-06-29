/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProdPlanSpecSheetColumnVO.java
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
public class ProdPlanSpecSheetColumnVO extends BusinessObjectMasterVO {
    private String        specSheetId                                       ;
    private String        specColId                                         ;
    private Integer       colNo                                             ;


    public void    setSpecSheetId(String specSheetId){
        this.specSheetId = specSheetId;
    }
    public void    setSpecColId(String specColId){
        this.specColId = specColId;
    }
    public void    setColNo(Integer colNo){
        this.colNo = colNo;
    }
    public String getSpecSheetId(){
        return specSheetId;
    }
    public String getSpecColId(){
        return specColId;
    }
    public Integer getColNo(){
        return colNo;
    }
}

