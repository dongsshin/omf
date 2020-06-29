/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PartProjectMembersRevVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.partproject.model;


import com.rap.omc.api.object.model.BusinessObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class PartProjectMembersRevVO extends BusinessObjectVO {
    private String        isFinalYn                                          = "N";


    public void    setIsFinalYn(String isFinalYn){
        this.isFinalYn = isFinalYn;
    }
    public String getIsFinalYn(){
        return isFinalYn;
    }
}

