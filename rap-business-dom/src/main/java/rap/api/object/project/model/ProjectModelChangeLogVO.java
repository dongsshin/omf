/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectModelChangeLogVO.java
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
public class ProjectModelChangeLogVO extends BusinessObjectMasterVO {
    private String        oldModelSuffix                                    ;
    private String        newdModelSuffix                                   ;
    private String        eventCode                                         ;
    private String        fromObid                                          ;


    public void    setOldModelSuffix(String oldModelSuffix){
        this.oldModelSuffix = oldModelSuffix;
    }
    public void    setNewdModelSuffix(String newdModelSuffix){
        this.newdModelSuffix = newdModelSuffix;
    }
    public void    setEventCode(String eventCode){
        this.eventCode = eventCode;
    }
    public void    setFromObid(String fromObid){
        this.fromObid = fromObid;
    }
    public String getOldModelSuffix(){
        return oldModelSuffix;
    }
    public String getNewdModelSuffix(){
        return newdModelSuffix;
    }
    public String getEventCode(){
        return eventCode;
    }
    public String getFromObid(){
        return fromObid;
    }
}

