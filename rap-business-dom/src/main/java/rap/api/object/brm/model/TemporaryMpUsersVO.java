/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : TemporaryMpUsersVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.brm.model;


import rap.api.object.organization.model.AbstractUsersVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class TemporaryMpUsersVO extends AbstractUsersVO {
    private String        tempUserType                                      ;
    private String        skillName                                         ;


    public void    setTempUserType(String tempUserType){
        this.tempUserType = tempUserType;
    }
    public void    setSkillName(String skillName){
        this.skillName = skillName;
    }
    public String getTempUserType(){
        return tempUserType;
    }
    public String getSkillName(){
        return skillName;
    }
}

