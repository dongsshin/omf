/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasOwnerByPartWBSVO.java
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
public class HasOwnerByPartWBSVO extends BusinessRelationObjectVO {
    private String        isFinalYn                                          = "N";


    public void    setIsFinalYn(String isFinalYn){
        this.isFinalYn = isFinalYn;
    }
    public String getIsFinalYn(){
        return isFinalYn;
    }
}

