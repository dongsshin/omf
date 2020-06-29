/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectEventQuantityVO.java
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
public class ProjectEventQuantityVO extends BusinessObjectMasterVO {
    private String        fromObid                                          ;
    private String        eventCode                                         ;
    private Integer       quantity                                          ;
    private Integer       sorting                                           ;


    public void    setFromObid(String fromObid){
        this.fromObid = fromObid;
    }
    public void    setEventCode(String eventCode){
        this.eventCode = eventCode;
    }
    public void    setQuantity(Integer quantity){
        this.quantity = quantity;
    }
    public void    setSorting(Integer sorting){
        this.sorting = sorting;
    }
    public String getFromObid(){
        return fromObid;
    }
    public String getEventCode(){
        return eventCode;
    }
    public Integer getQuantity(){
        return quantity;
    }
    public Integer getSorting(){
        return sorting;
    }
}

