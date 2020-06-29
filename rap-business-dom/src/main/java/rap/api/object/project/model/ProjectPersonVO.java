/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectPersonVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import rap.api.object.project.model.ProjectMembersVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ProjectPersonVO extends ProjectMembersVO {
    private String        userId                                            ;
    private String        memberType                                         = "Regular";


    public void    setUserId(String userId){
        this.userId = userId;
    }
    public void    setMemberType(String memberType){
        this.memberType = memberType;
    }
    public String getUserId(){
        return userId;
    }
    public String getMemberType(){
        return memberType;
    }
}

