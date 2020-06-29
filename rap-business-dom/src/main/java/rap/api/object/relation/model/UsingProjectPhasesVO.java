/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : UsingProjectPhasesVO.java
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
public class UsingProjectPhasesVO extends BusinessRelationObjectVO {
    private Integer       sequences                                          = 100;
    private String        isActive                                           = "Y";
    private String        displayedCode                                     ;
    private String        displayedName                                     ;


    public void    setSequences(Integer sequences){
        this.sequences = sequences;
    }
    public void    setIsActive(String isActive){
        this.isActive = isActive;
    }
    public void    setDisplayedCode(String displayedCode){
        this.displayedCode = displayedCode;
    }
    public void    setDisplayedName(String displayedName){
        this.displayedName = displayedName;
    }
    public Integer getSequences(){
        return sequences;
    }
    public String getIsActive(){
        return isActive;
    }
    public String getDisplayedCode(){
        return displayedCode;
    }
    public String getDisplayedName(){
        return displayedName;
    }
}

