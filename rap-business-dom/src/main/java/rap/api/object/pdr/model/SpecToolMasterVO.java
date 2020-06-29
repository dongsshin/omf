/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : SpecToolMasterVO.java
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
public class SpecToolMasterVO extends BusinessObjectMasterVO {
    private String        valuecode                                         ;
    private String        divisioncode                                      ;
    private String        toolname                                          ;
    private String        useflag                                           ;


    public void    setValuecode(String valuecode){
        this.valuecode = valuecode;
    }
    public void    setDivisioncode(String divisioncode){
        this.divisioncode = divisioncode;
    }
    public void    setToolname(String toolname){
        this.toolname = toolname;
    }
    public void    setUseflag(String useflag){
        this.useflag = useflag;
    }
    public String getValuecode(){
        return valuecode;
    }
    public String getDivisioncode(){
        return divisioncode;
    }
    public String getToolname(){
        return toolname;
    }
    public String getUseflag(){
        return useflag;
    }
}

