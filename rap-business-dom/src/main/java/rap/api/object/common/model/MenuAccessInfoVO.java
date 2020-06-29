/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : MenuAccessInfoVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.common.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class MenuAccessInfoVO extends BusinessObjectMasterVO {
    private Integer       accessCount                                       ;
    private String        accessYearMonth                                   ;


    public void    setAccessCount(Integer accessCount){
        this.accessCount = accessCount;
    }
    public void    setAccessYearMonth(String accessYearMonth){
        this.accessYearMonth = accessYearMonth;
    }
    public Integer getAccessCount(){
        return accessCount;
    }
    public String getAccessYearMonth(){
        return accessYearMonth;
    }
}

