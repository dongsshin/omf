/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : CommentVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class CommentVO extends BusinessObjectMasterVO {
    private String        upperObid                                         ;


    public void    setUpperObid(String upperObid){
        this.upperObid = upperObid;
    }
    public String getUpperObid(){
        return upperObid;
    }
}

