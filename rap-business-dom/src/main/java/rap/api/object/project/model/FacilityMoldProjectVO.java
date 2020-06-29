/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : FacilityMoldProjectVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import rap.api.object.project.model.FacilityProjectsVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class FacilityMoldProjectVO extends FacilityProjectsVO {
    private String        poNo                                              ;
    private String        poPartNo                                          ;
    private String        poExpenseType                                     ;
    private String        mesProjectCode                                    ;


    public void    setPoNo(String poNo){
        this.poNo = poNo;
    }
    public void    setPoPartNo(String poPartNo){
        this.poPartNo = poPartNo;
    }
    public void    setPoExpenseType(String poExpenseType){
        this.poExpenseType = poExpenseType;
    }
    public void    setMesProjectCode(String mesProjectCode){
        this.mesProjectCode = mesProjectCode;
    }
    public String getPoNo(){
        return poNo;
    }
    public String getPoPartNo(){
        return poPartNo;
    }
    public String getPoExpenseType(){
        return poExpenseType;
    }
    public String getMesProjectCode(){
        return mesProjectCode;
    }
}

