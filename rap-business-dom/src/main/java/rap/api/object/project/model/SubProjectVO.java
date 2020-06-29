/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : SubProjectVO.java
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
public class SubProjectVO extends BusinessObjectMasterVO {
    private String        projectTypeCode                                   ;
    private Integer       sortOrderNo                                       ;


    public void    setProjectTypeCode(String projectTypeCode){
        this.projectTypeCode = projectTypeCode;
    }
    public void    setSortOrderNo(Integer sortOrderNo){
        this.sortOrderNo = sortOrderNo;
    }
    public String getProjectTypeCode(){
        return projectTypeCode;
    }
    public Integer getSortOrderNo(){
        return sortOrderNo;
    }
}

