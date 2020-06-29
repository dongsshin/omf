/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ClLaborUnitCostVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.closing.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ClLaborUnitCostVO extends BusinessObjectMasterVO {
    private String        closeYm                                           ;
    private String        jobGrade                                          ;
    private BigDecimal    laborCost                                          = new BigDecimal(0);


    public void    setCloseYm(String closeYm){
        this.closeYm = closeYm;
    }
    public void    setJobGrade(String jobGrade){
        this.jobGrade = jobGrade;
    }
    public void    setLaborCost(BigDecimal laborCost){
        this.laborCost = laborCost;
    }
    public String getCloseYm(){
        return closeYm;
    }
    public String getJobGrade(){
        return jobGrade;
    }
    public BigDecimal getLaborCost(){
        return laborCost;
    }
}

