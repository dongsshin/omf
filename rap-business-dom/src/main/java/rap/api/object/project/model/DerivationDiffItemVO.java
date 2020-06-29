/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : DerivationDiffItemVO.java
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
public class DerivationDiffItemVO extends BusinessObjectMasterVO {
    private String        deriMainCode                                      ;
    private String        deriSubCode                                       ;
    private String        checkItem                                         ;
    private String        item                                              ;
    private String        suffixCode                                        ;
    private String        divisionCode                                      ;


    public void    setDeriMainCode(String deriMainCode){
        this.deriMainCode = deriMainCode;
    }
    public void    setDeriSubCode(String deriSubCode){
        this.deriSubCode = deriSubCode;
    }
    public void    setCheckItem(String checkItem){
        this.checkItem = checkItem;
    }
    public void    setItem(String item){
        this.item = item;
    }
    public void    setSuffixCode(String suffixCode){
        this.suffixCode = suffixCode;
    }
    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public String getDeriMainCode(){
        return deriMainCode;
    }
    public String getDeriSubCode(){
        return deriSubCode;
    }
    public String getCheckItem(){
        return checkItem;
    }
    public String getItem(){
        return item;
    }
    public String getSuffixCode(){
        return suffixCode;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
}

