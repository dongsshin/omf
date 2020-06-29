/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : DerivationModelGroupVO.java
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
public class DerivationModelGroupVO extends BusinessObjectMasterVO {
    private String        deriMainCode                                      ;
    private String        suffixRule1                                       ;
    private String        suffixRule2                                       ;
    private String        suffixRule3                                       ;
    private String        suffixRule4                                       ;
    private String        suffixRule5                                       ;
    private String        suffixRule6                                       ;
    private String        suffixRule7                                       ;
    private String        divisionCode                                      ;


    public void    setDeriMainCode(String deriMainCode){
        this.deriMainCode = deriMainCode;
    }
    public void    setSuffixRule1(String suffixRule1){
        this.suffixRule1 = suffixRule1;
    }
    public void    setSuffixRule2(String suffixRule2){
        this.suffixRule2 = suffixRule2;
    }
    public void    setSuffixRule3(String suffixRule3){
        this.suffixRule3 = suffixRule3;
    }
    public void    setSuffixRule4(String suffixRule4){
        this.suffixRule4 = suffixRule4;
    }
    public void    setSuffixRule5(String suffixRule5){
        this.suffixRule5 = suffixRule5;
    }
    public void    setSuffixRule6(String suffixRule6){
        this.suffixRule6 = suffixRule6;
    }
    public void    setSuffixRule7(String suffixRule7){
        this.suffixRule7 = suffixRule7;
    }
    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public String getDeriMainCode(){
        return deriMainCode;
    }
    public String getSuffixRule1(){
        return suffixRule1;
    }
    public String getSuffixRule2(){
        return suffixRule2;
    }
    public String getSuffixRule3(){
        return suffixRule3;
    }
    public String getSuffixRule4(){
        return suffixRule4;
    }
    public String getSuffixRule5(){
        return suffixRule5;
    }
    public String getSuffixRule6(){
        return suffixRule6;
    }
    public String getSuffixRule7(){
        return suffixRule7;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
}

