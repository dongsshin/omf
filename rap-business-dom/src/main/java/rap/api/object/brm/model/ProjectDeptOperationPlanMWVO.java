/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectDeptOperationPlanMWVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.brm.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ProjectDeptOperationPlanMWVO extends BusinessObjectMasterVO {
    private String        yyyy                                              ;
    private String        week                                              ;
    private String        yyyyMm                                            ;
    private Float         mm                                                ;


    public void    setYyyy(String yyyy){
        this.yyyy = yyyy;
    }
    public void    setWeek(String week){
        this.week = week;
    }
    public void    setYyyyMm(String yyyyMm){
        this.yyyyMm = yyyyMm;
    }
    public void    setMm(Float mm){
        this.mm = mm;
    }
    public String getYyyy(){
        return yyyy;
    }
    public String getWeek(){
        return week;
    }
    public String getYyyyMm(){
        return yyyyMm;
    }
    public Float getMm(){
        return mm;
    }
}

