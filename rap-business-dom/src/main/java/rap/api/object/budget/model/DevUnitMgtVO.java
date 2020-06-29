/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : DevUnitMgtVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.budget.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class DevUnitMgtVO extends BusinessObjectMasterVO {
    private String        oldDeptCode                                       ;
    private String        accountingUnitCode                                ;
    private String        deptCode                                          ;
    private String        devEd                                             ;
    private String        devUnit                                           ;
    private String        devUnitCode                                       ;


    public void    setOldDeptCode(String oldDeptCode){
        this.oldDeptCode = oldDeptCode;
    }
    public void    setAccountingUnitCode(String accountingUnitCode){
        this.accountingUnitCode = accountingUnitCode;
    }
    public void    setDeptCode(String deptCode){
        this.deptCode = deptCode;
    }
    public void    setDevEd(String devEd){
        this.devEd = devEd;
    }
    public void    setDevUnit(String devUnit){
        this.devUnit = devUnit;
    }
    public void    setDevUnitCode(String devUnitCode){
        this.devUnitCode = devUnitCode;
    }
    public String getOldDeptCode(){
        return oldDeptCode;
    }
    public String getAccountingUnitCode(){
        return accountingUnitCode;
    }
    public String getDeptCode(){
        return deptCode;
    }
    public String getDevEd(){
        return devEd;
    }
    public String getDevUnit(){
        return devUnit;
    }
    public String getDevUnitCode(){
        return devUnitCode;
    }
}

