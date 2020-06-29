/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasCheckItemSubPointVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.relation.model;


import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class HasCheckItemSubPointVO extends BusinessRelationObjectVO {
    private Integer       allottedPoint                                     ;
    private String        subPointPoint                                     ;
    private Integer       sortOrderNo                                       ;
    private String        finalPoint                                        ;
    private String        descriptions                                      ;


    public void    setAllottedPoint(Integer allottedPoint){
        this.allottedPoint = allottedPoint;
    }
    public void    setSubPointPoint(String subPointPoint){
        this.subPointPoint = subPointPoint;
    }
    public void    setSortOrderNo(Integer sortOrderNo){
        this.sortOrderNo = sortOrderNo;
    }
    public void    setFinalPoint(String finalPoint){
        this.finalPoint = finalPoint;
    }
    public void    setDescriptions(String descriptions){
        this.descriptions = descriptions;
    }
    public Integer getAllottedPoint(){
        return allottedPoint;
    }
    public String getSubPointPoint(){
        return subPointPoint;
    }
    public Integer getSortOrderNo(){
        return sortOrderNo;
    }
    public String getFinalPoint(){
        return finalPoint;
    }
    public String getDescriptions(){
        return descriptions;
    }
}

