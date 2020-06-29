/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PartClassLevelVO.java
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
public class PartClassLevelVO extends BusinessObjectMasterVO {
    private String        divisionCode                                      ;
    private String        partClassCode                                     ;
    private String        upPartClassCode                                   ;
    private Integer       classLevel                                        ;
    private Integer       sorting                                           ;
    private String        partClassName                                     ;
    private String        partClassNameEng                                  ;


    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setPartClassCode(String partClassCode){
        this.partClassCode = partClassCode;
    }
    public void    setUpPartClassCode(String upPartClassCode){
        this.upPartClassCode = upPartClassCode;
    }
    public void    setClassLevel(Integer classLevel){
        this.classLevel = classLevel;
    }
    public void    setSorting(Integer sorting){
        this.sorting = sorting;
    }
    public void    setPartClassName(String partClassName){
        this.partClassName = partClassName;
    }
    public void    setPartClassNameEng(String partClassNameEng){
        this.partClassNameEng = partClassNameEng;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getPartClassCode(){
        return partClassCode;
    }
    public String getUpPartClassCode(){
        return upPartClassCode;
    }
    public Integer getClassLevel(){
        return classLevel;
    }
    public Integer getSorting(){
        return sorting;
    }
    public String getPartClassName(){
        return partClassName;
    }
    public String getPartClassNameEng(){
        return partClassNameEng;
    }
}

