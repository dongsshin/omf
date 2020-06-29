/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : BusinessObjectSample2VO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.prototype.model;


import rap.api.object.prototype.model.BusinessObjectSample1VO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class BusinessObjectSample2VO extends BusinessObjectSample1VO {
    private Integer       attr5                                             ;
    private Integer       attr6                                             ;


    public void    setAttr5(Integer attr5){
        this.attr5 = attr5;
    }
    public void    setAttr6(Integer attr6){
        this.attr6 = attr6;
    }
    public Integer getAttr5(){
        return attr5;
    }
    public Integer getAttr6(){
        return attr6;
    }
}

