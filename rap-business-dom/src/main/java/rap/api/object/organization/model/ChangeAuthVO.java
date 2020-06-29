/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ChangeAuthVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.organization.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ChangeAuthVO extends BusinessObjectMasterVO {
    private String        fromObid                                          ;
    private String        divisionName                                      ;
    private String        roleName                                          ;
    private String        distributeName                                    ;
    private String        person                                            ;
    private String        authName                                          ;
    private String        useFlag                                           ;


    public void    setFromObid(String fromObid){
        this.fromObid = fromObid;
    }
    public void    setDivisionName(String divisionName){
        this.divisionName = divisionName;
    }
    public void    setRoleName(String roleName){
        this.roleName = roleName;
    }
    public void    setDistributeName(String distributeName){
        this.distributeName = distributeName;
    }
    public void    setPerson(String person){
        this.person = person;
    }
    public void    setAuthName(String authName){
        this.authName = authName;
    }
    public void    setUseFlag(String useFlag){
        this.useFlag = useFlag;
    }
    public String getFromObid(){
        return fromObid;
    }
    public String getDivisionName(){
        return divisionName;
    }
    public String getRoleName(){
        return roleName;
    }
    public String getDistributeName(){
        return distributeName;
    }
    public String getPerson(){
        return person;
    }
    public String getAuthName(){
        return authName;
    }
    public String getUseFlag(){
        return useFlag;
    }
}

