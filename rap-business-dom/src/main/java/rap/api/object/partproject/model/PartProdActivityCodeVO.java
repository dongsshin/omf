/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PartProdActivityCodeVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.partproject.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class PartProdActivityCodeVO extends BusinessObjectMasterVO {
    private String        activityCode                                      ;
    private String        activityNameKor                                   ;
    private String        activityNameEng                                   ;
    private String        activityNameChi                                   ;


    public void    setActivityCode(String activityCode){
        this.activityCode = activityCode;
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
    public String getActivityCode(){
        return activityCode;
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
}

