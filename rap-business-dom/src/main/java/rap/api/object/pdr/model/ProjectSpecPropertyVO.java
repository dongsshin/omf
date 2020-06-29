/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectSpecPropertyVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.pdr.model;


import rap.api.object.pdr.model.PDRMastersVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ProjectSpecPropertyVO extends PDRMastersVO {
    private String        activityStatus                                    ;


    public void    setActivityStatus(String activityStatus){
        this.activityStatus = activityStatus;
    }
    public String getActivityStatus(){
        return activityStatus;
    }
}

