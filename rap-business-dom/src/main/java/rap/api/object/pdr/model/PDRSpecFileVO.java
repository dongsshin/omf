/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PDRSpecFileVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.pdr.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class PDRSpecFileVO extends BusinessObjectMasterVO {
    private String        fromObid                                          ;


    public void    setFromObid(String fromObid){
        this.fromObid = fromObid;
    }
    public String getFromObid(){
        return fromObid;
    }
}

