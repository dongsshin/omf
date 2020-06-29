/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : SWMaintenanceTVVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import rap.api.object.project.model.SoftwareMaintenanceProjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class SWMaintenanceTVVO extends SoftwareMaintenanceProjectVO {
    private String        soc                                               ;
    private String        series                                            ;
    private String        region                                            ;


    public void    setSoc(String soc){
        this.soc = soc;
    }
    public void    setSeries(String series){
        this.series = series;
    }
    public void    setRegion(String region){
        this.region = region;
    }
    public String getSoc(){
        return soc;
    }
    public String getSeries(){
        return series;
    }
    public String getRegion(){
        return region;
    }
}

