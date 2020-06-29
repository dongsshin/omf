/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PORoleUserVO.java
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
public class PORoleUserVO extends BusinessRelationObjectVO {
    private String        domesticUserId                                    ;
    private String        foreignUserId                                     ;


    public void    setDomesticUserId(String domesticUserId){
        this.domesticUserId = domesticUserId;
    }
    public void    setForeignUserId(String foreignUserId){
        this.foreignUserId = foreignUserId;
    }
    public String getDomesticUserId(){
        return domesticUserId;
    }
    public String getForeignUserId(){
        return foreignUserId;
    }
}

