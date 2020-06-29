/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : OemEventTemplateVO.java
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
public class OemEventTemplateVO extends BusinessObjectMasterVO {
    private Integer       sequences                                         ;
    private String        pmsEvent                                          ;
    private String        oemSignalR                                        ;
    private String        oemSignalY                                        ;
    private String        pmsEventBase                                      ;


    public void    setSequences(Integer sequences){
        this.sequences = sequences;
    }
    public void    setPmsEvent(String pmsEvent){
        this.pmsEvent = pmsEvent;
    }
    public void    setOemSignalR(String oemSignalR){
        this.oemSignalR = oemSignalR;
    }
    public void    setOemSignalY(String oemSignalY){
        this.oemSignalY = oemSignalY;
    }
    public void    setPmsEventBase(String pmsEventBase){
        this.pmsEventBase = pmsEventBase;
    }
    public Integer getSequences(){
        return sequences;
    }
    public String getPmsEvent(){
        return pmsEvent;
    }
    public String getOemSignalR(){
        return oemSignalR;
    }
    public String getOemSignalY(){
        return oemSignalY;
    }
    public String getPmsEventBase(){
        return pmsEventBase;
    }
}

