/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ClIndirectAllocBasisVO.java
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
public class ClIndirectAllocBasisVO extends BusinessObjectMasterVO {
    private String        closeYm                                           ;
    private String        subsidiary                                        ;
    private String        accountingUnitCode                                ;
    private String        departmentCode                                    ;
    private String        projectCode                                       ;
    private Integer       distDgr                                           ;
    private String        distBasisFg                                       ;
    private String        distSendYn                                        ;
    private String        distNotReceiveYn                                  ;


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
    public void    setDistDgr(Integer distDgr){
        this.distDgr = distDgr;
    }
    public void    setDistBasisFg(String distBasisFg){
        this.distBasisFg = distBasisFg;
    }
    public void    setDistSendYn(String distSendYn){
        this.distSendYn = distSendYn;
    }
    public void    setDistNotReceiveYn(String distNotReceiveYn){
        this.distNotReceiveYn = distNotReceiveYn;
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
    public Integer getDistDgr(){
        return distDgr;
    }
    public String getDistBasisFg(){
        return distBasisFg;
    }
    public String getDistSendYn(){
        return distSendYn;
    }
    public String getDistNotReceiveYn(){
        return distNotReceiveYn;
    }
}

