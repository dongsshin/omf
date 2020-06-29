/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectSpecVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.pdr.model;


import com.rap.omc.api.object.model.BusinessObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ProjectSpecVO extends BusinessObjectVO {
    private String        eventCode                                         ;
    private String        eventName                                         ;


    public void    setEventCode(String eventCode){
        this.eventCode = eventCode;
    }
    public void    setEventName(String eventName){
        this.eventName = eventName;
    }
    public String getEventCode(){
        return eventCode;
    }
    public String getEventName(){
        return eventName;
    }
}

