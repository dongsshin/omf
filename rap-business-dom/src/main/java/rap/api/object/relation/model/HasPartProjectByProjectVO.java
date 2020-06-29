/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasPartProjectByProjectVO.java
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
public class HasPartProjectByProjectVO extends BusinessRelationObjectVO {
    private String        partDevelopmentType                               ;
    private String        partRegisterStatus                                ;
    private String        prodProjectGubun                                  ;
    private String        prevPartProjectCode                               ;


    public void    setPartDevelopmentType(String partDevelopmentType){
        this.partDevelopmentType = partDevelopmentType;
    }
    public void    setPartRegisterStatus(String partRegisterStatus){
        this.partRegisterStatus = partRegisterStatus;
    }
    public void    setProdProjectGubun(String prodProjectGubun){
        this.prodProjectGubun = prodProjectGubun;
    }
    public void    setPrevPartProjectCode(String prevPartProjectCode){
        this.prevPartProjectCode = prevPartProjectCode;
    }
    public String getPartDevelopmentType(){
        return partDevelopmentType;
    }
    public String getPartRegisterStatus(){
        return partRegisterStatus;
    }
    public String getProdProjectGubun(){
        return prodProjectGubun;
    }
    public String getPrevPartProjectCode(){
        return prevPartProjectCode;
    }
}

