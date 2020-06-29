/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : WBSRegularActivityVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import rap.api.object.project.model.WBSActivitiesVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class WBSRegularActivityVO extends WBSActivitiesVO {
    private String        activityType                                       = "General";


    public void    setActivityType(String activityType){
        this.activityType = activityType;
    }
    public String getActivityType(){
        return activityType;
    }
}

