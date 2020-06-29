/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProdPlanSpecSheetRowVO.java
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
public class ProdPlanSpecSheetRowVO extends BusinessObjectMasterVO {
    private String        specSheetId                                       ;
    private String        specRowId                                         ;
    private Integer       rowNo                                             ;


    public void    setSpecSheetId(String specSheetId){
        this.specSheetId = specSheetId;
    }
    public void    setSpecRowId(String specRowId){
        this.specRowId = specRowId;
    }
    public void    setRowNo(Integer rowNo){
        this.rowNo = rowNo;
    }
    public String getSpecSheetId(){
        return specSheetId;
    }
    public String getSpecRowId(){
        return specRowId;
    }
    public Integer getRowNo(){
        return rowNo;
    }
}

