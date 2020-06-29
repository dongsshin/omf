/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : CTOReportVO.java
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
public class CTOReportVO extends BusinessObjectMasterVO {
    private String        reportCategory                                    ;
    private Integer       hitCount                                          ;


    public void    setReportCategory(String reportCategory){
        this.reportCategory = reportCategory;
    }
    public void    setHitCount(Integer hitCount){
        this.hitCount = hitCount;
    }
    public String getReportCategory(){
        return reportCategory;
    }
    public Integer getHitCount(){
        return hitCount;
    }
}

