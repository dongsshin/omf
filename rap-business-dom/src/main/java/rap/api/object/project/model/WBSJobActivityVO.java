/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : WBSJobActivityVO.java
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
public class WBSJobActivityVO extends BusinessObjectMasterVO {
    private Integer       progress                                           = 0;
    private String        userId                                            ;
    private String        activityNameKor                                   ;
    private String        activityNameEng                                   ;
    private String        activityNameChi                                   ;
    private String        fromTemplate                                      ;


    public void    setProgress(Integer progress){
        this.progress = progress;
    }
    public void    setUserId(String userId){
        this.userId = userId;
    }
    public void    setActivityNameKor(String activityNameKor){
        this.activityNameKor = activityNameKor;
    }
    public void    setActivityNameEng(String activityNameEng){
        this.activityNameEng = activityNameEng;
    }
    public void    setActivityNameChi(String activityNameChi){
        this.activityNameChi = activityNameChi;
    }
    public void    setFromTemplate(String fromTemplate){
        this.fromTemplate = fromTemplate;
    }
    public Integer getProgress(){
        return progress;
    }
    public String getUserId(){
        return userId;
    }
    public String getActivityNameKor(){
        return activityNameKor;
    }
    public String getActivityNameEng(){
        return activityNameEng;
    }
    public String getActivityNameChi(){
        return activityNameChi;
    }
    public String getFromTemplate(){
        return fromTemplate;
    }
}

