/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasVersionDepartmentVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.relation.model;


import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class HasVersionDepartmentVO extends BusinessRelationObjectVO {
    private String        realDepartmentCode                                ;
    private String        realAccountingUnitCode                            ;
    private String        hrDeptCode                                        ;
    private String        hrUpperDeptCode                                   ;
    private String        hrOrganizationLevel                               ;
    private String        hrDeptKorName                                     ;
    private String        hrDeptEngName                                     ;
    private String        hrUpperDeptKorName                                ;
    private String        hrUpperDeptEngName                                ;
    private String        leaderEmployeeNo                                  ;
    private String        leaderEmployeeName                                ;
    private String        inputEmployeeNo                                   ;
    private String        editableFlag                                      ;


    public void    setRealDepartmentCode(String realDepartmentCode){
        this.realDepartmentCode = realDepartmentCode;
    }
    public void    setRealAccountingUnitCode(String realAccountingUnitCode){
        this.realAccountingUnitCode = realAccountingUnitCode;
    }
    public void    setHrDeptCode(String hrDeptCode){
        this.hrDeptCode = hrDeptCode;
    }
    public void    setHrUpperDeptCode(String hrUpperDeptCode){
        this.hrUpperDeptCode = hrUpperDeptCode;
    }
    public void    setHrOrganizationLevel(String hrOrganizationLevel){
        this.hrOrganizationLevel = hrOrganizationLevel;
    }
    public void    setHrDeptKorName(String hrDeptKorName){
        this.hrDeptKorName = hrDeptKorName;
    }
    public void    setHrDeptEngName(String hrDeptEngName){
        this.hrDeptEngName = hrDeptEngName;
    }
    public void    setHrUpperDeptKorName(String hrUpperDeptKorName){
        this.hrUpperDeptKorName = hrUpperDeptKorName;
    }
    public void    setHrUpperDeptEngName(String hrUpperDeptEngName){
        this.hrUpperDeptEngName = hrUpperDeptEngName;
    }
    public void    setLeaderEmployeeNo(String leaderEmployeeNo){
        this.leaderEmployeeNo = leaderEmployeeNo;
    }
    public void    setLeaderEmployeeName(String leaderEmployeeName){
        this.leaderEmployeeName = leaderEmployeeName;
    }
    public void    setInputEmployeeNo(String inputEmployeeNo){
        this.inputEmployeeNo = inputEmployeeNo;
    }
    public void    setEditableFlag(String editableFlag){
        this.editableFlag = editableFlag;
    }
    public String getRealDepartmentCode(){
        return realDepartmentCode;
    }
    public String getRealAccountingUnitCode(){
        return realAccountingUnitCode;
    }
    public String getHrDeptCode(){
        return hrDeptCode;
    }
    public String getHrUpperDeptCode(){
        return hrUpperDeptCode;
    }
    public String getHrOrganizationLevel(){
        return hrOrganizationLevel;
    }
    public String getHrDeptKorName(){
        return hrDeptKorName;
    }
    public String getHrDeptEngName(){
        return hrDeptEngName;
    }
    public String getHrUpperDeptKorName(){
        return hrUpperDeptKorName;
    }
    public String getHrUpperDeptEngName(){
        return hrUpperDeptEngName;
    }
    public String getLeaderEmployeeNo(){
        return leaderEmployeeNo;
    }
    public String getLeaderEmployeeName(){
        return leaderEmployeeName;
    }
    public String getInputEmployeeNo(){
        return inputEmployeeNo;
    }
    public String getEditableFlag(){
        return editableFlag;
    }
}

