/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : OemProjectActivitysVO.java
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
public class OemProjectActivitysVO extends BusinessObjectMasterVO {
    private String        startDateYyyymmdd                                 ;
    private String        endDateYyyymmdd                                   ;
    private String        fromObid                                          ;
    private Integer       sequences                                         ;


    public void    setStartDateYyyymmdd(String startDateYyyymmdd){
        this.startDateYyyymmdd = startDateYyyymmdd;
    }
    public void    setEndDateYyyymmdd(String endDateYyyymmdd){
        this.endDateYyyymmdd = endDateYyyymmdd;
    }
    public void    setFromObid(String fromObid){
        this.fromObid = fromObid;
    }
    public void    setSequences(Integer sequences){
        this.sequences = sequences;
    }
    public String getStartDateYyyymmdd(){
        return startDateYyyymmdd;
    }
    public String getEndDateYyyymmdd(){
        return endDateYyyymmdd;
    }
    public String getFromObid(){
        return fromObid;
    }
    public Integer getSequences(){
        return sequences;
    }
}

