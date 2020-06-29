/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : SWMaintenanceMCVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import rap.api.object.project.model.SoftwareMaintenanceProjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class SWMaintenanceMCVO extends SoftwareMaintenanceProjectVO {
    private String        operator                                          ;


    public void    setOperator(String operator){
        this.operator = operator;
    }
    public String getOperator(){
        return operator;
    }
}

