/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ClProjectMMVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.closing.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ClProjectMMVO extends BusinessObjectMasterVO {
    private String        closeYm                                           ;
    private String        subsidiary                                        ;
    private String        accountingUnitCode                                ;
    private String        departmentCode                                    ;
    private String        projectCode                                       ;
    private Float         manMonth                                          ;


    public void    setCloseYm(String closeYm){
        this.closeYm = closeYm;
    }
    public void    setSubsidiary(String subsidiary){
        this.subsidiary = subsidiary;
    }
    public void    setAccountingUnitCode(String accountingUnitCode){
        this.accountingUnitCode = accountingUnitCode;
    }
    public void    setDepartmentCode(String departmentCode){
        this.departmentCode = departmentCode;
    }
    public void    setProjectCode(String projectCode){
        this.projectCode = projectCode;
    }
    public void    setManMonth(Float manMonth){
        this.manMonth = manMonth;
    }
    public String getCloseYm(){
        return closeYm;
    }
    public String getSubsidiary(){
        return subsidiary;
    }
    public String getAccountingUnitCode(){
        return accountingUnitCode;
    }
    public String getDepartmentCode(){
        return departmentCode;
    }
    public String getProjectCode(){
        return projectCode;
    }
    public Float getManMonth(){
        return manMonth;
    }
}

