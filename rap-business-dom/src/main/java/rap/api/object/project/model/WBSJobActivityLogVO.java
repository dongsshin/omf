/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : WBSJobActivityLogVO.java
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
public class WBSJobActivityLogVO extends BusinessObjectMasterVO {
    private String        fromObid                                           = "0";
    private Integer       progress                                           = 0;


    public void    setFromObid(String fromObid){
        this.fromObid = fromObid;
    }
    public void    setProgress(Integer progress){
        this.progress = progress;
    }
    public String getFromObid(){
        return fromObid;
    }
    public Integer getProgress(){
        return progress;
    }
}

