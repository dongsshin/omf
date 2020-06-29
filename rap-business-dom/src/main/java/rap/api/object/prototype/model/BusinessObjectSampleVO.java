/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : BusinessObjectSampleVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.prototype.model;


import com.rap.omc.api.object.model.BusinessObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class BusinessObjectSampleVO extends BusinessObjectVO {
    private String        attr1                                             ;
    private String        attr2                                             ;


    public void    setAttr1(String attr1){
        this.attr1 = attr1;
    }
    public void    setAttr2(String attr2){
        this.attr2 = attr2;
    }
    public String getAttr1(){
        return attr1;
    }
    public String getAttr2(){
        return attr2;
    }
}

