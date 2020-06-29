/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : BusinessObjectSample1VO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.prototype.model;


import rap.api.object.prototype.model.BusinessObjectSampleVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class BusinessObjectSample1VO extends BusinessObjectSampleVO {
    private String        attr3                                             ;


    public void    setAttr3(String attr3){
        this.attr3 = attr3;
    }
    public String getAttr3(){
        return attr3;
    }
}

