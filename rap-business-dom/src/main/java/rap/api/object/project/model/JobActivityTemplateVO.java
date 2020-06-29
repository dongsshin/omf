/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : JobActivityTemplateVO.java
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
public class JobActivityTemplateVO extends BusinessObjectMasterVO {
    private String        activityNameKor                                   ;
    private String        revision                                           = "-";
    private String        previousObid                                       = "1";
    private String        activityNameEng                                   ;
    private String        nextObid                                           = "1";
    private String        activityNameChi                                   ;


    public void    setActivityNameKor(String activityNameKor){
        this.activityNameKor = activityNameKor;
    }
    public void    setRevision(String revision){
        this.revision = revision;
    }
    public void    setPreviousObid(String previousObid){
        this.previousObid = previousObid;
    }
    public void    setActivityNameEng(String activityNameEng){
        this.activityNameEng = activityNameEng;
    }
    public void    setNextObid(String nextObid){
        this.nextObid = nextObid;
    }
    public void    setActivityNameChi(String activityNameChi){
        this.activityNameChi = activityNameChi;
    }
    public String getActivityNameKor(){
        return activityNameKor;
    }
    public String getRevision(){
        return revision;
    }
    public String getPreviousObid(){
        return previousObid;
    }
    public String getActivityNameEng(){
        return activityNameEng;
    }
    public String getNextObid(){
        return nextObid;
    }
    public String getActivityNameChi(){
        return activityNameChi;
    }
}

