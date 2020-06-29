/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : DesktopMemoVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.common.model;


import com.rap.omc.api.object.model.BusinessObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class DesktopMemoVO extends BusinessObjectVO {
    private String        memo                                              ;


    public void    setMemo(String memo){
        this.memo = memo;
    }
    public String getMemo(){
        return memo;
    }
}

