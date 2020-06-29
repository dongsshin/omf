/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : UsingDevelopmentTypeVO.java
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
public class UsingDevelopmentTypeVO extends BusinessRelationObjectVO {
    private String        isActive                                           = "Y";
    private String        isDeleted                                          = "N";


    public void    setIsActive(String isActive){
        this.isActive = isActive;
    }
    public void    setIsDeleted(String isDeleted){
        this.isDeleted = isDeleted;
    }
    public String getIsActive(){
        return isActive;
    }
    public String getIsDeleted(){
        return isDeleted;
    }
}

