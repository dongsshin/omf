/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : GroupProjectVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.brm.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class GroupProjectVO extends BusinessObjectMasterVO {
    private String        useYn                                             ;


    public void    setUseYn(String useYn){
        this.useYn = useYn;
    }
    public String getUseYn(){
        return useYn;
    }
}

