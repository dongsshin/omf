/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : DesignRequestsScheduleVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.requests.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class DesignRequestsScheduleVO extends BusinessObjectMasterVO {
    private String        fromObid                                          ;
    private Integer       sortOrderNo                                       ;
    private String        activity1Date                                     ;
    private String        activity2Date                                     ;
    private String        activity3Date                                     ;
    private String        activity4Date                                     ;
    private String        activity5Date                                     ;
    private String        activity6Date                                     ;
    private String        scheduleComment                                   ;


    public void    setFromObid(String fromObid){
        this.fromObid = fromObid;
    }
    public void    setSortOrderNo(Integer sortOrderNo){
        this.sortOrderNo = sortOrderNo;
    }
    public void    setActivity1Date(String activity1Date){
        this.activity1Date = activity1Date;
    }
    public void    setActivity2Date(String activity2Date){
        this.activity2Date = activity2Date;
    }
    public void    setActivity3Date(String activity3Date){
        this.activity3Date = activity3Date;
    }
    public void    setActivity4Date(String activity4Date){
        this.activity4Date = activity4Date;
    }
    public void    setActivity5Date(String activity5Date){
        this.activity5Date = activity5Date;
    }
    public void    setActivity6Date(String activity6Date){
        this.activity6Date = activity6Date;
    }
    public void    setScheduleComment(String scheduleComment){
        this.scheduleComment = scheduleComment;
    }
    public String getFromObid(){
        return fromObid;
    }
    public Integer getSortOrderNo(){
        return sortOrderNo;
    }
    public String getActivity1Date(){
        return activity1Date;
    }
    public String getActivity2Date(){
        return activity2Date;
    }
    public String getActivity3Date(){
        return activity3Date;
    }
    public String getActivity4Date(){
        return activity4Date;
    }
    public String getActivity5Date(){
        return activity5Date;
    }
    public String getActivity6Date(){
        return activity6Date;
    }
    public String getScheduleComment(){
        return scheduleComment;
    }
}

